package com.moises.odontoDelta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moises.odontoDelta.domain.PecaItem;

@Repository
public interface PecaItemRepository extends JpaRepository<PecaItem, Integer>{


	@Transactional(readOnly=true)
	@Query("SELECT obj FROM PecaItem obj WHERE obj.codigo.peca.codigo=:peca AND obj.codigo.orcamento.codigo=:orcamento") 
	Optional<PecaItem> fiPecaItemOrcamento(@Param("peca") Integer peca, @Param("orcamento") Integer orcamento);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PecaItem obj WHERE obj.codigo.peca.codigo=:peca AND obj.codigo.orcamento.codigo=:orcamento") 
	public void delPecaItemOrcamento(@Param("peca") Integer peca, @Param("orcamento") Integer orcamento);
}
