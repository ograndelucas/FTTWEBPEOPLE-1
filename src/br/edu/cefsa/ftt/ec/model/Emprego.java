package br.edu.cefsa.ftt.ec.model;

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

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public Emprego(long idPeople, long idTrabalho, Date dataAdmissao) {
		super();
		this.idPeople = idPeople;
		this.idTrabalho = idTrabalho;
		this.dataAdmissao = dataAdmissao;
	}

	public Emprego(long idPeople, long idTrabalho, Date dataAdmissao, Date dataDemissao) {
		super();
		this.idPeople = idPeople;
		this.idTrabalho = idTrabalho;
		this.dataAdmissao = dataAdmissao;
		this.dataDemissao = dataDemissao;
	}

	public Emprego() {
		// TODO Auto-generated constructor stub		
	}

}
