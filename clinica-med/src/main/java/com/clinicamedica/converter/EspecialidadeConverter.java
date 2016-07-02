package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.EspecialidadeDAO;
import com.clinicamedica.modelo.Especialidade;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Especialidade.class)
public class EspecialidadeConverter implements Converter {

	private EspecialidadeDAO especialidadeDAO;
	
	public EspecialidadeConverter() {
		this.especialidadeDAO = CDIServiceLocator.getBean(EspecialidadeDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Especialidade retorno = null;

		if (value != null) {
			retorno = this.especialidadeDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Especialidade) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}