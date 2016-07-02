package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Medico> medicosFiltrados;
	
	private String nome;
	
	@Inject
	MedicoDAO medicoDAO;

	private List<Medico> medicos = new ArrayList<>();
	
	private Medico medicoSelecionado;
	
	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public void pesquisar() {
		this.medicosFiltrados = this.medicoDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			medicoDAO.excluir(medicoSelecionado);
			this.medicosFiltrados.remove(medicoSelecionado);
			FacesUtil.addSuccessMessage("Médico " + medicoSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}
	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}

	public List<Medico> getMedicosFiltrados() {
		return medicosFiltrados;
	}

	public void setMedicosFiltrados(List<Medico> medicosFiltrados) {
		this.medicosFiltrados = medicosFiltrados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		medicos = medicoDAO.buscarTodos();
	}
}