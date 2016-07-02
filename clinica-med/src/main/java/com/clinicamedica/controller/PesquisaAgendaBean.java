package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.AgendaDAO;
import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.modelo.Agenda;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Agenda agenda;
	private List<Paciente> pacientes;
	private List<Medico> medicos;
	private List<Agenda> agendas;
	
	private Agenda agendaSelecionado;

	@Inject
	private AgendaDAO agendaDAO;
	
	@Inject
	private PacienteDAO pacienteDAO;
	
	@Inject
	private MedicoDAO medicoDAO;
	
	//@Inject
	//@UsuarioLogado
	//private UsuarioSistema usuarioLogado;
	
	@PostConstruct
	public void inicializar() {
		this.agenda = new Agenda();
		this.agendas = new ArrayList<>();
		this.pacientes = this.pacienteDAO.buscarTodos();
		this.medicos = this.medicoDAO.buscarTodos();
	}
	
	public void pesquisar() {
		this.agendas = agendaDAO.buscarPorDataConsultaEMedico(this.agenda.getDataConsulta(), this.agenda.getPaciente(),this.agenda.getMedico());
	}
	
	public void excluir() {
		try {
			agendaDAO.excluir(agendaSelecionado);
			this.agendas.remove(agendaSelecionado);
			FacesUtil.addSuccessMessage("Agendamento excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Agenda getAgendaSelecionado() {
		return agendaSelecionado;
	}
	public void setAgendaSelecionado(Agenda agendaSelecionado) {
		this.agendaSelecionado = agendaSelecionado;
	}
	

	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	
}