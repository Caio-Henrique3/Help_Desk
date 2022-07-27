package com.helpdesk.HelpDesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.enums.Perfil;
import com.helpdesk.HelpDesk.enums.Prioridade;
import com.helpdesk.HelpDesk.enums.Status;
import com.helpdesk.HelpDesk.model.Chamado;
import com.helpdesk.HelpDesk.model.Cliente;
import com.helpdesk.HelpDesk.model.Tecnico;
import com.helpdesk.HelpDesk.repository.ChamadoRepository;
import com.helpdesk.HelpDesk.repository.ClienteRepository;
import com.helpdesk.HelpDesk.repository.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "700.441.260-18", "torvalds@mail.com", "123");
		Tecnico tec1 = new Tecnico(null, "Caio Henrique", "335.882.170-61", "caio@mail.com", "123");
		tec1.addPerfis(Perfil.ADMIN);
		Chamado c1 = new Chamado(null, "Chamado 01", "Primeiro Chamado", Prioridade.MEDIA, Status.ANDAMENTO, cli1, tec1);
	
		clienteRepository.saveAll(Arrays.asList(cli1));
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
	
}
