package com.moises.odontoDelta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moises.odontoDelta.domain.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer>{

	
}
