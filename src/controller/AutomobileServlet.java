package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcAutomobileDAO;
import dao.jdbc.JdbcCategoriaDAO;
import dataBase.DataBase;
import model.Automobile;
import model.Categoria;

@WebServlet("/AutomobileServlet")
public class AutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int idCategoria;
    public AutomobileServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();

		idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		ArrayList<Automobile> list = dbAutomobile.getAutomobiliCategoria(idCategoria);
		
 		request.setAttribute("list", list);
 		request.setAttribute("idCategoria", idCategoria);

        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);
        
		 }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		ArrayList<Automobile> list = dbAutomobile.getAutomobiliCategoria(idCategoria);

		String marcaInserita = request.getParameter("marca");
		String targaInserita = request.getParameter("targa");
		int numeroPorteInserita=0;
		if(request.getParameter("numeroPorte") != "") {numeroPorteInserita = Integer.parseInt(request.getParameter("numeroPorte"));}
		boolean prenotabile = true;
		boolean exist = dbAutomobile.existAutomobile(targaInserita);
		
		if(!marcaInserita.isBlank() && !targaInserita.isBlank() ) {
		      if(!exist) {
		        if(targaInserita.matches("(?!EE)(?!Ee)(?!eE)(?!ee)[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\d{3}[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\b")){		          
		        	Automobile a = new Automobile(marcaInserita,targaInserita,numeroPorteInserita,prenotabile);
		    		Categoria c = dbCategoria.getCategoria(idCategoria);
		    		dbAutomobile.inserisciAutomobile(a, c);
		    		
		    		request.setAttribute("list", list);
	 		 		request.setAttribute("idCategoria", idCategoria);

	 		        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);	

		        }else {
					request.setAttribute("errore", "La targa non rispetta le regole italiane");

		        	System.out.println("La targa non rispetta le regole italiane");
	 				request.setAttribute("errore", "La targa non rispetta le regole italiane");		          
	 				request.setAttribute("list", list);
	 		 		request.setAttribute("idCategoria", idCategoria);

	 		        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);		        }
		      }else {
		    	  
					request.setAttribute("errore", "La targa inserita esiste già");
		        	System.out.println("La targa inserita esiste già");

			        request.setAttribute("errore", "La targa inserita esiste già");
			        request.setAttribute("list", list);
			 		request.setAttribute("idCategoria", idCategoria);
			        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);		      }
		    }else {
				request.setAttribute("errore", "Riempire tutti i campi");

	        	System.out.println("Riempire tutti i campi");

		    	request.setAttribute("errore", "Riempire tutti i campi");
		    	request.setAttribute("list", list);
		 		request.setAttribute("idCategoria", idCategoria);

		        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);		    
	        }
		  

 		request.setAttribute("list", list);
 		request.setAttribute("idCategoria", idCategoria);

        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);
        
       
	}
	

}
