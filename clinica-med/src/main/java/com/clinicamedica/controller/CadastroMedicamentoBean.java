package com.clinicamedica.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.clinicamedica.modelo.Medicamento;
import com.clinicamedica.service.CadastroMedicamentoService;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMedicamentoBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroMedicamentoService cadastroMedicamentoService;
	
	private Medicamento medicamento;
	
	public void salvar() {
		try {
			this.cadastroMedicamentoService.salvar(medicamento);
			FacesUtil.addSuccessMessage("Medicamento salvo com sucesso!");
			
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
		this.medicamento = new Medicamento();
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	
}