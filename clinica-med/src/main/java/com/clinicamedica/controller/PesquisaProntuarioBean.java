package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.dao.ProntuarioDAO;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.modelo.Prontuario;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProntuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Prontuario prontuario;
	private List<Paciente> pacientes;
	private List<Medico> medicos;
	
	@Inject
	ProntuarioDAO prontuarioDAO;
	
	@Inject
	private PacienteDAO pacienteDAO;
	
	@Inject
	private MedicoDAO medicoDAO;
	
	private List<Prontuario> prontuarios = new ArrayList<>();;
	
	private Prontuario prontuarioSelecionado;
	
	public void pesquisar() {
		this.prontuarios = prontuarioDAO.buscarPorPacienteEMedico(this.prontuario.getPaciente(),this.prontuario.getMedico());
	}
	
	public void buscarMedicamentosParaProntuario() {
		prontuarioSelecionado = prontuarioDAO.buscarMedicamentos(prontuarioSelecionado.getCodigo());
	}
	
	public void buscarExamesParaProntuario(){
		prontuarioSelecionado = prontuarioDAO.buscarExames(prontuarioSelecionado.getCodigo());
	}
	
	
	public void excluir() {
		try {
			prontuarioDAO.excluir(prontuarioSelecionado);
			this.prontuarios.remove(prontuarioSelecionado);
			FacesUtil.addSuccessMessage("Prontuário excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Prontuario getProntuarioSelecionado() {
		return prontuarioSelecionado;
	}
	public void setProntuarioSelecionado(Prontuario prontuarioSelecionado) {
		this.prontuarioSelecionado = prontuarioSelecionado;
	}
		
	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public List<Prontuario> getProntuarios() {
		return prontuarios;
	}
	
	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	@PostConstruct
	public void inicializar() {
		this.prontuarios = prontuarioDAO.buscarTodos();
		this.pacientes = this.pacienteDAO.buscarTodos();
		this.medicos = this.medicoDAO.buscarTodos();
		this.prontuario = new Prontuario();
	}
	
}