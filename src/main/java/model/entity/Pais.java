package model.entity;

public class Pais {
	
	private int idPais;
	private String nomePais;
	private String siglaPais;
	
	public Pais(int idPais, String nomePais, String siglaPais) {
		super();
		this.idPais = idPais;
		this.nomePais = nomePais;
		this.siglaPais = siglaPais;
	}

	public Pais() {
		super();
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getSiglaPais() {
		return siglaPais;
	}

	public void setSiglaPais(String siglaPais) {
		this.siglaPais = siglaPais;
	}
	
	
	
}
