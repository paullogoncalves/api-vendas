package com.prgjesusindustry.apivendas.domain;

import com.prgjesusindustry.apivendas.domain.enums.EstadoPagamento;

public class PagamentoComCartão extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numerosDeParcelas;
	
	public PagamentoComCartão() {
		
	}

	public PagamentoComCartão(Integer id, EstadoPagamento estado, Pedido pedido, 
			Integer numerosDeParcelas) {
		super(id, estado, pedido);
		this.numerosDeParcelas = numerosDeParcelas;
	}

	public Integer getNumerosDeParcelas() {
		return numerosDeParcelas;
	}

	public void setNumerosDeParcelas(Integer numerosDeParcelas) {
		this.numerosDeParcelas = numerosDeParcelas;
	}
	
	
}
