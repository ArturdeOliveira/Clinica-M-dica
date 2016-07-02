package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.clinicamedica.modelo.Estado;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.modelo.Sexo;
import com.clinicamedica.service.CadastroPacienteService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente paciente;
	
	@Inject
	private CadastroPacienteService cadastroPacienteService;
	
	private List<Sexo> sexos;
	private List<Estado> estados;
	
	private UploadedFile fotoPaciente;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
		this.estados = Arrays.asList(Estado.values());
	}
	
	public void salvar() {
		try {
			
			if (this.fotoPaciente != null && this.fotoPaciente.getContents() != null 
					&& this.fotoPaciente.getContents().length > 0) {
				this.paciente.setFoto(this.fotoPaciente.getContents());
			}
			
			this.cadastroPacienteService.salvar(paciente);
			FacesUtil.addSuccessMessage("paciente salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.paciente = new Paciente();
	}

	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public List<Sexo> getSexos() {
		return sexos;
	}
	public List<Estado> getEstados() {
		return estados;
	}

	public UploadedFile getFotoPaciente() {
		return fotoPaciente;
	}

	public void setFotoPaciente(UploadedFile fotoPaciente) {
		this.fotoPaciente = fotoPaciente;
	}

}