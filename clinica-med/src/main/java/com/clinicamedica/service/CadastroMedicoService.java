package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroMedicoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MedicoDAO medicoDAO;
	
	@Transactional
	public void salvar(Medico medico) throws NegocioException {
		if (medico.getNome() == null || medico.getNome().trim().equals("")) { 
			throw new NegocioException("O nome do médico é obrigatório");
		}if (medico.getEspecialidade() == null ){
			throw new NegocioException("O nome da especialidade é obrigatório");
		}
		
		this.medicoDAO.salvar(medico);
	}
	
}
