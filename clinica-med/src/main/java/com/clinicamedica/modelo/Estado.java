package com.clinicamedica.modelo;

public enum Estado {
	

	Ac("AC"),
	Al("AL"),
	Ap("AP"),
	Am("AM"),
	Bh("BH"),
	Ce("CE"),
	Df("DF"),
	Es("ES"),
	Go("GO"),
	Ma("MA"),
	Mg("MG"),
	Ms("MS"),
	Pa("PA"),
	Pb("PB"),
	Pr("PR"),
	Pe("PE"),
	Pi("PI"),
	Rj("RJ"),
	Rn("RN"),
	Rs("RS"),
	Ro("RO"),
	Rr("RR"),
	Sc("SC"),
	Sp("SP"),
	Se("SE"),
	To("TO");
	
	private String descricao;
	
	Estado(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}