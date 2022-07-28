package com.helpdesk.HelpDesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.exception.DataIntegrityValidationException;
import com.helpdesk.HelpDesk.exception.ObjectNotFounExcepetion;
import com.helpdesk.HelpDesk.model.Pessoa;
import com.helpdesk.HelpDesk.model.Tecnico;
import com.helpdesk.HelpDesk.modelDTO.TecnicoDTO;
import com.helpdesk.HelpDesk.repository.PessoaRepository;
import com.helpdesk.HelpDesk.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFounExcepetion("Técnico de id " + id + " não econtrado"));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico created(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validarCPFeEmail(tecnicoDTO);
		return tecnicoRepository.save(new Tecnico(tecnicoDTO));
	}

	private void validarCPFeEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityValidationException("CPF já cadastrado no sistema!");
		}
		
		pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityValidationException("Email já cadastrado no sistema!");
		}
	}
	
}
