package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.MedicamentoDAO;
import com.clinicamedica.modelo.Medicamento;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMedicamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Medicamento> medicamentosFiltrados;
	
	private String nome;

	@Inject
	MedicamentoDAO medicamentoDAO;
	
	private List<Medicamento> medicamentos = new ArrayList<>();
	
	private Medicamento medicamentoSelecionado;
	
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	
	public void pesquisar() {
		this.medicamentosFiltrados = this.medicamentoDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			medicamentoDAO.excluir(medicamentoSelecionado);
			this.medicamentosFiltrados.remove(medicamentoSelecionado);
			FacesUtil.addSuccessMessage("Medicamentos " + medicamentoSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Medicamento> getMedicamentosFiltrados() {
		return medicamentosFiltrados;
	}

	public void setMedicamentosFiltrados(List<Medicamento> medicamentosFiltrados) {
		this.medicamentosFiltrados = medicamentosFiltrados;
	}

	public Medicamento getMedicamentoSelecionado() {
		return medicamentoSelecionado;
	}

	public void setMedicamentoSelecionado(Medicamento medicamentoSelecionado) {
		this.medicamentoSelecionado = medicamentoSelecionado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		medicamentos = medicamentoDAO.buscarTodos();
	}
}