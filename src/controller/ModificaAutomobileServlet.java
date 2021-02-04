package controller;

import java.io.IOException;
import java.util.ArrayList;

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
import model.Automobile;
import model.Categoria;
import model.Utente;


@WebServlet("/ModificaAutomobileServlet")
public class ModificaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	JpaNoleggioDAO dbNoleggio = JpaNoleggioDAO.getInstance();
	JpaAutomobileDAO dbAutomobile = JpaAutomobileDAO.getInstance();
	

    public ModificaAutomobileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAuto = Integer.parseInt(request.getParameter("idAuto"));

		Automobile autoDaModificare = dbAutomobile.getAutomobile(idAuto);

 		request.setAttribute("autoDaModificare", autoDaModificare);
		request.getRequestDispatcher("WEB-INF/jsp/modificaAuto.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	Categoria c = (Categoria)session.getAttribute("categoria");
    	
		String marcaInserita = request.getParameter("marca");
		String targaInserita = request.getParameter("targa");
		int numeroPorteInserita=0;
		if(request.getParameter("numeroPorte") != "") {
		 numeroPorteInserita = Integer.parseInt(request.getParameter("numeroPorte"));}
		boolean prenotabile = true;
		int id = Integer.parseInt(request.getParameter("idAuto"));
		
		if(!marcaInserita.isBlank() && !targaInserita.isBlank() ) {
		     
		        if(targaInserita.matches("(?!EE)(?!Ee)(?!eE)(?!ee)[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\d{3}[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\b")){
		        	Automobile a = new Automobile(id,marcaInserita,targaInserita,numeroPorteInserita,c ,true);
		        	
		    		dbAutomobile.inserisciAutomobile(a, c);

		        }else {
	 				request.setAttribute("errore", "La targa non rispetta le regole italiane");		          
	 					        
	        	}
		}else {
		        request.setAttribute("errore", "Riempire tutti i campi");
		        
		}

		int idAuto = Integer.parseInt(request.getParameter("idAuto"));

		Automobile autoDaModificare = dbAutomobile.getAutomobile(idAuto);

 		request.setAttribute("autoDaModificare", autoDaModificare);
		request.getRequestDispatcher("WEB-INF/jsp/modificaAuto.jsp").forward(request, response);	
	
		
	}
}
