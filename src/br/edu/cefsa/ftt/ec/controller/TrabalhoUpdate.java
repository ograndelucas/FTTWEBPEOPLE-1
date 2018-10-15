package br.edu.cefsa.ftt.ec.controller;

import java.io.IOException;
import java.util.Date;

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
 * Servlet implementation class TrabalhoUpdate
 */
@WebServlet(name = "trabalhoUpdate", urlPatterns = { "/trabalhoUpdate" })
public class TrabalhoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrabalhoUpdate() {
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
    	try {
    		long id = Long.valueOf(request.getParameter("pid"));
    		TrabalhoDao dao = new TrabalhoDao();
    		Trabalho trab = dao.getTrabalhoById(id);
    		JsonConverter convert = new JsonConverter();
    		String output = convert.ConveretTrabalhoToJson(trab);
    		
    		System.out.println(output);
			
			response.setStatus(200);
			response.getWriter().print(output);
    	}catch(Exception e) {
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
		String status = "";
		String message = "";
		String now = String.valueOf(new Date());
		try {
			Trabalho trab = new Trabalho(request.getParameter("pName"), request.getParameter("pLocalizacao"));
			TrabalhoDao dao = new TrabalhoDao();
			dao.updateTrabalho(trab);
			response.setStatus(200);
			status = "OK";
			message = "Trabalho Atualizado com Sucesso!!!";
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			status = "Error";
			response.setStatus(100);
			message = e.getMessage();
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
