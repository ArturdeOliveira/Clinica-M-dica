package com.clinicamedica.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.clinicamedica.modelo.Paciente;

@Named
@SessionScoped
public class FotoPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paciente pacienteSelecionado;
	
	public StreamedContent getFoto() {
		DefaultStreamedContent content = null;
		if (this.pacienteSelecionado != null && this.pacienteSelecionado.getFoto() != null
				&& this.pacienteSelecionado.getFoto().length > 0) {
			byte[] imagem = this.pacienteSelecionado.getFoto();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpg", "paciente.jpg");
		}
		
		return content;
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

}
