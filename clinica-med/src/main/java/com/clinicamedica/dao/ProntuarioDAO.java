package com.clinicamedica.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.modelo.Prontuario;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class ProntuarioDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Prontuario buscarPeloCodigo(Long codigo) {
		return manager.find(Prontuario.class, codigo);
	}
	
	public void salvar(Prontuario prontuario) {
		manager.merge(prontuario);
	}

	@SuppressWarnings("unchecked")
	public List<Prontuario> buscarTodos() {
		return manager.createQuery("from Prontuario ").getResultList();
	}
	
	@Transactional
	public void excluir(Prontuario prontuario) throws NegocioException {
		prontuario = buscarPeloCodigo(prontuario.getCodigo());
		try {
			manager.remove(prontuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este prontuario não pode ser excluído.");
		}
	}
	
	public Prontuario buscarMedicamentos(Long codigo) {
		return manager.createNamedQuery("Prontuario.buscarProntuarioComMedicamentos", Prontuario.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}
	
	public Prontuario buscarExames(Long codigo) {
		return manager.createNamedQuery("Prontuario.buscarProntuarioComExames", Prontuario.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}
	
	public List<Prontuario> buscarPorPacienteEMedico(
			Paciente paciente, Medico medico) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Prontuario> criteriaQuery = builder.createQuery(Prontuario.class);
		Root<Prontuario> a = criteriaQuery.from(Prontuario.class);
		criteriaQuery.select(a);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (paciente != null) {
			ParameterExpression<Paciente> modeloExpression = builder.parameter(Paciente.class, "paciente");
			predicates.add(builder.equal(a.get("paciente"), modeloExpression));
		}
		
		if (medico != null) {
			ParameterExpression<Medico> modeloExpression = builder.parameter(Medico.class, "medico");
			predicates.add(builder.equal(a.get("medico"), modeloExpression));
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Prontuario> query = manager.createQuery(criteriaQuery);
		
		if (paciente != null) {
			query.setParameter("paciente", paciente);
		}
		if (medico != null) {
			query.setParameter("medico", medico);
		}
		
		return query.getResultList();
	}
	
}