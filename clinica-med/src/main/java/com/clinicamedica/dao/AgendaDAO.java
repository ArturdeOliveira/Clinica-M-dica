package com.clinicamedica.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.clinicamedica.modelo.Agenda;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.service.NegocioException;
import com.clinicamedica.util.jpa.Transactional;

public class AgendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Agenda agenda) {
		manager.merge(agenda);
	}
	
	@Transactional
	public void excluir(Agenda agenda) throws NegocioException {
		Agenda espTemp = manager.find(Agenda.class, agenda.getCodigo());
		try {
			manager.remove(espTemp);
			manager.flush();
		} catch (Exception e) {
			throw new NegocioException("Consulta não pode ser excluída.");
		}
		
	}
	//editar agenda
	public Agenda buscarPeloCodigo(Long codigo) {
		return manager.find(Agenda.class, codigo);
	}	

	public List<Agenda> buscarPorDataConsultaEMedico(Date dataConsulta,
			Paciente paciente, Medico medico) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Agenda> criteriaQuery = builder.createQuery(Agenda.class);
		Root<Agenda> a = criteriaQuery.from(Agenda.class);
		criteriaQuery.select(a);
		
		List<Predicate> predicates = new ArrayList<>();
		if (dataConsulta != null) {
			ParameterExpression<Date> dataConsultaInicial = builder.parameter(Date.class, "dataConsultaInicial");
			ParameterExpression<Date> dataConsultaFinal = builder.parameter(Date.class, "dataConsultaFinal");
			predicates.add(builder.between(a.<Date>get("dataConsulta"), dataConsultaInicial, dataConsultaFinal));
		}
		
		if (paciente != null) {
			ParameterExpression<Paciente> modeloExpression = builder.parameter(Paciente.class, "paciente");
			predicates.add(builder.equal(a.get("paciente"), modeloExpression));
		}
		
		if (medico != null) {
			ParameterExpression<Medico> modeloExpression = builder.parameter(Medico.class, "medico");
			predicates.add(builder.equal(a.get("medico"), modeloExpression));
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Agenda> query = manager.createQuery(criteriaQuery);
		
		if (dataConsulta != null) {
			Calendar dataConsultaInicial = Calendar.getInstance();
			dataConsultaInicial.setTime(dataConsulta);
			dataConsultaInicial.set(Calendar.HOUR_OF_DAY, 0);
			dataConsultaInicial.set(Calendar.MINUTE, 0);
			dataConsultaInicial.set(Calendar.SECOND, 0);
			
			Calendar dataConsultaFinal = Calendar.getInstance();
			dataConsultaFinal.setTime(dataConsulta);
			dataConsultaFinal.set(Calendar.HOUR_OF_DAY, 23);
			dataConsultaFinal.set(Calendar.MINUTE, 59);
			dataConsultaFinal.set(Calendar.SECOND, 59);
			
			query.setParameter("dataConsultaInicial", dataConsultaInicial.getTime());
			query.setParameter("dataConsultaFinal", dataConsultaFinal.getTime());
		}
		
		if (paciente != null) {
			query.setParameter("paciente", paciente);
		}
		if (medico != null) {
			query.setParameter("medico", medico);
		}
		
		return query.getResultList();
	}

}