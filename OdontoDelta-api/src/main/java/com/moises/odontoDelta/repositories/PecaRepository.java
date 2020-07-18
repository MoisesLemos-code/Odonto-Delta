package com.moises.odontoDelta.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moises.odontoDelta.domain.Categoria;
import com.moises.odontoDelta.domain.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Integer> {

	@Transactional(readOnly=true) 
	@Query("SELECT DISTINCT obj FROM Peca obj INNER JOIN obj.categorias "
			+ "cat WHERE obj.descricao LIKE %:descricao% AND cat IN :categorias")
	Page<Peca> search(@Param("descricao") String descricao, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	//Page<Peca> findDistinctByDescricaoContainingAndCategoriasIn(String descricao, List<Categoria> categorias, Pageable pageRequest);   
}