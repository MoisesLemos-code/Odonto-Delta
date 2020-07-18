package com.moises.odontoDelta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moises.odontoDelta.domain.Finalizador;

@Repository
public interface FinalizadorRepository extends JpaRepository<Finalizador, Integer>{

	
}
