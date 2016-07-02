package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroPacienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PacienteDAO pacienteDAO;
	
	@Transactional
	public void salvar(Paciente paciente) throws NegocioException {
		if (paciente.getNome() == null || paciente.getNome().trim().equals("")) { 
			throw new NegocioException("O nome do paciente é obrigatório");
		}
		
		this.pacienteDAO.salvar(paciente);
	}
	
}
