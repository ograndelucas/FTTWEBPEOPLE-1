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

import br.edu.cefsa.ftt.ec.dao.EmpregoDao;
import br.edu.cefsa.ftt.ec.model.Emprego;
import br.edu.cefsa.ftt.ec.util.JsonConverter;

/**
 * Servlet implementation class EmpregoFilterByIdPeople
 */
@WebServlet(name = "empregoFilterByIdPeople", urlPatterns = { "/empregoFilterByIdPeople" })
public class EmpregoFilterByIdPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpregoFilterByIdPeople() {
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
			EmpregoDao dao = new EmpregoDao();
			long id = Long.valueOf(request.getParameter("pidPeople"));
			List<Emprego> emprega = dao.getEmpregoByIdPeople(id);
			
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJsonArray(emprega, "Empregos");
			
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

}
