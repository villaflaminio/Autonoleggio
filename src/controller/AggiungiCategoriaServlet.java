package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Utente;
import model.Categoria;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcCategoriaDAO;
import dataBase.DataBase;

@WebServlet("/AggiungiCategoriaServlet")
public class AggiungiCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AggiungiCategoriaServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		
		ArrayList<Categoria> list = dbCategoria.getListCategoria();

 		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
		String nome = request.getParameter("nome");
		int prezzoGiornaliero = 0 ;
		int prezzoSettimanale = 0;
		int prezzoMensile = 0;
		if(request.getParameter("prezzoGiornaliero") != "") {prezzoGiornaliero = Integer.parseInt(request.getParameter("prezzoGiornaliero"));}
		if(request.getParameter("prezzoSettimanale") != "") {prezzoSettimanale = Integer.parseInt(request.getParameter("prezzoSettimanale"));}
		if(request.getParameter("prezzoMensile") != "") {prezzoMensile = Integer.parseInt(request.getParameter("prezzoMensile"));}

		
		String descrizione = request.getParameter("descrizione");
		//devo controllare se la categoria non esiste e se non e'vuota
		
		if(!dbCategoria.existCateogira(nome)) {
			if(!nome.isBlank() && !descrizione.isBlank() && prezzoGiornaliero >0 && prezzoSettimanale >0 && prezzoMensile >0) {
				Categoria c = new Categoria(nome,prezzoGiornaliero,prezzoSettimanale, prezzoMensile, descrizione );
				dbCategoria.inserisciCategoria(c);
 				request.setAttribute("errore","categoria " + nome+ " aggiunta con successo");
 				System.out.println("categoria " + nome+ " aggiunta con successo");

			}else {

 				request.setAttribute("errore", "compilare tutti i campi");
 				System.out.println("compilare tutti i campi");

			}
			

			
		}else {
				request.setAttribute("errore", "La categoria con il nome" + nome+ "e' gia presente nel db");

			System.out.println("La categoria con il nome" + nome+ "e' gia presente nel db");
		}
		
		
		
		
		ArrayList<Categoria> list = dbCategoria.getListCategoria();

 		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
	}

}
