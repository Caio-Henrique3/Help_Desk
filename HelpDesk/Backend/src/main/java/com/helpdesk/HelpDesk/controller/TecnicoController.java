package com.helpdesk.HelpDesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.HelpDesk.modelDTO.TecnicoDTO;
import com.helpdesk.HelpDesk.service.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(new TecnicoDTO(tecnicoService.findById(id)));
	}
	
}
