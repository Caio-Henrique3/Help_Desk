package com.helpdesk.HelpDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	
}
