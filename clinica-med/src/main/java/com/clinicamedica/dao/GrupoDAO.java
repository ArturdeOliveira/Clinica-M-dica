package com.clinicamedica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.clinicamedica.modelo.Grupo;

public class GrupoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Grupo> todos() {
		return this.manager.createQuery("from Grupo order by nome", Grupo.class).getResultList();
	}

	public Grupo porId(Long id) {
		return this.manager.find(Grupo.class, id);
	}

}
