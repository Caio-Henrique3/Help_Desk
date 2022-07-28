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

import com.helpdesk.HelpDesk.model.Tecnico;
import com.helpdesk.HelpDesk.modelDTO.TecnicoDTO;
import com.helpdesk.HelpDesk.service.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> tecnicos = tecnicoService.findAll();
		return ResponseEntity.ok(tecnicos.stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(new TecnicoDTO(tecnicoService.findById(id)));
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> created(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = tecnicoService.created(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tecnico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TecnicoDTO(tecnicoService.findById(tecnico.getId())));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = tecnicoService.update(id, tecnicoDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
