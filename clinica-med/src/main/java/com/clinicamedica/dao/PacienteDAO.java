package com.clinicamedica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class PacienteDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Paciente burcarPeloCodigo(Long codigo){
		return manager.find(Paciente.class, codigo);
	}
	
	public void salvar(Paciente paciente){
		manager.merge(paciente);
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> buscarTodos(){
		return manager.createQuery("from Paciente order by nome").getResultList();
	}
	@Transactional
	public void excluir(Paciente paciente) throws NegocioException{
		paciente = burcarPeloCodigo(paciente.getCodigo());
		try{
			manager.remove(paciente);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Paciente não pode ser excluido!");
		}
	}	
	@SuppressWarnings("unchecked")
	public List<Paciente> filtrados(String nome) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Paciente.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
}
