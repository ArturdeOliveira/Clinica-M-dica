package com.clinicamedica.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.clinicamedica.dao.EspecialidadeDAO;
import com.clinicamedica.modelo.Especialidade;
import com.clinicamedica.modelo.Estado;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Sexo;
import com.clinicamedica.service.CadastroMedicoService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Medico medico;

	@Inject
	private CadastroMedicoService cadastroMedicoService;

	private List<Sexo> sexos;
	private List<Estado> estados;
	private List<Especialidade> especialidades;

	@Inject
	private EspecialidadeDAO especialidadeDAO;
	
	private UploadedFile fotoMedico;

	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.especialidades = especialidadeDAO.buscarTodos();
		this.sexos = Arrays.asList(Sexo.values());
		this.estados = Arrays.asList(Estado.values());
	}

	public void salvar() {
		try {
			
			if (this.fotoMedico != null && this.fotoMedico.getContents() != null 
					&& this.fotoMedico.getContents().length > 0) {
				this.medico.setFoto(this.fotoMedico.getContents());
			}
			
			this.cadastroMedicoService.salvar(medico);
			FacesUtil.addSuccessMessage("Médico salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}

		this.limpar();
	}

	public void limpar() {
		this.medico = new Medico();
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public UploadedFile getFotoMedico() {
		return fotoMedico;
	}

	public void setFotoMedico(UploadedFile fotoMedico) {
		this.fotoMedico = fotoMedico;
	}
	
}