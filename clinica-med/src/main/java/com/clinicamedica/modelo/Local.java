package com.clinicamedica.modelo;

public enum Local {
	
	LaboratorioA("Laboratório A"),
	LaboratorioB("Laboratório B"),
	LaboratorioC("Laboratório C"),
	LaboratorioD("Laboratório D");
	
	private String descricao;
	
	Local(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
