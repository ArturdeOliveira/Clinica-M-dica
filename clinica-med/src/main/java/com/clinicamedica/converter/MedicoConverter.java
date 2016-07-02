package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.MedicoDAO;
import com.clinicamedica.modelo.Medico;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Medico.class)
public class MedicoConverter implements Converter {

	private MedicoDAO medicoDAO;
	
	public MedicoConverter() {
		this.medicoDAO = CDIServiceLocator.getBean(MedicoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Medico retorno = null;

		if (value != null) {
			retorno = this.medicoDAO.burcarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Medico) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}