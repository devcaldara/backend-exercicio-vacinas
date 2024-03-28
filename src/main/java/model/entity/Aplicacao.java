package model.entity;

import java.time.LocalDate;

public class Aplicacao {
	
	private int idAplicacao;
	private int idPessoa;
	private Vacina vacina;
	private LocalDate dataAplicacao;
	private int avaliacao;
	
	public Aplicacao() {
		super();
	}

	public Aplicacao(int idAplicacao, int idPessoa, Vacina vacina, LocalDate dataAplicacao, int avaliacao) {
		super();
		this.idAplicacao = idAplicacao;
		this.idPessoa = idPessoa;
		this.vacina = vacina;
		this.dataAplicacao = dataAplicacao;
		this.avaliacao = avaliacao;
	}

	public int getIdAplicacao() {
		return idAplicacao;
	}

	public void setIdAplicacao(int idAplicacao) {
		this.idAplicacao = idAplicacao;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
	
}

