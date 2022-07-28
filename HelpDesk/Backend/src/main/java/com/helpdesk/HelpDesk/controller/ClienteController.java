package com.helpdesk.HelpDesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helpdesk.HelpDesk.model.Cliente;
import com.helpdesk.HelpDesk.modelDTO.ClienteDTO;
import com.helpdesk.HelpDesk.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(new ClienteDTO(clienteService.findById(id)));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> created(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.created(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(clienteService.findById(cliente.getId())));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.update(id, clienteDTO);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
