package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcCategoriaDAO;
import dao.jdbc.JdbcUtenteDAO;
import dao.jpa.JpaCategoriaDAO;
import dao.jpa.JpaUtenteDAO;
import model.Utente;


@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	
	
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);		    

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String emailInserito = request.getParameter("inputEmail");
        String passwordInserita = request.getParameter("inputPassword");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		
		SimpleDateFormat  dtf = new SimpleDateFormat("yyyy-MM-dd");;

		String stringDataNascita = request.getParameter("dataNascita");
		System.out.println();

		if(dbUtente.existUtente(emailInserito)==false) {
			if(!emailInserito.isBlank()		
				&& !passwordInserita.isBlank()&& !nome.isBlank() 
				&& !cognome.isBlank()&& !stringDataNascita.isBlank() ) {
				//verifico se l'utente e' maggiorenne
			    java.sql.Date dateNascitaSQL =java.sql.Date.valueOf(stringDataNascita);//converting string into sql date  
	
				Date dateNascita = null;
				try {
					dateNascita = dtf.parse(stringDataNascita);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("tutti i dati sono inseriti");
	
				GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
				   cal.setTime(dateNascita);
				if(maggiorenne(cal)) {
					Utente u = new Utente(nome,cognome,emailInserito,passwordInserita,dateNascitaSQL);
					dbUtente.inserisciUtente(u);
					request.setAttribute("errore", "registrazione effettuata");

					System.out.println("registrazione effettuata");
	
					//registrazione effettuata
					System.out.println(emailInserito + " " + passwordInserita+ " " + nome+ " " + cognome + " " +stringDataNascita);
			        request.getRequestDispatcher("login.jsp").forward(request, response);
					
				}else {
					request.setAttribute("errore", "utente minorenne");

					System.out.println("utente minorenne");

				}
			
			}else {
				request.setAttribute("errore","mancano dati");

			System.out.println("mancano dati");

			}
		}else {
			request.setAttribute("errore","L' utente esiste gia' !");

			System.out.println("L' utente esiste gia' !");

		}
		
		
        request.getRequestDispatcher("login.jsp").forward(request, response);

	}
	
	public static boolean maggiorenne(GregorianCalendar datanascita)
	{
	boolean mag=false;

	GregorianCalendar now=new GregorianCalendar();

	int giorno=datanascita.get(Calendar.DAY_OF_MONTH);
	int mese=datanascita.get(Calendar.MONTH);
	int anno=datanascita.get(Calendar.YEAR)+18;
	GregorianCalendar mageta= new GregorianCalendar(anno,mese,giorno,0,0,0);
	mageta.set(Calendar.MILLISECOND, 0);
	long dif=now.getTimeInMillis()-mageta.getTimeInMillis();

	return dif > 0;

	}

	
		
}
