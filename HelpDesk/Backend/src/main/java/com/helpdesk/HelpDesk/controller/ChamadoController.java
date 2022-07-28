package com.helpdesk.HelpDesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helpdesk.HelpDesk.model.Chamado;
import com.helpdesk.HelpDesk.modelDTO.ChamadoDTO;
import com.helpdesk.HelpDesk.service.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping()
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> chamados = chamadoService.findAll();
		return ResponseEntity
				.ok(chamados.stream().map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new ChamadoDTO(chamadoService.findById(id)));
	}

	@PostMapping()
	public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO) {
		Chamado chamado = chamadoService.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId())
				.toUri();
		return ResponseEntity.created(uri).body(new ChamadoDTO(chamadoService.findById(chamado.getId())));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
		return ResponseEntity.ok(new ChamadoDTO(chamadoService.update(id, chamadoDTO)));
	}

}
