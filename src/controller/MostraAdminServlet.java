package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcCategoriaDAO;
import model.Categoria;


@WebServlet("/MostraAdminServlet")
public class MostraAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MostraAdminServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();

		ArrayList<Categoria> list = dbCategoria.getListCategoria();
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
	}

}
