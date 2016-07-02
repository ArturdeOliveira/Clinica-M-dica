package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.PacienteDAO;
import com.clinicamedica.modelo.Paciente;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Paciente.class)
public class PacienteConverter implements Converter {

	private PacienteDAO pacienteDAO;
	
	public PacienteConverter() {
		this.pacienteDAO = CDIServiceLocator.getBean(PacienteDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Paciente retorno = null;

		if (value != null) {
			retorno = this.pacienteDAO.burcarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Paciente) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}