package model.entity;

import java.time.LocalDate;

public class Vacinacao {
	
	private int idVacinacao;
	private int id_pessoa;
	private Vacina vacina;
	private LocalDate dataVacinacao;
	private int avaliacao;
	
	public Vacinacao(int idVacinacao, int id_pessoa, Vacina vacina, LocalDate dataVacinacao, int avaliacao) {
		super();
		this.idVacinacao = idVacinacao;
		this.id_pessoa = id_pessoa;
		this.vacina = vacina;
		this.dataVacinacao = dataVacinacao;
		this.avaliacao = avaliacao;
	}

	public Vacinacao() {
		super();
	}

	public int getIdVacinacao() {
		return idVacinacao;
	}

	public void setIdVacinacao(int idVacinacao) {
		this.idVacinacao = idVacinacao;
	}

	public int getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getDataVacinacao() {
		return dataVacinacao;
	}

	public void setDataVacinacao(LocalDate dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
	
}
