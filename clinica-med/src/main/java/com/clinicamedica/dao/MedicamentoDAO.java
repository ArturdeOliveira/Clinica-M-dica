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

import com.clinicamedica.modelo.Medicamento;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class MedicamentoDAO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	//inserir Medicamento
	public void salvar(Medicamento me) {
		em.merge(me);
	}
	
	//buscar todas as Medicamento
	@SuppressWarnings("unchecked")
	public List<Medicamento> buscarTodos() {
		return em.createQuery("from Medicamento order by nome").getResultList();
	}
	
	//Excluir medicamento
	@Transactional
	public void excluir(Medicamento me) throws NegocioException {
		me = buscarPeloCodigo(me.getCodigo());
		try {
			em.remove(me);
			em.flush();
		} catch (Exception e) {
			throw new NegocioException("Medicamento não pode ser excluído.");
		}
		
	}
	//editar medicamento
	public Medicamento buscarPeloCodigo(Long codigo) {
		return em.find(Medicamento.class, codigo);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Medicamento> filtrados(String nome) {

		Session session = this.em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Medicamento.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
}
