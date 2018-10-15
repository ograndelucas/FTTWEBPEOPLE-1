package br.edu.cefsa.ftt.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.cefsa.ftt.ec.model.Emprego;
import br.edu.cefsa.ftt.ec.model.Trabalho;
import br.edu.cefsa.ftt.ec.util.DbUtil;

public class EmpregoDao implements Dao{

	private Connection connection;
	
	public EmpregoDao() {
		// TODO Auto-generated constructor stub
		connection = DbUtil.getConnection();
	}
	
	public void addEmprego(Emprego emprego) {
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Emprego (idPeople, idTrabalho, DATAADMISSAO) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setLong(1, emprego.getIdPeople());   
            preparedStatement.setLong(2, emprego.getIdTrabalho());
            preparedStatement.setDate(3,(java.sql.Date) emprego.getDataAdmissao());
            
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("EmpregoDao: addEmprego: " + e.getMessage()); 
        }
	}//addEmprego
	
	public void deleteEmprego(long idTrabalho, long idPeople) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM Emprego WHERE idTrabalho=? and idPeople=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, idTrabalho);
            preparedStatement.setLong(2, idPeople);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("EmpregoDao: deleteEmprego: " + e.getMessage()); 
        }
    } //deleteEmprego

    public void updateEmprego(Emprego emprego) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE Emprego SET DATAADMISSAO=?, " 
                    		                          + "DATADEMISSAO=?, "                     		                         
                                               + "WHERE idTrabalho=? and idPeople=?");
            
            // Parameters start with 1
           preparedStatement.setDate(1, (java.sql.Date)emprego.getDataAdmissao());
           preparedStatement.setDate(2, (java.sql.Date)emprego.getDataDemissao());
           preparedStatement.setLong(3, emprego.getIdTrabalho());
           preparedStatement.setLong(4, emprego.getIdPeople());
            
                       
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("EmpregoDao: updateEmprego: " + e.getMessage()); 
        }
    } //updateEmprego

    public List<Emprego> getAllEmprego() {
        
    	List<Emprego> emp = new ArrayList<Emprego>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Emprego");
            while (rs.next()) {
                
            	Emprego emprego = new Emprego(rs.getLong("idPeople"),rs.getLong("idTrabalho"),rs.getString("DATAADMISSAO"));
            	if(rs.getDate("DATADEMISSAO") != null) {
            		emprego.setDataDemissao(rs.getString("DATADEMISSAO"));
            	}

                emp.add(emprego);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("EmpregoDao: getAllEmprego: " + e.getMessage()); 
        }

        return emp;
    } //getAllEmprego

    public List<Emprego> getEmpregoByIdPeople(long id) {

    	List<Emprego> emp = new ArrayList<Emprego>();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from Emprego WHERE idPeople=?");
            
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                
            	Emprego emprego = new Emprego(rs.getLong("idPeople"),rs.getLong("idTrabalho"),rs.getString("DATAADMISSAO"));
            	if(rs.getDate("DATADEMISSAO") != null) {
            		emprego.setDataDemissao(rs.getString("DATADEMISSAO"));
            	}

                emp.add(emprego);
            }
           

            
        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("EmpregoDao: getEmpregoByIdPeople: " + e.getMessage()); 
        }

        return emp;
    } //getEmpregoByIdPeople

    public List<Emprego> getEmpregoByIdTrabalho(long id) {

    	List<Emprego> emp = new ArrayList<Emprego>();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from Emprego WHERE idTrabalho=?");
            
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                
            	Emprego emprego = new Emprego(rs.getLong("idPeople"),rs.getLong("idTrabalho"),rs.getString("DATAADMISSAO"));
            	if(rs.getDate("DATADEMISSAO") != null) {
            		emprego.setDataDemissao(rs.getString("DATADEMISSAO"));
            	}

                emp.add(emprego);
            }
           

            
        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("EmpregoDao: getEmpregoByIdTrabalho: " + e.getMessage()); 
        }

        return emp;
    } //getEmpregoByIdTrabalho

}
