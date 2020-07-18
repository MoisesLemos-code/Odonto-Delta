package com.moises.odontoDelta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moises.odontoDelta.domain.Dente;

@Repository
public interface DenteRepository extends JpaRepository<Dente, Integer>{

	
}
