package com.prgjesusindustry.apivendas.services;

import org.springframework.mail.SimpleMailMessage;

import com.prgjesusindustry.apivendas.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
