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

/**
 * Servlet implementation class PeopleDelete
 */
@WebServlet(name = "peopleDelete", urlPatterns = { "/peopleDelete" })
public class PeopleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleDelete() {
        super();
        // TODO Auto-generated constructor stub
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
			long id = Long.valueOf(request.getParameter("pid"));
			PeopleDao dao = new PeopleDao();
			dao.deletePeople(id);
			status = "OK";
			message = "Deletado com Sucesso";
		}catch(Exception e) {
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
