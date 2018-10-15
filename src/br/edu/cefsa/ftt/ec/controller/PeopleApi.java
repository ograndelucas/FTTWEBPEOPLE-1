package br.edu.cefsa.ftt.ec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import br.edu.cefsa.ftt.ec.dao.PeopleDao;
import br.edu.cefsa.ftt.ec.model.People;
import br.edu.cefsa.ftt.ec.util.JsonConverter;

/**
 * Servlet implementation class People
 */
@WebServlet("/people")
public class PeopleApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Vítor Marques de Castro RA 082150381
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, 
	                                                          IOException {
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		try {
			PeopleDao dao = new PeopleDao();
			List<People> pessoas = dao.getAllPeople();
			
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJsonArray(pessoas, "People");
			
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
		People p = new People(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"),
				request.getParameter("email"),
        		request.getParameter("dob"),
        		request.getParameter("value"),
        		request.getParameter("color"),
        		request.getParameter("cardType"),
        		request.getParameter("gender"),
        		request.getParameter("periodM") + ";" +
        		request.getParameter("periodA") + ";" +
        		request.getParameter("periodN"));
		
		System.out.print(p);
		
		PeopleDao pd = new PeopleDao();
		
		String status = "OK";
		String message = "People data saved!";
		String now = String.valueOf(new Date());
		
		try {
		   pd.addPeople(p);
		   response.setStatus(200);
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + p);
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

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
