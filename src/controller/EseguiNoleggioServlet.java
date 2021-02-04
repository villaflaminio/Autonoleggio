package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.jdbc.JdbcAutomobileDAO;
import dao.jdbc.JdbcCategoriaDAO;
import dao.jdbc.JdbcNoleggioDAO;
import dao.jdbc.JdbcUtenteDAO;
import dao.jpa.JpaAutomobileDAO;
import dao.jpa.JpaCategoriaDAO;
import dao.jpa.JpaNoleggioDAO;
import dao.jpa.JpaUtenteDAO;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/EseguiNoleggioServlet")
public class EseguiNoleggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	JpaNoleggioDAO dbNoleggio = JpaNoleggioDAO.getInstance();
	JpaAutomobileDAO dbAutomobile = JpaAutomobileDAO.getInstance();
	
    public EseguiNoleggioServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int  idNoleggio = Integer.parseInt(request.getParameter("idNoleggio"));
		HttpSession session = request.getSession();
    	Utente utente = (Utente)session.getAttribute("utente");
    	
		Noleggio n = dbNoleggio.getNoleggioById(idNoleggio);
		 // data attuale
		 Calendar calendar = Calendar.getInstance();
		 java.util.Date currentDate = calendar.getTime();	
		 java.sql.Date dateNow = new java.sql.Date(currentDate.getTime());
		 
         if(n.getDataInizio().compareTo(dateNow) > 0) {
        	 dbNoleggio.deleteNoleggio(n);

				System.out.println(" noleggio cancellato");
				request.setAttribute("errore", " noleggio cancellato");
				

         }else {
				System.out.println("non puoi annullare il noleggio, la data e' antecedente a oggi");
				request.setAttribute("errore", "non puoi annullare il noleggio, la data e' antecedente a oggi");			
				 
         }
		 
		
         	List<Noleggio> noleggiUtente = (List<Noleggio>) dbNoleggio.getNoleggiUtente(utente);					
			request.setAttribute("noleggiUtente", noleggiUtente);
			//request.setAttribute("utente",utente );
			request.getRequestDispatcher("WEB-INF/jsp/iTuoiNoleggi.jsp").forward(request, response);

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		HttpSession session = request.getSession();
    	Utente u = (Utente)session.getAttribute("utente");
    	
		//int utente = Integer.parseInt(request.getParameter("utente"));
		
		int autoDaNoleggiare = Integer.parseInt(request.getParameter("idaAutoDaNoleggiare"));
		double prezzoNoleggio = Double.parseDouble(request.getParameter("prezzoNoleggio"));
		//int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
	    

		//Utente u = dbUtente.getUtenteById(utente);  
		//Categoria c = dbCategoria.getCategoria(idCategoria);
	    Automobile a = dbAutomobile.getAutomobile(autoDaNoleggiare);
	    System.out.println ("id u: " + u.getEmail());
	    System.out.println ("prezzoNoleggio " + prezzoNoleggio);

		//conversione della data
	    Date dateInizio =Date.valueOf(dataInizio);//converting string into sql date  
	    Date dateFine =Date.valueOf(dataFine);//converting string into sql date      
	    //System.out.println ("id categor: " + idCategoria);

		
	    
	    Noleggio n = new Noleggio(u , a, dateInizio, dateFine,prezzoNoleggio);
		dbNoleggio.inserisciNoleggio(n);
		List<Noleggio> noleggiUtente = (List<Noleggio>) dbNoleggio.getNoleggiUtente(u);
			
  		request.setAttribute("noleggiUtente", noleggiUtente);
  		//request.setAttribute("utente", u);

		request.getRequestDispatcher("WEB-INF/jsp/iTuoiNoleggi.jsp").forward(request, response);

	}
	
	
}
