package com.clinicamedica.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.modelo.Exame;
import com.clinicamedica.service.CadastroExameService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroExameBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroExameService cadastroExameService;
	
	private Exame exame;
	
	public void salvar() {
		try {
			this.cadastroExameService.salvar(exame);
			FacesUtil.addSuccessMessage("Exame salvo com sucesso!");
			
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
		this.exame = new Exame();
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
}