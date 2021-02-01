package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcAutomobileDAO;
import dao.jdbc.JdbcCategoriaDAO;
import dao.jdbc.JdbcNoleggioDAO;
import dao.jdbc.JdbcUtenteDAO;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
       
  
    public EseguiNoleggioServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcNoleggioDAO dbNoleggio = JdbcNoleggioDAO.getInstance();			
		int  idNoleggio = Integer.parseInt(request.getParameter("idNoleggio"));
		Noleggio n = dbNoleggio.getNoleggioById(idNoleggio);
		 // data attuale
		 Calendar calendar = Calendar.getInstance();
		 java.util.Date currentDate = calendar.getTime();	
		 java.sql.Date dateNow = new java.sql.Date(currentDate.getTime());
		 
         if(n.getDataInizio().compareTo(dateNow) > 0) {
        	 dbNoleggio.deleteNoleggio(idNoleggio);
        	 System.out.println(" noleggio cancellato");
				request.setAttribute("errore", " noleggio cancellato");

        	 ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(n.getUtente());
				
     		request.setAttribute("noleggiUtente", noleggiUtente);

     		request.setAttribute("utente", n.getUtente().getId());
			request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);

         }else {
				System.out.println("non puoi annullare il noleggio, la data e' antecedente a oggi");
				request.setAttribute("errore", "non puoi annullare il noleggio, la data e' antecedente a oggi");

				ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(n.getUtente());
					
				request.setAttribute("noleggiUtente", noleggiUtente);
				request.setAttribute("utente", n.getUtente().getId());
				request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);
				 
         }
		 
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcNoleggioDAO dbNoleggio = JdbcNoleggioDAO.getInstance();			
		JdbcUtenteDAO dbUtente = JdbcUtenteDAO.getInstance();
		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		int utente = Integer.parseInt(request.getParameter("utente"));
		int autoDaNoleggiare = Integer.parseInt(request.getParameter("idaAutoDaNoleggiare"));
		double prezzoNoleggio = Double.parseDouble(request.getParameter("prezzoNoleggio"));
		//int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
	    

		Utente u = dbUtente.getUtenteById(utente);  
		//Categoria c = dbCategoria.getCategoria(idCategoria);
	    Automobile a = dbAutomobile.getAutomobileById(autoDaNoleggiare);
	    System.out.println ("id u: " + u.getEmail());
	    System.out.println ("id utente: " + utente);
	    System.out.println ("prezzoNoleggio " + prezzoNoleggio);

		//conversione della data
	    Date dateInizio =Date.valueOf(dataInizio);//converting string into sql date  
	    Date dateFine =Date.valueOf(dataFine);//converting string into sql date      
	    //System.out.println ("id categor: " + idCategoria);

		
	    
	    Noleggio n = new Noleggio(u , a, dateInizio, dateFine,prezzoNoleggio);
		dbNoleggio.inserisciNoleggio(n);
		ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(u);
			
  		request.setAttribute("noleggiUtente", noleggiUtente);
  		request.setAttribute("utente", utente);

		request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);

	}
	
	
}
