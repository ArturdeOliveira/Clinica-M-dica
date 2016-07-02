package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.MedicamentoDAO;
import com.clinicamedica.modelo.Medicamento;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Medicamento.class, value="medicamentoConverter")
public class MedicamentoConverter implements Converter {

	private MedicamentoDAO medicamentoDAO;
	
	public MedicamentoConverter() {
		this.medicamentoDAO = CDIServiceLocator.getBean(MedicamentoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Medicamento retorno = null;

		if (value != null) {
			retorno = this.medicamentoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Medicamento) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}
}