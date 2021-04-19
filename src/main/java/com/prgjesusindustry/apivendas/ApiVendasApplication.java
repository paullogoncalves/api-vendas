	 package com.prgjesusindustry.apivendas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prgjesusindustry.apivendas.domain.Categoria;
import com.prgjesusindustry.apivendas.domain.Cidade;
import com.prgjesusindustry.apivendas.domain.Cliente;
import com.prgjesusindustry.apivendas.domain.Endereco;
import com.prgjesusindustry.apivendas.domain.Estado;
import com.prgjesusindustry.apivendas.domain.ItemPedido;
import com.prgjesusindustry.apivendas.domain.Pagamento;
import com.prgjesusindustry.apivendas.domain.PagamentoComBoleto;
import com.prgjesusindustry.apivendas.domain.PagamentoComCartao;
import com.prgjesusindustry.apivendas.domain.Pedido;
import com.prgjesusindustry.apivendas.domain.Produto;
import com.prgjesusindustry.apivendas.domain.enums.EstadoPagamento;
import com.prgjesusindustry.apivendas.domain.enums.TipoCliente;
import com.prgjesusindustry.apivendas.repositories.CategoriaRepository;
import com.prgjesusindustry.apivendas.repositories.CidadeRepository;
import com.prgjesusindustry.apivendas.repositories.ClienteRepository;
import com.prgjesusindustry.apivendas.repositories.EnderecoRepository;
import com.prgjesusindustry.apivendas.repositories.EstadoRepository;
import com.prgjesusindustry.apivendas.repositories.ItemPedidoRepository;
import com.prgjesusindustry.apivendas.repositories.PagamentoRepository;
import com.prgjesusindustry.apivendas.repositories.PedidoRepository;
import com.prgjesusindustry.apivendas.repositories.ProdutoRepository;

@SpringBootApplication
public class ApiVendasApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 100.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 100.00);
		Produto p5 = new Produto(null, "Toalha", 100.00);
		Produto p6 = new Produto(null, "Colcha", 100.00);
		Produto p7 = new Produto(null, "Tv true color", 100.00);
		Produto p8 = new Produto(null, "Roçadeira", 100.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 100.00);
		Produto p11 = new Produto(null, "Shampoo", 100.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia",est1 );
		Cidade c2 = new Cidade(null, "São Paulo", est2 );
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "367987600092", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("332152453", "998875432"));
		
		Endereco e1 = new Endereco(null, "Rua das flores", "100", "Casa", "Capmo Grande", "45456-98", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua são Francisco", "49", "", "Piramboraí", "43213-79", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepo.save(cli1);
		
		enderecoRepo.saveAll(Arrays.asList(e1, e2));
		
		DateTimeFormatter formaDt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		//LocalDateTime hora = LocalDateTime.parse("06/04/2021 14:00", formaDt);
		
		Pedido ped1 = new Pedido(null, LocalDateTime.parse("06/04/2021 14:00", formaDt), cli1, e1);
		Pedido ped2 = new Pedido(null, LocalDateTime.parse("01/04/2021 09:25", formaDt), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, LocalDateTime.parse("30/04/2021 09:25", formaDt), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
