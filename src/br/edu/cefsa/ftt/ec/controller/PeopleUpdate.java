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
import br.edu.cefsa.ftt.ec.model.People;
import br.edu.cefsa.ftt.ec.util.JsonConverter;

/**
 * Servlet implementation class PeopleUpdate
 */
@WebServlet(name = "peopleUpdate", urlPatterns = { "/peopleUpdate" })
public class PeopleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	resp.setContentType("application/json"); //MIME Type
		resp.setCharacterEncoding("utf-8");
    	try {
    		long id = Long.valueOf(req.getParameter("pid"));
    		PeopleDao dao = new PeopleDao();
    		People p = dao.getUserById(id);
    		JsonConverter convert = new JsonConverter();
    		String output = convert.ConvertPeopleToJson(p);
    		
    		System.out.println(output);
			
			resp.setStatus(200);
			resp.getWriter().print(output);
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
			
			resp.setStatus(100);
			resp.getWriter().print(json.toString());
    	}
    	resp.flushBuffer();
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
			People p = new People(
					request.getParameter("pid"), 
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
			
			PeopleDao dao = new PeopleDao();
			dao.updatePeople(p);
			response.setStatus(200);
			status = "OK";
			message = "Pessoa Atualizada com Sucesso!!!";
			
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
