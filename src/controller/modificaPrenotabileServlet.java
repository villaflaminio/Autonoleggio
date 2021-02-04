package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.jdbc.JdbcAutomobileDAO;
import dao.jpa.JpaAutomobileDAO;
import dao.jpa.JpaCategoriaDAO;
import dao.jpa.JpaNoleggioDAO;
import dao.jpa.JpaUtenteDAO;
import dataBase.DataBase;
import model.Automobile;
import model.Categoria;

/**
 * Servlet implementation class modificaPrenotabileServlet
 */
@WebServlet("/modificaPrenotabileServlet")
public class modificaPrenotabileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	JpaNoleggioDAO dbNoleggio = JpaNoleggioDAO.getInstance();
	JpaAutomobileDAO dbAutomobile = JpaAutomobileDAO.getInstance();

    public modificaPrenotabileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Categoria categoria = (Categoria) session.getAttribute("categoria");
    	
		List<Automobile> list = dbAutomobile.getAutomobiliPerCategoria(categoria);
		
 		request.setAttribute("list", list);
 		//request.setAttribute("idCategoria", idCategoria);
 		
		request.getRequestDispatcher("WEB-INF/jsp/visualizzaAuto.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAuto = Integer.parseInt(request.getParameter("idAuto"));
		HttpSession session = request.getSession();
		Categoria categoria = (Categoria)session.getAttribute("categoria");
		
		String disponibile = request.getParameter("cars");
		boolean disp ;
		if(disponibile.equals("Si")){disp = true;}else { disp =false ;}
		
		System.out.println(idAuto);
		System.out.println(disponibile);
		Automobile a = dbAutomobile.getAutomobile(idAuto);
		a.setPrenotabile(disp);

		dbAutomobile.inserisciAutomobile(a, categoria);
		
		
		List<Automobile> list = dbAutomobile.getAutomobiliPerCategoria(a.getCategoria());
		
 		request.setAttribute("list", list);
  
        request.getRequestDispatcher("/WEB-INF/jsp/visualizzaAuto.jsp").forward(request, response);
		
	}

}
