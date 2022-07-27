package com.helpdesk.HelpDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	
	
}
