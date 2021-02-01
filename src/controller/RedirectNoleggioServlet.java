package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/RedirectNoleggioServlet")
public class RedirectNoleggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int idCategoria;
    String dataInizio;
    String dataFine;
    public RedirectNoleggioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		JdbcAutomobileDAO dbAutomobili = JdbcAutomobileDAO.getInstance();
		JdbcNoleggioDAO dbNoleggio = JdbcNoleggioDAO.getInstance();			
		JdbcUtenteDAO dbUtente =JdbcUtenteDAO.getInstance();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataInizio = request.getParameter("dataInizio");
		dataFine = request.getParameter("dataFine");
		int utente = Integer.parseInt(request.getParameter("utente"));
		Utente u = dbUtente.getUtenteById(utente);		
		ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(u);
		//devo controllare se le date inserite sono coerenti , se non sono vuote
		
		if(!dataInizio.isBlank()  && !dataFine.isBlank()) {
			 java.sql.Date dateInizio =java.sql.Date.valueOf(dataInizio);//converting string into sql date  
			 java.sql.Date dateFine =java.sql.Date.valueOf(dataFine);//converting string into sql date  
			 // data attuale
			 Calendar calendar = Calendar.getInstance();
			 java.util.Date currentDate = calendar.getTime();	
			 java.sql.Date dateNow = new java.sql.Date(currentDate.getTime());
			 
			if(dateFine.compareTo(dateInizio) > 0) {
		         System.out.println("Date fine e' dopo data inizio");
		         if(dateInizio.compareTo(dateNow) > 0) {
		        	Date dtInizio = null; 
	     			Date dtFine = null;
		     		 
	     			 System.out.println ("dataInizio: " + dataInizio);
	     		     System.out.println ("dataFine: " + dataFine);
		     		     
		     	   
		     		
		     		ArrayList<Automobile> automobilifree = dbNoleggio.getAutoDisponibili(dateInizio,dateFine);
		     		ArrayList<Categoria> categorieConAutoDisponibili = dbNoleggio.getCategoriaAutoDisponibili(dateInizio,dateFine);
		     		System.out.println("auto free");

		     		
		     		for(Automobile a: automobilifree) {
		     			System.out.println(a.getId());
		     		}
		     		System.out.println("categorie free");

		     		for(Categoria a: categorieConAutoDisponibili) {
		     			System.out.println(a.getId());
		     		}
		     		
		     		request.setAttribute("list", categorieConAutoDisponibili);
		      		request.setAttribute("utente", utente);

		     		request.getRequestDispatcher("WEB-INF/jsp/categoriePerNoleggio.jsp").forward(request, response);
			         
			      }else {
						request.setAttribute("errore", "la data di inizio e' minore di oggi");

						System.out.println("la data di inizio e' minore di oggi");
		 	     		request.setAttribute("noleggiUtente", noleggiUtente);
		 	     		request.setAttribute("utente", utente);
		 				request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);					
			      }
		         
		         
		      }else {
					request.setAttribute("errore", "La data di fine noleggio deve essere > di quella iniziale");

					System.out.println("La data di fine noleggio deve essere > di quella iniziale");
					request.setAttribute("noleggiUtente", noleggiUtente);
	 	     		request.setAttribute("utente", utente);
	 				request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);						
		      }
			
		}else {
			request.setAttribute("errore", "Inserire data di inizio e fine noleggio ");

			System.out.println("Inserire data di inizio e fine noleggio ");
			request.setAttribute("noleggiUtente", noleggiUtente);
     		request.setAttribute("utente", utente);
			request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);	
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		JdbcUtenteDAO dbUtente =JdbcUtenteDAO.getInstance();

		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		java.sql.Date dateInizio =java.sql.Date.valueOf(dataInizio);//converting string into sql date  
		java.sql.Date dateFine =java.sql.Date.valueOf(dataFine);//converting string into sql date  
		int utente = Integer.parseInt(request.getParameter("utente"));

		Categoria c = dbCategoria.getCategoria(idCategoria); //categoria di noleggio
		ArrayList<Automobile> autoDaNoleggiare = dbAutomobile.getAutoDisponibili(dateInizio,dateFine,idCategoria);

		double prezzoTotale = 0 ;
		int day = daysBetweenDates(dataInizio,dataFine);
	    System.out.println ("Days: " + day);

	    prezzoTotale = calcolaPrezzo(day,c);
	    System.out.println ("Prezzo: " + prezzoTotale);

	
 		request.setAttribute("autoDaNoleggiare", autoDaNoleggiare);
 		request.setAttribute("prezzoTotale", prezzoTotale); 		
 		request.setAttribute("utente", utente); 		
 		request.setAttribute("idCategoria", idCategoria); 		
 		request.setAttribute("dataInizio", dataInizio); 		
 		request.setAttribute("dataFine", dataFine); 		

		request.getRequestDispatcher("WEB-INF/jsp/listAutoPerNoleggio.jsp").forward(request, response);

	}
	


	/*
	String str="2020-12-29";  
    Date date =Date.valueOf(str);//converting string into sql date  
    System.out.println(date);  
    */
    
	
	public int daysBetweenDates(String date1, String date2) {
	    LocalDate dt1 = LocalDate.parse(date1);
	    LocalDate dt2= LocalDate.parse(date2);
	
	    long diffDays = ChronoUnit.DAYS.between(dt1, dt2);
	
	    return Math.abs((int)diffDays);
	}
	
	public double calcolaPrezzo(int day, Categoria c) {
		
		double prezzoGiornaliero = c.getPrezzoGiornaliero();
		double prezzoSettimanale = c.getPrezzoSettimanale();
		double prezzoMensile = c.getPrezzoMensile();
	    System.out.println ("prezzoGiornaliero: " + prezzoGiornaliero+"prezzoSettimanale: " + prezzoSettimanale+"prezzoMensile: " + prezzoMensile );
	
		double prezzoTotale = 0 ;
	
		while(day>0) {
			if(day >= 30) {	
			    System.out.println ("day: " + day+"prezzo: " + prezzoTotale );
	
				prezzoTotale += prezzoMensile;
				day -=30;
			}else if(day >= 7) {
			    System.out.println ("day: " + day+"prezzo: " + prezzoTotale );
	
				prezzoTotale += prezzoSettimanale;
				day -=7;
			}else if(day >= 1) {
			    System.out.println ("day: " + day+"prezzo: " + prezzoTotale );
	
				prezzoTotale += prezzoGiornaliero;
				day -=1;
			}
		}
		return prezzoTotale;
	   
	}
	
	public List<Date> datesInRange(Date dtInizio,Date dtFine ){
		List<Date> datesInRange = new ArrayList<>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(dtInizio);
	    
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(dtFine);
	
	    while (calendar.before(endCalendar)) {
	        Date result = (Date) calendar.getTime();
	        datesInRange.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    
	    return datesInRange;
	}
	
	
	
	
}
