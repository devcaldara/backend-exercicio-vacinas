package model.entity;

import java.time.LocalDate;

public class Vacina {
	
	private int idVacina;
	private String nome;
	//	private String paisOrigem;
	private Pais pais;
	private Pessoa pesquisadorResponsavel;
	private LocalDate dataInicioPesquisa;
	private int estagio;
	private double mediaAvaliacao;
	
	public Vacina() {
		super();
	}

	public Vacina(int idVacina, String nome, Pais pais, Pessoa pesquisadorResponsavel, LocalDate dataInicioPesquisa,
			int estagio, double mediaAvaliacao) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.pais = pais;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.estagio = estagio;
		this.mediaAvaliacao = mediaAvaliacao;
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public int getEstagio() {
		return estagio;
	}

	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}

	public double getMediaAvaliacao() {
		return mediaAvaliacao;
	}

	public void setMediaAvaliacao(double mediaAvaliacao) {
		this.mediaAvaliacao = mediaAvaliacao;
	}
	
}
