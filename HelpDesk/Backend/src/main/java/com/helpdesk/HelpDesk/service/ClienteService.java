package com.helpdesk.HelpDesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.exception.DataIntegrityValidationException;
import com.helpdesk.HelpDesk.exception.ObjectNotFounExcepetion;
import com.helpdesk.HelpDesk.model.Cliente;
import com.helpdesk.HelpDesk.model.Pessoa;
import com.helpdesk.HelpDesk.modelDTO.ClienteDTO;
import com.helpdesk.HelpDesk.repository.ClienteRepository;
import com.helpdesk.HelpDesk.repository.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFounExcepetion("Cliente de id " + id + " não econtrado!"));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente created(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		validarCPFeEmail(clienteDTO);
		return clienteRepository.save(new Cliente(clienteDTO));
	}

	public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		Cliente cliente = findById(id);
		validarCPFeEmail(clienteDTO);
		cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}
	
	public void delete(Integer id) {
		Cliente cliente = findById(id);
		if (cliente.getChamados().size() > 0) {
			throw new DataIntegrityValidationException("Este cliente possui Orderns de Serviços e não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
	}

	private void validarCPFeEmail(ClienteDTO clienteDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityValidationException("CPF já cadastrado no sistema!");
		}
		
		pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityValidationException("Email já cadastrado no sistema!");
		}
	}
	
}
