package com.clinicamedica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.clinicamedica.modelo.Especialidade;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class EspecialidadeDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	//inserir especialidade
	public void salvar(Especialidade esp) {
		em.merge(esp);
	}
	
	//buscar todas as especialidades
	@SuppressWarnings("unchecked")
	public List<Especialidade> buscarTodos() {
		return em.createQuery("from Especialidade order by nome").getResultList();
	}
	
	//Excluir especialidade
	@Transactional
	public void excluir(Especialidade esp) throws NegocioException {
		Especialidade espTemp = em.find(Especialidade.class, esp.getCodigo());
		try {
			em.remove(espTemp);
			em.flush();
		} catch (Exception e) {
			throw new NegocioException("Especialidade não pode ser excluído.");
		}
		
	}
	//editar especialidade
	public Especialidade buscarPeloCodigo(Long codigo) {
		return em.find(Especialidade.class, codigo);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Especialidade> filtrados(String nome) {

		Session session = this.em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Especialidade.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
}