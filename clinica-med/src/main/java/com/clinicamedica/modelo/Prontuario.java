package com.clinicamedica.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQueries({
	@NamedQuery(name = "Prontuario.buscarProntuarioComMedicamentos", query = "select c from Prontuario c left JOIN fetch c.medicamentos "
			+ " where c.codigo = :codigo"),
	@NamedQuery(name = "Prontuario.buscarProntuarioComExames", query = "select p from Prontuario p left JOIN fetch p.exames "
			+ " where p.codigo = :codigo")
})
public class Prontuario {

	private Long codigo;
	private Medico medico;
	private Paciente paciente;
	private List<Medicamento> medicamentos;
	private List<Exame> exames;
	private Local local;
	private Status status;
	private String peso;
	private String altura;
	private String temperatura;
	private String pressao;
	private String frequencia_cardiaca;
	private String queixa;
	private String fumante;
	private String diabetico;
	private String cardiaco;
	private String lesao;
	private String medicamento;
	private String atividadeFisica;
	private String alergia;
	private String doenca;
	private String tipoSanguineo;
	private String descricao;
	private Date dataExame;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_medico")
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_paciente")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "prontuario_medicamento", joinColumns = @JoinColumn(name = "codigo_prontuario"), inverseJoinColumns = @JoinColumn(name = "codigo_medicamento"))
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "prontuario_exame", joinColumns = @JoinColumn(name = "codigo_prontuario"), inverseJoinColumns = @JoinColumn(name = "codigo_exame"))

	public List<Exame> getExames() {
		return exames;
	}
	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}
	
	@Enumerated(EnumType.STRING)
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getPressao() {
		return pressao;
	}
	public void setPressao(String pressao) {
		this.pressao = pressao;
	}
	public String getFrequencia_cardiaca() {
		return frequencia_cardiaca;
	}
	public void setFrequencia_cardiaca(String frequencia_cardiaca) {
		this.frequencia_cardiaca = frequencia_cardiaca;
	}
	
	public String getQueixa() {
		return queixa;
	}
	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}
	public String getFumante() {
		return fumante;
	}
	public void setFumante(String fumante) {
		this.fumante = fumante;
	}
	public String getDiabetico() {
		return diabetico;
	}
	public void setDiabetico(String diabetico) {
		this.diabetico = diabetico;
	}
	public String getCardiaco() {
		return cardiaco;
	}
	public void setCardiaco(String cardiaco) {
		this.cardiaco = cardiaco;
	}
	public String getLesao() {
		return lesao;
	}
	public void setLesao(String lesao) {
		this.lesao = lesao;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public String getAtividadeFisica() {
		return atividadeFisica;
	}
	public void setAtividadeFisica(String atividadeFisica) {
		this.atividadeFisica = atividadeFisica;
	}
	public String getAlergia() {
		return alergia;
	}
	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}
	public String getDoenca() {
		return doenca;
	}
	public void setDoenca(String doenca) {
		this.doenca = doenca;
	}
	
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_exame")
	public Date getDataExame() {
		return dataExame;
	}
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prontuario other = (Prontuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}