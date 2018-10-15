package br.edu.cefsa.ftt.ec.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import br.edu.cefsa.ftt.ec.dao.PeopleDao;
import br.edu.cefsa.ftt.ec.dao.TrabalhoDao;
import br.edu.cefsa.ftt.ec.model.People;
import br.edu.cefsa.ftt.ec.model.Trabalho;
import br.edu.cefsa.ftt.ec.util.JsonConverter;

/**
 * Servlet implementation class TrabalhoAdd
 */
@WebServlet(name = "trabalhoAdd", urlPatterns = { "/trabalhoAdd" })
public class TrabalhoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrabalhoAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		try {
			TrabalhoDao dao = new TrabalhoDao();
			List<Trabalho> trab = dao.getAllTrabalho();
			
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJsonArray(trab, "Trabalhos");
			
			System.out.println(output);
			
			response.setStatus(200);
			response.getWriter().print(output);
			
		}catch (Exception e) {
			
			String status = "Error";
			String message = e.getMessage();	
			String now = String.valueOf(new Date());									
						
		    //create Json Object
			JsonObject json = new JsonObject();

			// put some value pairs into the JSON object .
			
			json.addProperty("Status", status);
			json.addProperty("Message", message);
			json.addProperty("Time", now);

			System.out.println(json.toString());
			
			response.setStatus(100);
			response.getWriter().print(json.toString());
	         
		}
		response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Trabalho trab = new Trabalho(request.getParameter("pName"), request.getParameter("pLocalizacao"));
		
		System.out.print(trab);
		
		TrabalhoDao dao = new TrabalhoDao();
		
		String status = "OK";
		String message = "Trabalho Adicionado com Sucesso!!!";
		String now = String.valueOf(new Date());
		
		try {
		   dao.addTrabalho(trab);
		   response.setStatus(200);
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + trab);
			e.printStackTrace();
			response.setStatus(100);
		}
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		
		
	    //create Json Object
		JsonObject json = new JsonObject();

		// put some value pairs into the JSON object .
		
		json.addProperty("Status", status);
		json.addProperty("Message", message);
		json.addProperty("Time", now);


		 response.getWriter().print(json.toString());
         response.flushBuffer();
	}

}
