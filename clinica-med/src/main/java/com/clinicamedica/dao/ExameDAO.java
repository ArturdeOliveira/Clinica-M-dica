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

import com.clinicamedica.modelo.Exame;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class ExameDAO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	//inserir exame
	public void salvar(Exame exame) {
		em.merge(exame);
	}
	
	//buscar todas as exame
	@SuppressWarnings("unchecked")
	public List<Exame> buscarTodos() {
		return em.createQuery("from Exame order by nome").getResultList();
	}
	
	//Excluir Exame
	@Transactional
	public void excluir(Exame exame) throws NegocioException {
		exame = buscarPeloCodigo(exame.getCodigo());
		try {
			em.remove(exame);
			em.flush();
		} catch (Exception e) {
			throw new NegocioException("Exame não pode ser excluído.");
		}
		
	}
	//editar exame
	public Exame buscarPeloCodigo(Long codigo) {
		return em.find(Exame.class, codigo);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Exame> filtrados(String nome) {

		Session session = this.em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Exame.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
