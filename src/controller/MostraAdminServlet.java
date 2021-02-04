package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.jdbc.JdbcCategoriaDAO;
import dao.jpa.JpaCategoriaDAO;
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
		JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();

		List<Categoria> list = dbCategoria.getListCategoria();
	
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
	}

}
