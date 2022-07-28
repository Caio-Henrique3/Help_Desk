package com.helpdesk.HelpDesk.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.enums.Prioridade;
import com.helpdesk.HelpDesk.enums.Status;
import com.helpdesk.HelpDesk.exception.ObjectNotFoundExcepetion;
import com.helpdesk.HelpDesk.model.Chamado;
import com.helpdesk.HelpDesk.model.Cliente;
import com.helpdesk.HelpDesk.model.Tecnico;
import com.helpdesk.HelpDesk.modelDTO.ChamadoDTO;
import com.helpdesk.HelpDesk.repository.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		return chamadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepetion("Chamado de id " + id + " não econtrado!"));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	
	public Chamado update(Integer id, @Valid ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		Chamado chamado = findById(id);
		chamado = newChamado(chamadoDTO);
		return chamadoRepository.save(chamado);
	}

	private Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		Cliente cliente = clienteService.findById(chamadoDTO.getCliente());
		Chamado chamado = new Chamado();
		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		if (chamadoDTO.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());

		return chamado;
	}

}
