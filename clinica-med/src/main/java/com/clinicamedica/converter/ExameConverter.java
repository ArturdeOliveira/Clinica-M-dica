package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.ExameDAO;
import com.clinicamedica.modelo.Exame;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Exame.class, value="exameConverter")
public class ExameConverter implements Converter {

	private ExameDAO exameDAO;
	
	public ExameConverter() {
		this.exameDAO = CDIServiceLocator.getBean(ExameDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Exame retorno = null;

		if (value != null) {
			retorno = this.exameDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Exame) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}
}