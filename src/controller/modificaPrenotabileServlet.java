package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcAutomobileDAO;
import dataBase.DataBase;
import model.Automobile;


@WebServlet("/modificaPrenotabileServlet")
public class modificaPrenotabileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int idCategoria;

    public modificaPrenotabileServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
		int id = Integer.parseInt(request.getParameter("id"));

		String disponibile = request.getParameter("cars");
		int d ;
		if(disponibile.equals("Si")){d=1;}else {d=0;}
		
		System.out.println(id);
		System.out.println(disponibile);

		dbAutomobile.modificaPrenotabile(id, d);
		
		int idCategoria = dbAutomobile.getCategoriaFromId(id);
		
		ArrayList<Automobile> list = dbAutomobile.getAutomobiliCategoria(idCategoria);
		
 		request.setAttribute("list", list);
 		request.setAttribute("idCategoria", idCategoria);

        request.getRequestDispatcher("/WEB-INF/jsp/viewAutomobili.jsp").forward(request, response);
		
	}

}
