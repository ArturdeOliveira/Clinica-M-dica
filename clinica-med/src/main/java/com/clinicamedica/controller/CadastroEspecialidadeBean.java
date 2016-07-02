package com.clinicamedica.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.modelo.Especialidade;
import com.clinicamedica.service.CadastroEspecialidadeService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEspecialidadeBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEspecialidadeService cadastroEspecialidadeService;
	
	private Especialidade especialidade;
	
	public void salvar() {
		try {
			this.cadastroEspecialidadeService.salvar(especialidade);
			FacesUtil.addSuccessMessage("Especialidade salvo com sucesso!");
			
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.especialidade = new Especialidade();
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
}