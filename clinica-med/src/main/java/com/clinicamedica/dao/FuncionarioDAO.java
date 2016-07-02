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

import com.clinicamedica.modelo.Funcionario;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class FuncionarioDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Funcionario burcarPeloCodigo(Long codigo){
		return manager.find(Funcionario.class, codigo);
	}
	
	public void salvar(Funcionario funcionario){
		manager.merge(funcionario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos(){
		return manager.createQuery("from Funcionario order by nome").getResultList();
	}
	@Transactional
	public void excluir(Funcionario funcionario) throws NegocioException{
		funcionario = burcarPeloCodigo(funcionario.getCodigo());
		try{
			manager.remove(funcionario);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Funcionario não pode ser excluido!");
		}
	}	
	@SuppressWarnings("unchecked")
	public List<Funcionario> filtrados(String nome) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
			// criteria troca td pra minusculo
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
}
