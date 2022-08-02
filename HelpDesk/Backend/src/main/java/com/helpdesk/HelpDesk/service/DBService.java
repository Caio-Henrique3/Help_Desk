package com.helpdesk.HelpDesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.enums.Perfil;
import com.helpdesk.HelpDesk.enums.Prioridade;
import com.helpdesk.HelpDesk.enums.Status;
import com.helpdesk.HelpDesk.model.Chamado;
import com.helpdesk.HelpDesk.model.Cliente;
import com.helpdesk.HelpDesk.model.Tecnico;
import com.helpdesk.HelpDesk.repository.ChamadoRepository;
import com.helpdesk.HelpDesk.repository.PessoaRepository;

@Service
public class DBService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Caio Henrique", "335.882.170-61", "caio@mail.com", encoder.encode("123"));
		tec1.addPerfis(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

		Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
 
		Chamado c1 = new Chamado(null, "Chamado 1", "Teste chamado 1", Prioridade.MEDIA, Status.ANDAMENTO, cli1, tec1);
		Chamado c2 = new Chamado(null, "Chamado 2", "Teste chamado 2", Prioridade.ALTA, Status.ABERTO, cli2, tec1);
		Chamado c3 = new Chamado(null, "Chamado 3", "Teste chamado 3", Prioridade.BAIXA, Status.ENCERRADO, cli3, tec2);
		Chamado c4 = new Chamado(null, "Chamado 4", "Teste chamado 4", Prioridade.ALTA, Status.ABERTO, cli3, tec3);
		Chamado c5 = new Chamado(null, "Chamado 5", "Teste chamado 5",Prioridade.MEDIA, Status.ANDAMENTO, cli1, tec2);
		Chamado c6 = new Chamado(null, "Chamado 7", "Teste chamado 6",Prioridade.BAIXA, Status.ENCERRADO, cli5, tec1);

		pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
	}
	
}
