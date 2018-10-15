package br.edu.cefsa.ftt.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.cefsa.ftt.ec.util.DbUtil;
import br.edu.cefsa.ftt.ec.model.*;

public class TrabalhoDao implements Dao{
	
	private Connection connection;

	public TrabalhoDao() {
		// TODO Auto-generated constructor stub
		connection = DbUtil.getConnection();
	}
	
	public void addTrabalho(Trabalho empresa) {
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Trabalho (NOME, LOCALIZACAO) VALUES (?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, empresa.getName());
            preparedStatement.setString(2,empresa.getLocalizacao());           
            
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("TrabalhoDao: addTrabalho: " + e.getMessage()); 
        }
	}//addTrabalho
	
	public void deleteTrabalho(long id) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM Trabalho WHERE idTrabalho=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("TrabalhoDao: deleteTrabalho: " + e.getMessage()); 
        }
    } //deleteTrabalho

    public void updateTrabalho(Trabalho empresa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE Trabalho SET NOME=?, " 
                    		                          + "LOCALIZACAO=?, "                     		                         
                                               + "WHERE idTrabalho=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, empresa.getName());
            preparedStatement.setString(2, empresa.getLocalizacao());
            preparedStatement.setLong(3, empresa.getId());
            
                       
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("TrabalhoDao: updateTrabalho: " + e.getMessage()); 
        }
    } //updateEmpresa

    public List<Trabalho> getAllTrabalho() {
        
    	List<Trabalho> eL = new ArrayList<Trabalho>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Trabalho");
            while (rs.next()) {
                
            	Trabalho empresa = new Trabalho(rs.getLong("idTrabalho"),rs.getString("NOME"),rs.getString("LOCALIZACAO"));


                eL.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("TrabalhoDao: getAllTrabalho: " + e.getMessage()); 
        }

        return eL;
    } //getAllTrabalho

    public Trabalho getTrabalhoById(long id) {

    	Trabalho emp = new Trabalho();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from Trabalho WHERE idTrabalho=?");
            
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                emp.setId(rs.getLong("idEmpresa"));
                emp.setName(rs.getString("NOME"));
                emp.setLocalizacao(rs.getString("LOCALIZACAO"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            
            throw new ArithmeticException("Trabalho: getTrabalhoById: " + e.getMessage()); 
        }

        return emp;
    } //getTrabalhoById

}
