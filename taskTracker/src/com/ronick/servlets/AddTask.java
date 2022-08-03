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
 * Servlet implementation class AddTask
 */
@WebServlet("/AddTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MysqlDaoFactory mdf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTask() {
        super();
        
    }
    public void init() throws ServletException{
    	mdf = MysqlDaoFactory.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/addTask.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String label = request.getParameter("label-task");
		String date = request.getParameter("date-task");
		mdf.getTaskDao().addTask(new TaskItem(0,label,date,1));
		 
		response.sendRedirect("task");
	}

}
