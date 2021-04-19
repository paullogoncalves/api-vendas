package com.prgjesusindustry.apivendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgjesusindustry.apivendas.domain.Pedido;
import com.prgjesusindustry.apivendas.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class ProdutoResource {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable(name = "id") Integer id) {
		Pedido pedido = service.find(id);
		return ResponseEntity.ok().body(pedido);
	}

}
