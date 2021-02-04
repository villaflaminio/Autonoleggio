package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.jdbc.JdbcCategoriaDAO;
import dao.jdbc.JdbcNoleggioDAO;
import dao.jdbc.JdbcUtenteDAO;
import dao.jpa.JpaCategoriaDAO;
import dao.jpa.JpaNoleggioDAO;
import dao.jpa.JpaUtenteDAO;
import model.Noleggio;
import model.Utente;

/**
 * Servlet implementation class ITuoiNoleggiServlet
 */
@WebServlet("/ITuoiNoleggiServlet")
public class ITuoiNoleggiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	JpaNoleggioDAO dbNoleggio = JpaNoleggioDAO.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ITuoiNoleggiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	Utente utente = (Utente)session.getAttribute("utente");
    	
		List<Noleggio> noleggiUtente =  dbNoleggio.getNoleggiUtente(utente);

  		request.setAttribute("noleggiUtente", noleggiUtente);
  		//request.setAttribute("utente", utente);

		request.getRequestDispatcher("WEB-INF/jsp/iTuoiNoleggi.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
