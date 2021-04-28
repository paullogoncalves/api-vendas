package com.prgjesusindustry.apivendas.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgjesusindustry.apivendas.domain.Cidade;
import com.prgjesusindustry.apivendas.domain.Cliente;
import com.prgjesusindustry.apivendas.domain.Endereco;
import com.prgjesusindustry.apivendas.domain.enums.TipoCliente;
import com.prgjesusindustry.apivendas.dto.ClienteDTO;
import com.prgjesusindustry.apivendas.dto.ClienteNewDTO;
import com.prgjesusindustry.apivendas.repositories.ClienteRepository;
import com.prgjesusindustry.apivendas.repositories.EnderecoRepository;
import com.prgjesusindustry.apivendas.services.exceptions.DataIntegrityException;
import com.prgjesusindustry.apivendas.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados"); 
		}
	}
	
	public List<Cliente> findAll() {
		List<Cliente> list = repo.findAll();
		return list;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
				
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()), passEncoder.encode(dto.getSenha()));
		Cidade cid = new Cidade(dto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cid);
		cliente.getEnderecos().add(end);
		cliente.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2() != null) {
			cliente.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3() != null) {
			cliente.getTelefones().add(dto.getTelefone3());
		}
		return cliente; 
	}
	

}
