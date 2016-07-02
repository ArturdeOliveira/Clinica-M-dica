package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.clinicamedica.dao.ExameDAO;
import com.clinicamedica.modelo.Exame;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroExameService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ExameDAO exameDAO;
	
	@Transactional
	public void salvar(Exame exame) throws NegocioException {
		
		if (StringUtils.isBlank(exame.getNome())) {
			throw new NegocioException("O exame é obrigatório");
		}
		
		this.exameDAO.salvar(exame);
	}
	
}
