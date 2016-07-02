package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.AgendaDAO;
import com.clinicamedica.modelo.Agenda;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Agenda.class)
public class AgendaConverter implements Converter {

	private AgendaDAO agendaDAO;
	
	public AgendaConverter() {
		this.agendaDAO = CDIServiceLocator.getBean(AgendaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agenda retorno = null;

		if (value != null) {
			retorno = this.agendaDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Agenda) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}