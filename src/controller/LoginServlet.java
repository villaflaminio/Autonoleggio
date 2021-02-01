package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoginServlet")




public class LoginServlet extends javax.servlet.http.HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);

	
	}
	
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		JdbcUtenteDAO dbUtente =JdbcUtenteDAO.getInstance();
		JdbcNoleggioDAO dbNoleggio = JdbcNoleggioDAO.getInstance();			

        String userInserito = request.getParameter("username");
        String passwordInserita = request.getParameter("password");
        ServletContext context = getServletContext();
        String userAdmin = context.getInitParameter("userAdmin");
        String passwordAdmin = context.getInitParameter("passwordAdmin");
        
        
        System.out.println(userInserito + " " + userAdmin);
        System.out.println(passwordInserita + " " + passwordAdmin);
        if (userInserito.equals(userAdmin) && passwordInserita.equals(passwordAdmin)) {
        	    		
    		ArrayList<Categoria> list = dbCategoria.getListCategoria();

     		request.setAttribute("list", list);
    		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
        }else {
 			Utente utente = dbUtente.getUtente(userInserito, passwordInserita);
 			if (utente != null) {
 				System.out.println("Login utente");
 				
 				ArrayList<Noleggio> noleggiUtente = (ArrayList<Noleggio>) dbNoleggio.getNoleggiUtente(utente);
 				
 	     		request.setAttribute("noleggiUtente", noleggiUtente);

 	     		request.setAttribute("utente", utente.getId());
 				request.getRequestDispatcher("WEB-INF/jsp/inserisciDate.jsp").forward(request, response);
 			} else {
 				request.setAttribute("errore","Credenziali errate, riprovare" );
				System.out.println("Credenziali errate, riprovare");
 				request.getRequestDispatcher("login.jsp").forward(request, response);
 			}
 		}
        
    }

}
