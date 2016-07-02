package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.clinicamedica.dao.AgendaDAO;
import com.clinicamedica.modelo.Agenda;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroAgendaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AgendaDAO agendaDAO;
	
	@Transactional
	public void salvar(Agenda agenda) throws NegocioException {
		
		if (agenda.getMedico() == null) {
			throw new NegocioException("O médico é obrigatório");
		}
		
		this.agendaDAO.salvar(agenda);
	}

}