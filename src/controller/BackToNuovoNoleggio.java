package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcCategoriaDAO;
import dao.jdbc.JdbcNoleggioDAO;
import dao.jdbc.JdbcUtenteDAO;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;


@WebServlet("/BackToNuovoNoleggio")
public class BackToNuovoNoleggio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BackToNuovoNoleggio() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcUtenteDAO dbUtente =JdbcUtenteDAO.getInstance();
		JdbcNoleggioDAO dbNoleggio = JdbcNoleggioDAO.getInstance();		
		int utente = Integer.parseInt(request.getParameter("utente"));
		Utente u = dbUtente.getUtenteById(utente);

		ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(u);
			
  		request.setAttribute("noleggiUtente", noleggiUtente);

  		request.setAttribute("utente", u.getId());
		request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);
			
			
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcUtenteDAO dbUtente =JdbcUtenteDAO.getInstance();
		JdbcNoleggioDAO dbNoleggio = JdbcNoleggioDAO.getInstance();		
		int utente = Integer.parseInt(request.getParameter("utente"));
		Utente u = dbUtente.getUtenteById(utente);
		
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
		ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(u);

      		Date dtInizio = null; 
 			Date dtFine = null;
 			java.sql.Date dateInizio =java.sql.Date.valueOf(dataInizio);//converting string into sql date  
 			java.sql.Date dateFine =java.sql.Date.valueOf(dataFine);//converting string into sql date  
 			 System.out.println ("dataInizio: " + dataInizio);
 		     System.out.println ("dataFine: " + dataFine);
     		     
     	   
     		
     		ArrayList<Automobile> automobilifree = dbNoleggio.getAutoDisponibili(dateInizio,dateFine);
     		ArrayList<Categoria> categorieConAutoDisponibili = dbNoleggio.getCategoriaAutoDisponibili(dateInizio,dateFine);
     		
    		
     		request.setAttribute("list", categorieConAutoDisponibili);
      		request.setAttribute("utente", u.getId());
      		request.setAttribute("dataInizio", dataInizio);
      		request.setAttribute("dataFine", dataFine);

     		request.getRequestDispatcher("WEB-INF/jsp/categoriePerNoleggio.jsp").forward(request, response);
	}

}
