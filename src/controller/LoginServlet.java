package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.jdbc.JdbcAutomobileDAO;
import dao.jdbc.JdbcCategoriaDAO;
import dao.jdbc.JdbcNoleggioDAO;
import dao.jdbc.JdbcUtenteDAO;
import dao.jpa.JpaCategoriaDAO;
import dao.jpa.JpaUtenteDAO;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoginServlet")




public class LoginServlet extends javax.servlet.http.HttpServlet {
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	session.invalidate();
    	
        request.getRequestDispatcher("login.jsp").forward(request, response);

	
	}
	
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		
        String userInserito = request.getParameter("username");
        String passwordInserita = request.getParameter("password");
        ServletContext context = getServletContext();
        String userAdmin = context.getInitParameter("userAdmin");
        String passwordAdmin = context.getInitParameter("passwordAdmin");

        
        System.out.println(userInserito + " " + userAdmin);
        System.out.println(passwordInserita + " " + passwordAdmin);
        if (userInserito.equals(userAdmin) && passwordInserita.equals(passwordAdmin)) {
        	// AMIN LOGGATO 
	        HttpSession session = request.getSession();
		        session.setAttribute("userInserito", userInserito);
 		        session.setAttribute("passwordInserita", passwordInserita);

        	List<Categoria> list = dbCategoria.getListCategoria();

     		request.setAttribute("list", list);
    		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
        }else {
 			Utente utente = dbUtente.getUtente(userInserito, passwordInserita);
 			if (utente != null) {
 				// UTENTE LOGGATO
 				System.out.println("Login utente");
 		        HttpSession session = request.getSession();

 		        session.setAttribute("utente", utente);

 				request.getRequestDispatcher("WEB-INF/jsp/utente.jsp").forward(request, response);
 			} else {
 				request.setAttribute("errore","Credenziali errate, riprovare" );
				System.out.println("Credenziali errate, riprovare");
 				request.getRequestDispatcher("login.jsp").forward(request, response);
 			}
 		}
        
    }

}
