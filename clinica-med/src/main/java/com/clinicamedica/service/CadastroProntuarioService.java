package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.clinicamedica.dao.ProntuarioDAO;
import com.clinicamedica.modelo.Prontuario;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroProntuarioService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	ProntuarioDAO prontuarioDAO;

	@Transactional
	//Descrição obrigratória
	public void salvar(Prontuario prontuario) throws NegocioException {
		if (prontuario.getPeso() == null || prontuario.getPeso().trim().equals("")) {
			throw new NegocioException("O peso é obrigatório.");
		}
		if (prontuario.getAltura() == null) {
			throw new NegocioException("A altura é obrigatório");
		}
		if (prontuario.getTemperatura() == null) {
			throw new NegocioException("A temperatura é obrigatório");
		}
		if (prontuario.getPressao() == null) {
			throw new NegocioException("A pressao é obrigatório");
		}
		if (prontuario.getFrequencia_cardiaca() == null) {
			throw new NegocioException("A frequência cardíaca é obrigatório");
		}
		this.prontuarioDAO.salvar(prontuario);
	}

	
}