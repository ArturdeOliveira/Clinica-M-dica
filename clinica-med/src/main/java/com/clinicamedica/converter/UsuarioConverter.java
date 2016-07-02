package com.clinicamedica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.clinicamedica.dao.UsuariosDAO;
import com.clinicamedica.modelo.Usuario;
import com.clinicamedica.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	// @Inject
	private UsuariosDAO usuarios;

	public UsuarioConverter() {
		this.usuarios = CDIServiceLocator.getBean(UsuariosDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String str) {
		Usuario retorno = null;
		if (str != null) {
			Long id = new Long(str);
			retorno = this.usuarios.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if (obj != null) {
			return ((Usuario) obj).getId() == null ? "" : ((Usuario) obj).getId().toString();
		}
		return "";
	}

}
