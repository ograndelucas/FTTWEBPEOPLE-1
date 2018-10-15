package br.edu.cefsa.ftt.ec.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import br.edu.cefsa.ftt.ec.dao.EmpregoDao;
import br.edu.cefsa.ftt.ec.dao.PeopleDao;
import br.edu.cefsa.ftt.ec.model.Emprego;
import br.edu.cefsa.ftt.ec.model.People;

/**
 * Servlet implementation class EmpregoUpdate
 */
@WebServlet(name = "empregoUpdate", urlPatterns = { "/empregoUpdate" })
public class EmpregoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpregoUpdate() {
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
			Emprego emp = new Emprego(Long.valueOf(request.getParameter("pidPeople")),Long.valueOf(request.getParameter("pidTrabalho")),
					request.getParameter("pDataAdmissao"));	
			
			EmpregoDao dao = new EmpregoDao();
			dao.updateEmprego(emp);
			response.setStatus(200);
			status = "OK";
			message = "Emprego Atualizado com Sucesso!!!";
			
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
