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
import model.Automobile;
import model.Categoria;


@WebServlet("/ModificaAutomobileServlet")
public class ModificaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int idCategoria;

    public ModificaAutomobileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
		int idAuto = Integer.parseInt(request.getParameter("idAuto"));
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

		Automobile autoDaModificare = dbAutomobile.getAutomobileById(idAuto);

 		request.setAttribute("autoDaModificare", autoDaModificare);
		request.getRequestDispatcher("WEB-INF/jsp/modificaAuto.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();

		String marcaInserita = request.getParameter("marca");
		String targaInserita = request.getParameter("targa");
		int numeroPorteInserita=0;
		if(request.getParameter("numeroPorte") != "") {
		 numeroPorteInserita = Integer.parseInt(request.getParameter("numeroPorte"));}
		boolean prenotabile = true;
		int id = Integer.parseInt(request.getParameter("idAuto"));
		
		if(!marcaInserita.isBlank() && !targaInserita.isBlank() ) {
		     
		        if(targaInserita.matches("(?!EE)(?!Ee)(?!eE)(?!ee)[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\d{3}[A-HJ-NPR-TV-Za-hj-npr-tv-z]{2}\\b")){		          
		        	
		    		dbAutomobile.modificaAutomobile(id,targaInserita,marcaInserita,numeroPorteInserita);

		        }else {
	 				request.setAttribute("errore", "La targa non rispetta le regole italiane");		          
	 				int idAuto = Integer.parseInt(request.getParameter("idAuto"));
	 				int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

	 				Automobile autoDaModificare = dbAutomobile.getAutomobileById(idAuto);

	 		 		request.setAttribute("autoDaModificare", autoDaModificare);
	 				request.getRequestDispatcher("WEB-INF/jsp/modificaAuto.jsp").forward(request, response);	        
	        	}
		}else {
		        request.setAttribute("errore", "Riempire tutti i campi");
		        int idAuto = Integer.parseInt(request.getParameter("idAuto"));
				int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

				Automobile autoDaModificare = dbAutomobile.getAutomobileById(idAuto);

		 		request.setAttribute("autoDaModificare", autoDaModificare);
				request.getRequestDispatcher("WEB-INF/jsp/modificaAuto.jsp").forward(request, response);	      
		    
		}

		int idAuto = Integer.parseInt(request.getParameter("idAuto"));
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

		Automobile autoDaModificare = dbAutomobile.getAutomobileById(idAuto);

 		request.setAttribute("autoDaModificare", autoDaModificare);
		request.getRequestDispatcher("WEB-INF/jsp/modificaAuto.jsp").forward(request, response);	
	
		
	}
}
