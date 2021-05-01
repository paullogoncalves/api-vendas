package com.prgjesusindustry.apivendas.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgjesusindustry.apivendas.domain.Cliente;
import com.prgjesusindustry.apivendas.domain.ItemPedido;
import com.prgjesusindustry.apivendas.domain.PagamentoComBoleto;
import com.prgjesusindustry.apivendas.domain.Pedido;
import com.prgjesusindustry.apivendas.domain.enums.EstadoPagamento;
import com.prgjesusindustry.apivendas.repositories.ItemPedidoRepository;
import com.prgjesusindustry.apivendas.repositories.PagamentoRepository;
import com.prgjesusindustry.apivendas.repositories.PedidoRepository;
import com.prgjesusindustry.apivendas.security.UserSS;
import com.prgjesusindustry.apivendas.services.exceptions.AuthorizationException;
import com.prgjesusindustry.apivendas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagtoRepo;
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private ItemPedidoRepository itemRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id:" + id 
				+ "Tipo:" + Pedido.class.getName()));
		
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null); 
		obj.setInstante(LocalDateTime.now());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
			
		}
		obj = repo.save(obj);
		pagtoRepo.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(prodService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
//	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
//		UserSS user = UserService.authenticated();
//		if (user == null) {
//			throw new AuthorizationException("Acesso negado");
//		}
//		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//		Cliente cliente =  clienteService.find(user.getId());
//		return repo.findByCliente(cliente, pageRequest);
//	}
}
