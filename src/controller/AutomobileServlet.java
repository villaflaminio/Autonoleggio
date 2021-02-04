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

import dao.jdbc.JdbcAutomobileDAO;
import dao.jdbc.JdbcCategoriaDAO;
import dao.jpa.JpaAutomobileDAO;
import dao.jpa.JpaCategoriaDAO;
import dao.jpa.JpaNoleggioDAO;
import dao.jpa.JpaUtenteDAO;
import dataBase.DataBase;
import model.Automobile;
import model.Categoria;
import model.Utente;

@WebServlet("/AutomobileServlet")
public class AutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	JpaNoleggioDAO dbNoleggio = JpaNoleggioDAO.getInstance();
	JpaAutomobileDAO dbAutomobile = JpaAutomobileDAO.getInstance();
	
    public AutomobileServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		HttpSession session = request.getSession();
		Categoria categoria = dbCategoria.getCategoria(idCategoria);
        session.setAttribute("categoria", categoria);
    	
		
		
		List<Automobile> list = dbAutomobile.getAutomobiliPerCategoria(categoria);
		
 		request.setAttribute("list", list);
 		//request.setAttribute("idCategoria", idCategoria);

        request.getRequestDispatcher("/WEB-INF/jsp/visualizzaAuto.jsp").forward(request, response);
        
		 }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Categoria c = (Categoria)session.getAttribute("categoria");


		List<Automobile> list = dbAutomobile.getAutomobiliPerCategoria(c);

		String marcaInserita = request.getParameter("marca");
		String targaInserita = request.getParameter("targa");
		int numeroPorteInserita=0;
		if(request.getParameter("numeroPorte") != "") {numeroPorteInserita = Integer.parseInt(request.getParameter("numeroPorte"));}
		boolean prenotabile = true;
		boolean exist = dbAutomobile.existAutomobile(targaInserita);
		
		if(!marcaInserita.isBlank() && !targaInserita.isBlank() ) {
		      if(!exist) {
		        if(targaInserita.matches("(?!EE)(?!Ee)(?!eE)(?!ee)[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\d{3}[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\b")){		          
		        	Automobile a = new Automobile(marcaInserita,targaInserita,numeroPorteInserita,c,prenotabile);
		    		dbAutomobile.inserisciAutomobile(a, c);
		    		list = dbAutomobile.getAutomobiliPerCategoria(c);
		    		request.setAttribute("list", list);

	 		        request.getRequestDispatcher("/WEB-INF/jsp/visualizzaAuto.jsp").forward(request, response);	

		        }else {
					request.setAttribute("errore", "La targa non rispetta le regole italiane");

		        	System.out.println("La targa non rispetta le regole italiane");
	 				request.setAttribute("errore", "La targa non rispetta le regole italiane");		          
	 					        }
		      }else {
		    	  
					request.setAttribute("errore", "La targa inserita esiste già");
		        	System.out.println("La targa inserita esiste già");

			        request.setAttribute("errore", "La targa inserita esiste già");
			        	      }
		    }else {
				request.setAttribute("errore", "Riempire tutti i campi");

	        	System.out.println("Riempire tutti i campi");

		    	request.setAttribute("errore", "Riempire tutti i campi");
		    		    
	        }
		  

 		request.setAttribute("list", list);

        request.getRequestDispatcher("/WEB-INF/jsp/visualizzaAuto.jsp").forward(request, response);
        
       
	}
	

}
