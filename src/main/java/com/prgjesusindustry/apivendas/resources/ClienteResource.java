package com.prgjesusindustry.apivendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgjesusindustry.apivendas.domain.Cliente;
import com.prgjesusindustry.apivendas.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity <Cliente> getCliente(@PathVariable(name = "id") Integer id) {
		Cliente cliente = service.getCliente(id);
		
		
		return ResponseEntity.ok().body(cliente);
	}
}
