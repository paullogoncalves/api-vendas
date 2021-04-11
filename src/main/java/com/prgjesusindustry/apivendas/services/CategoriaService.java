package com.prgjesusindustry.apivendas.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgjesusindustry.apivendas.domain.Categoria;
import com.prgjesusindustry.apivendas.repositories.CategoriaRepository;
import com.prgjesusindustry.apivendas.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		return repo.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		return repo.save(categoria);
	}
}
