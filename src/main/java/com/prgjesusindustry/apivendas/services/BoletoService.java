package com.prgjesusindustry.apivendas.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.prgjesusindustry.apivendas.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instantePedido) {
		LocalDateTime date = instantePedido.plusDays(7);
		pagto.setDataVencimento(date);
	}
}
