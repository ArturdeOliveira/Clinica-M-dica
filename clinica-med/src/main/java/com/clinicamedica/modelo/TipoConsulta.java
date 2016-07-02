package com.clinicamedica.modelo;

public enum TipoConsulta {
	
	NovaConsulta("Nova consulta"),
	Retorno("Retorno"),
	Emergencial("Emergencial"),
	Encerrada("Encerrada"),
	Cancelada("Cancelada");
	
	private String descricao;
	
	TipoConsulta(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
