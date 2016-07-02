package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Paciente> pacientesFiltrados;
	
	private String nome;

	@Inject
	PacienteDAO pacienteDAO;
	
	private List<Paciente> pacientes = new ArrayList<>();
	
	private Paciente pacienteSelecionado;
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	public void pesquisar() {
		this.pacientesFiltrados = this.pacienteDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			pacienteDAO.excluir(pacienteSelecionado);
			this.pacientesFiltrados.remove(pacienteSelecionado);
			FacesUtil.addSuccessMessage("Paciente " + pacienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}
	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}
	
	public List<Paciente> getPacientesFiltrados() {
		return pacientesFiltrados;
	}

	public void setPacientesFiltrados(List<Paciente> pacientesFiltrados) {
		this.pacientesFiltrados = pacientesFiltrados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		pacientes = pacienteDAO.buscarTodos();
	}
}