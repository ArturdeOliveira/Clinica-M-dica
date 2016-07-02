package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.ProntuarioDAO;
import com.clinicamedica.modelo.Prontuario;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Prontuario.class)
public class ProntuarioConverter implements Converter {

	private ProntuarioDAO prontuarioDAO;
	
	public ProntuarioConverter() {
		this.prontuarioDAO = CDIServiceLocator.getBean(ProntuarioDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Prontuario retorno = null;

		if (value != null) {
			retorno = this.prontuarioDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Prontuario) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		return "";
	}

}