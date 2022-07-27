package com.helpdesk.HelpDesk.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.enums.Prioridade;
import com.helpdesk.HelpDesk.enums.Status;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Chamado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento = LocalDate.now();
	private String titulo;
	private String observacoes;
	private Prioridade priopridade;
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;
	
	public Chamado() {
		super();
	}

	public Chamado(Integer id, String titulo, String observacoes, Prioridade priopridade, Status status,
			Cliente cliente, Tecnico tecnico) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.priopridade = priopridade;
		this.status = status;
		this.cliente = cliente;
		this.tecnico = tecnico;
	}
	
}
