package com.clinicamedica.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.clinicamedica.dao.MedicamentoDAO;
import com.clinicamedica.modelo.Medicamento;
import com.clinicamedica.util.jpa.Transactional;

public class CadastroMedicamentoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MedicamentoDAO medicamentoDAO;
	
	@Transactional
	public void salvar(Medicamento me) throws NegocioException {
		
		if (StringUtils.isBlank(me.getNome())) {
			throw new NegocioException("O medicamento é obrigatório");
		}
		
		this.medicamentoDAO.salvar(me);
	}
	
}
