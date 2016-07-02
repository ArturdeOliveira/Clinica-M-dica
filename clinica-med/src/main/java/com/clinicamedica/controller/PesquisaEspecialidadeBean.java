package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.EspecialidadeDAO;
import com.clinicamedica.modelo.Especialidade;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEspecialidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Especialidade> especialidadesFiltrados;
	
	private String nome;

	@Inject
	EspecialidadeDAO especialidadeDAO;
	
	private List<Especialidade> especialidades = new ArrayList<>();
	
	private Especialidade especialidadeSelecionado;
	
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}
	
	public void pesquisar() {
		this.especialidadesFiltrados = this.especialidadeDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			especialidadeDAO.excluir(especialidadeSelecionado);
			this.especialidadesFiltrados.remove(especialidadeSelecionado);
			FacesUtil.addSuccessMessage("Especialidade " + especialidadeSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Especialidade getEspecialidadeSelecionado() {
		return especialidadeSelecionado;
	}
	public void setEspecialidadeSelecionado(Especialidade especialidadeSelecionado) {
		this.especialidadeSelecionado = especialidadeSelecionado;
	}
	
	public List<Especialidade> getEspecialidadesFiltrados() {
		return especialidadesFiltrados;
	}

	public void setEspecialidadesFiltrados(List<Especialidade> especialidadesFiltrados) {
		this.especialidadesFiltrados = especialidadesFiltrados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		especialidades = especialidadeDAO.buscarTodos();
	}
}