package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.clinicamedica.dao.EspecialidadeDAO;
import com.clinicamedica.modelo.Especialidade;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroEspecialidadeService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EspecialidadeDAO especialidadeDAO;
	
	@Transactional
	public void salvar(Especialidade esp) throws NegocioException {
		if (esp.getNome() == null || esp.getNome().trim().equals("")) { 
			throw new NegocioException("O nome da especialidade é obrigatório");
		}
		
		this.especialidadeDAO.salvar(esp);
	}
	
}