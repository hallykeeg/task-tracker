package com.ronick.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ronick.beans.TaskItem;
import com.ronick.database.DB;

/**
 * Servlet implementation class EditTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		String identifiant = request.getParameter("id");
		if(identifiant !=null) {
			int id = Integer.parseInt(identifiant);
			TaskItem task = db.getTaskById(id);
			request.setAttribute("task", task);
			this.getServletContext().getRequestDispatcher("/WEB-INF/editTask.jsp").forward(request, response);;
		}else {
			response.sendRedirect("task");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		int id = Integer.parseInt(request.getParameter("id"));
		String label = request.getParameter("label-task");
		String date = request.getParameter("date-task");
		db.editTask(new TaskItem(id,label,date,1));
		 
		response.sendRedirect("task");
	}

}
