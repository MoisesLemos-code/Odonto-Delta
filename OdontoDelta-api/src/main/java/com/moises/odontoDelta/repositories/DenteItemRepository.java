package com.moises.odontoDelta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moises.odontoDelta.domain.DenteItem;

@Repository
public interface DenteItemRepository extends JpaRepository<DenteItem, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM DenteItem obj WHERE obj.codigo.dente.codigo=:dente AND obj.codigo.orcamento.codigo=:orcamento") 
	Optional<DenteItem> fiDenteItemOrcamento(@Param("dente") Integer Dente, @Param("orcamento") Integer orcamento);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM DenteItem obj WHERE obj.codigo.dente.codigo=:dente AND obj.codigo.orcamento.codigo=:orcamento") 
	public void delDenteItemOrcamento(@Param("dente") Integer Dente, @Param("orcamento") Integer orcamento);
	
}
