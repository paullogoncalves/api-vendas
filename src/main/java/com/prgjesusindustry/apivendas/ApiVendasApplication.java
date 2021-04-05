	 package com.prgjesusindustry.apivendas;

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
import com.prgjesusindustry.apivendas.domain.Produto;
import com.prgjesusindustry.apivendas.domain.enums.TipoCliente;
import com.prgjesusindustry.apivendas.repositories.CategoriaRepository;
import com.prgjesusindustry.apivendas.repositories.CidadeRepository;
import com.prgjesusindustry.apivendas.repositories.ClienteRepository;
import com.prgjesusindustry.apivendas.repositories.EnderecoRepository;
import com.prgjesusindustry.apivendas.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 100.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
		
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
		
	}

}
