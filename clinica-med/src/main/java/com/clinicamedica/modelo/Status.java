package com.clinicamedica.modelo;

public enum Status {

	pendente("Pendente"),
	EmAndamento("Em andamento"),
	Desmarcado("Desmarcado"),
	Liberado("Liberado");
	
	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
