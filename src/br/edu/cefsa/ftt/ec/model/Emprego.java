package br.edu.cefsa.ftt.ec.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprego {

	long idPeople;
	long idTrabalho;
	
	Date dataAdmissao;
	Date dataDemissao;
		
	public long getIdPeople() {
		return idPeople;
	}

	public void setIdPeople(long idPeople) {
		this.idPeople = idPeople;
	}

	public long getIdTrabalho() {
		return idTrabalho;
	}

	public void setIdTrabalho(long idTrabalho) {
		this.idTrabalho = idTrabalho;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(String dataAdmissao) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

		try { 
			this.dataAdmissao = formatter.parse(dataAdmissao);
		} catch (Exception e) {
			System.err.println("Ops! Problema com a data: " + dataAdmissao);
			e.printStackTrace();
		} //try
		
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(String dataDemissao) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

		try { 
			this.dataDemissao = formatter.parse(dataDemissao);
		} catch (Exception e) {
			System.err.println("Ops! Problema com a data: " + dataDemissao);
			e.printStackTrace();
		} //try
	}

	public Emprego(long idPeople, long idTrabalho, String dataAdmissao) {
		super();
		this.idPeople = idPeople;
		this.idTrabalho = idTrabalho;
		setDataAdmissao(dataAdmissao);
	}

	public Emprego(long idPeople, long idTrabalho, String dataAdmissao, String dataDemissao) {
		super();
		this.idPeople = idPeople;
		this.idTrabalho = idTrabalho;
		setDataAdmissao(dataAdmissao);
		setDataDemissao(dataDemissao);
	}

	public Emprego() {
		// TODO Auto-generated constructor stub		
	}

}
