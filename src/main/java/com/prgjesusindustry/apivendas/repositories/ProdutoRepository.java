package com.prgjesusindustry.apivendas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prgjesusindustry.apivendas.domain.Categoria;
import com.prgjesusindustry.apivendas.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	@Query("SELECT DISTINCT obj FROM Produto INNER JOIN obj.categorias cat WHERE obj.mame LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome")String nome, @Param("categorias")List<Categoria> categorias, Pageable pageRequest);
	
}
