package com.helpdesk.HelpDesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.exception.DataIntegrityValidationException;
import com.helpdesk.HelpDesk.exception.ObjectNotFoundExcepetion;
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundExcepetion("Técnico de id " + id + " não econtrado!"));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico created(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validarCPFeEmail(tecnicoDTO);
		tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
		return tecnicoRepository.save(new Tecnico(tecnicoDTO));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(id);
		Tecnico tecnico = findById(id);
		validarCPFeEmail(tecnicoDTO);
		if (!tecnicoDTO.getSenha().equals(tecnico.getSenha())) {
			tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
		}
		
		tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}
	
	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if (tecnico.getChamados().size() > 0) {
			throw new DataIntegrityValidationException("Este técnico possui Orderns de Serviços e não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);
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
