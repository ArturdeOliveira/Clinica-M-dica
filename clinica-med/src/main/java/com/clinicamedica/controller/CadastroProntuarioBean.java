package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.dao.ExameDAO;
import com.clinicamedica.dao.MedicamentoDAO;
import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.modelo.Exame;
import com.clinicamedica.modelo.Local;
import com.clinicamedica.modelo.Medicamento;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.modelo.Prontuario;
import com.clinicamedica.modelo.Status;
import com.clinicamedica.service.CadastroProntuarioService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProntuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Prontuario prontuario;
	
	private List<Medico> medicos;
	private List<Paciente> pacientes;
	private List<Medicamento> medicamentos;
	private List<Exame> exames;
	private List<Local> locais;
	private List<Status> status;
	
	@Inject
	private CadastroProntuarioService cadastroProntuarioService;
	
	@Inject
	private MedicoDAO medicoDAO;
	@Inject
	private PacienteDAO pacienteDAO;
	@Inject
	private MedicamentoDAO medicamentoDAO;
	@Inject
	private ExameDAO exameDAO;
	
	public void salvar() {
		try {
			this.cadastroProntuarioService.salvar(prontuario);
			FacesUtil.addSuccessMessage("Prontuário salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.medicos = medicoDAO.buscarTodos();
		this.pacientes = pacienteDAO.buscarTodos();
		this.medicamentos = medicamentoDAO.buscarTodos();
		this.exames = exameDAO.buscarTodos();
		this.locais = Arrays.asList(Local.values());
		this.status = Arrays.asList(Status.values());
	}
	
	public void limpar() {
		this.prontuario = new Prontuario();
		this.prontuario.setMedicamentos(new ArrayList<Medicamento>());
		this.prontuario.setExames(new ArrayList<Exame>());
	}

	public Prontuario getProntuario() {
		return prontuario;
	}
	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	
	public List<Exame> getExames(){
		return exames;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public List<Status> getStatus() {
		return status;
	}
	
}