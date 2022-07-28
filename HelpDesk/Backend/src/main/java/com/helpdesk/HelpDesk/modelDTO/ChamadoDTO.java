package com.helpdesk.HelpDesk.modelDTO;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.model.Chamado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;

	@NotBlank(message = "O campo título é obrigatório!")
	private String titulo;

	@NotBlank(message = "O campo observações é obrigatório!")
	private String observacoes;
	private String nomeCliente;
	private String nomeTecnico;

	@NotNull(message = "O campo prioridade é obrigatório!")
	private Integer prioridade;

	@NotNull(message = "O campo status é obrigatório!")
	private Integer status;

	@NotNull(message = "O campo cliente é obrigatório!")
	private Integer cliente;

	@NotNull(message = "O campo técnico é obrigatório!")
	private Integer tecnico;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.nomeCliente = chamado.getCliente().getNome();
		this.nomeTecnico = chamado.getTecnico().getNome();
		this.prioridade = chamado.getPrioridade().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.cliente = chamado.getCliente().getId();
		this.tecnico = chamado.getTecnico().getId();
	}

}
