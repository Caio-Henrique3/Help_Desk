package com.helpdesk.HelpDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.model.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

	
	
}
