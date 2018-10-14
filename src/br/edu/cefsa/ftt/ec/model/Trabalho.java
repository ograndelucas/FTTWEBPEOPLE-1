package br.edu.cefsa.ftt.ec.model;

import java.util.Date;

public class Trabalho {
	
	long id;
	String Name;
	String Localizacao;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLocalizacao() {
		return Localizacao;
	}

	public void setLocalizacao(String localizacao) {
		Localizacao = localizacao;
	}

	public Trabalho(String name, String localizacao) {
		super();
		Name = name;
		Localizacao = localizacao;
	}

	public Trabalho(long id, String name, String localizacao) {
		super();
		this.id = id;
		Name = name;
		Localizacao = localizacao;
	}

	public Trabalho() {
		// TODO Auto-generated constructor stub
	}

}
