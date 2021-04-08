package com.prgjesusindustry.apivendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgjesusindustry.apivendas.domain.Pedido;
import com.prgjesusindustry.apivendas.repositories.PedidoRepository;
import com.prgjesusindustry.apivendas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id:" + id 
				+ "Tipo:" + Pedido.class.getName()));
		
	}
}
