package com.ronick.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ronick.beans.TaskItem;
import com.ronick.dao.MysqlDaoFactory;
import com.ronick.database.DB;

/**
 * Servlet implementation class EditTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MysqlDaoFactory mdf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTask() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException{
    	mdf = MysqlDaoFactory.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String identifiant = request.getParameter("id");
		if(identifiant !=null) {
			int id = Integer.parseInt(identifiant);
			TaskItem task = mdf.getTaskDao().getTaskById(id);
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
		 
		int id = Integer.parseInt(request.getParameter("id"));
		String label = request.getParameter("label-task");
		String date = request.getParameter("date-task");
		mdf.getTaskDao().editTask(new TaskItem(id,label,date,1));
		 
		response.sendRedirect("task");
	}

}
