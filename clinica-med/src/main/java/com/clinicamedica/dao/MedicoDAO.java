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

import com.clinicamedica.modelo.Medico;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class MedicoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Medico burcarPeloCodigo(Long codigo){
		return manager.find(Medico.class, codigo);
	}
	
	public void salvar(Medico medico){
		manager.merge(medico);
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> buscarTodos(){
		return manager.createQuery("from Medico order by nome").getResultList();
	}
	@Transactional
	public void excluir(Medico medico) throws NegocioException{
		medico = burcarPeloCodigo(medico.getCodigo());
		try{
			manager.remove(medico);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Medico não pode ser excluido!");
		}
	}
	@SuppressWarnings("unchecked")
	public List<Medico> filtrados(String nome) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Medico.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
