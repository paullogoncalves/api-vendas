package com.prgjesusindustry.apivendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.prgjesusindustry.apivendas.domain.Categoria;
import com.prgjesusindustry.apivendas.domain.Produto;
import com.prgjesusindustry.apivendas.repositories.CategoriaRepository;
import com.prgjesusindustry.apivendas.repositories.ProdutoRepository;
import com.prgjesusindustry.apivendas.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto find(Integer id) {
		Optional<Produto> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id:" + id 
				+ "Tipo:" + Produto.class.getName()));
		
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
