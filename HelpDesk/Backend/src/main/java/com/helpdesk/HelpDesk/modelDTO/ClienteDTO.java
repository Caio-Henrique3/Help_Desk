package com.helpdesk.HelpDesk.modelDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.enums.Perfil;
import com.helpdesk.HelpDesk.model.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	@NotBlank(message = "O campo nome é obrigatório")
	protected String nome;
	
	@CPF
	@NotBlank(message = "O campo CPF é obrigatório")
	protected String cpf;
	
	@Email
	@NotBlank(message = "O campo email é obrigatório")
	protected String email;
	
	@NotBlank(message = "O campo senha é obrigatório")
	protected String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	protected Set<Integer> perfis = new HashSet<>();
	
	public ClienteDTO() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
		this.dataCriacao = cliente.getDataCriacao();
		this.perfis = cliente.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
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
