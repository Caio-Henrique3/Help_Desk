package com.helpdesk.HelpDesk.modelDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.enums.Perfil;
import com.helpdesk.HelpDesk.model.Tecnico;

public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	@NotBlank(message = "O campo nome não pode ser nulo ou vazio")
	protected String nome;
	
	@NotBlank(message = "O campo CPF não pode ser nulo ou vazio")
	protected String cpf;
	
	@NotBlank(message = "O campo email não pode ser nulo ou vazio")
	protected String email;
	
	@NotBlank(message = "O campo senha não pode ser nulo ou vazio")
	protected String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	protected Set<Integer> perfis = new HashSet<>();
	
	public TecnicoDTO() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.email = tecnico.getEmail();
		this.senha = tecnico.getSenha();
		this.dataCriacao = tecnico.getDataCriacao();
		this.perfis = tecnico.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		addPerfis(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
	
}
