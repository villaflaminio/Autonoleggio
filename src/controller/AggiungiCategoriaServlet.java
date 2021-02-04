package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Utente;
import model.Categoria;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcCategoriaDAO;
import dao.jpa.JpaCategoriaDAO;
import dataBase.DataBase;
/*
 * 
		db = DataBase.getInstance();
		Utente u = db.getUtente("vifla01@gmail.com", "flaminio");
		System.out.println(u.getNome());
		Categoria c = new Categoria(1,"berlina",15,20,30,"benzina 120cv");
		db.inserisciCategoria(c);
		
		Categoria k = db.getCategoria(1);
		System.out.println(k.getPrezzoMensile());
 */
@WebServlet("/AggiungiCategoriaServlet")
public class AggiungiCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();	
    public AggiungiCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		List<Categoria> list = dbCategoria.getListCategoria();

 		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		double prezzoGiornaliero = 0 ;
		double prezzoSettimanale = 0;
		double prezzoMensile = 0;
		if(request.getParameter("prezzoGiornaliero") != "") {prezzoGiornaliero = Integer.parseInt(request.getParameter("prezzoGiornaliero"));}
		if(request.getParameter("prezzoSettimanale") != "") {prezzoSettimanale = Integer.parseInt(request.getParameter("prezzoSettimanale"));}
		if(request.getParameter("prezzoMensile") != "") {prezzoMensile = Integer.parseInt(request.getParameter("prezzoMensile"));}
		List<Categoria> list = dbCategoria.getListCategoria();

		
		String descrizione = request.getParameter("descrizione");
		//devo controllare se la categoria non esiste e se non e'vuota
		
		if(!dbCategoria.existCategoria(nome)) {
			if(!nome.isBlank() && !descrizione.isBlank() && prezzoGiornaliero >0 && prezzoSettimanale >0 && prezzoMensile >0) {
				Categoria c = new Categoria(nome,prezzoGiornaliero,prezzoSettimanale, prezzoMensile, descrizione );
				dbCategoria.inserisciCategoria(c);
 				request.setAttribute("errore","categoria " + nome+ " aggiunta con successo");
 				System.out.println("categoria " + nome+ " aggiunta con successo");
 				list = dbCategoria.getListCategoria();
 		 		request.setAttribute("list", list);
 				request.getRequestDispatcher("/WEB-INF/jsp/categorie.jsp").forward(request, response);

			}else {
 				request.setAttribute("errore", "compilare tutti i campi");
 				System.out.println("compilare tutti i campi");
			}			
		}else {
				request.setAttribute("errore", "La categoria con il nome" + nome+ "e' gia presente nel db");

			System.out.println("La categoria con il nome" + nome+ "e' gia presente nel db");
		}
				

 		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/aggiungiCategoria.jsp").forward(request, response);
	}

}
