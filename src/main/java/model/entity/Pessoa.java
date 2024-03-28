package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String sexo;
	private String cpf;
	private int categoria;
	private Pais pais;
	private LocalDate dataNascimento;
	//	private ArrayList<Vacinacao> vacinacoes;

	public Pessoa() {
		super();
	}
	public Pessoa(int idPessoa, String nome, String sexo, String cpf, int categoria, Pais pais,
			LocalDate dataNascimento) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.categoria = categoria;
		this.pais = pais;
		this.dataNascimento = dataNascimento;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	

}
