package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.modelo.Agenda;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.modelo.TipoConsulta;
import com.clinicamedica.service.CadastroAgendaService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Agenda agenda;
	
	private List<TipoConsulta> tipos;
	private List<Medico> medicos;
	private List<Paciente> pacientes;
	
	@Inject
	private CadastroAgendaService cadastroAgendaService;
	
	@Inject
	private MedicoDAO medicoDAO;
	@Inject
	private PacienteDAO pacienteDAO;
	
	public void salvar() {
		try {
			this.cadastroAgendaService.salvar(agenda);
			FacesUtil.addSuccessMessage("Agendamento salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.tipos = Arrays.asList(TipoConsulta.values());
		this.medicos = this.medicoDAO.buscarTodos();
		this.pacientes = this.pacienteDAO.buscarTodos();
		
	}
	
	public void limpar() {
		this.agenda = new Agenda();
		
	}

	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public List<TipoConsulta> getTipoConsultas() {
		return tipos;
	}
	
	public List<Medico> getMedicos() {
	    return medicos;
	}
	public List<Paciente> getPacientes() {
	    return pacientes;
	}
	
}