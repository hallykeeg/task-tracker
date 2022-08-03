package com.ronick.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ronick.beans.TaskItem;
import com.ronick.dao.MysqlDaoFactory;
import com.ronick.database.DB;

/**
 * Servlet implementation class Task
 */
@WebServlet("/Task")
public class ShowTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MysqlDaoFactory mdf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTasks() {
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
		 
		 
		ArrayList<TaskItem> liste = mdf.getTaskDao().getTasks();
		HttpSession session = request.getSession(true);
		
		request.setAttribute("tasks", liste);
		this.getServletContext().getRequestDispatcher("/WEB-INF/showTasks.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
