package com.moises.odontoDelta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moises.odontoDelta.domain.ServicoItem;

@Repository
public interface ServicoItemRepository extends JpaRepository<ServicoItem, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM ServicoItem obj WHERE obj.codigo.servico.codigo=:servico AND obj.codigo.orcamento.codigo=:orcamento") 
	Optional<ServicoItem> fiServicoItemOrcamento(@Param("servico") Integer Servico, @Param("orcamento") Integer orcamento);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM ServicoItem obj WHERE obj.codigo.servico.codigo=:servico AND obj.codigo.orcamento.codigo=:orcamento") 
	public void delServicoItemOrcamento(@Param("servico") Integer Servico, @Param("orcamento") Integer orcamento);
}
