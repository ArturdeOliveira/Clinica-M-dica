package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.ExameDAO;
import com.clinicamedica.modelo.Exame;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaExameBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Exame> examesFiltrados;
	
	private String nome;

	@Inject
	ExameDAO exameDAO;
	
	private List<Exame> exames = new ArrayList<>();
	
	private Exame exameSelecionado;
	
	public List<Exame> getExames() {
		return exames;
	}
	
	public void pesquisar() {
		this.examesFiltrados = this.exameDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			exameDAO.excluir(exameSelecionado);
			this.examesFiltrados.remove(exameSelecionado);
			FacesUtil.addSuccessMessage("Exame " + exameSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Exame> getExamesFiltrados() {
		return examesFiltrados;
	}

	public void setExamesFiltrados(List<Exame> examesFiltrados) {
		this.examesFiltrados = examesFiltrados;
	}

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		exames = exameDAO.buscarTodos();
	}
}