package com.helpdesk.HelpDesk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.HelpDesk.enums.Perfil;
import com.helpdesk.HelpDesk.modelDTO.TecnicoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tecnico extends Pessoa {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfis(Perfil.TECNICO);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.TECNICO);
	}
	
	public Tecnico(TecnicoDTO tecnicoDTO) {
		super();
		this.id = tecnicoDTO.getId();
		this.nome = tecnicoDTO.getNome();
		this.cpf = tecnicoDTO.getCpf();
		this.email = tecnicoDTO.getEmail();
		this.senha = tecnicoDTO.getSenha();
		this.dataCriacao = tecnicoDTO.getDataCriacao();
		this.perfis = tecnicoDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
	}
	
}
