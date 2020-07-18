package com.moises.odontoDelta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moises.odontoDelta.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

	
}
