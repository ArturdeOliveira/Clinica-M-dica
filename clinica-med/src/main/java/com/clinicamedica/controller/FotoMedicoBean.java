package com.clinicamedica.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.clinicamedica.modelo.Medico;

@Named
@SessionScoped
public class FotoMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Medico medicoSelecionado;
	
	public StreamedContent getFoto() {
		DefaultStreamedContent content = null;
		if (this.medicoSelecionado != null && this.medicoSelecionado.getFoto() != null
				&& this.medicoSelecionado.getFoto().length > 0) {
			byte[] imagem = this.medicoSelecionado.getFoto();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpg", "medico.jpg");
		}
		
		return content;
	}

	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}

	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}

}
