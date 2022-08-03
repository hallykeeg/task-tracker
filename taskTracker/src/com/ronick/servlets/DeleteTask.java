package com.ronick.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ronick.beans.TaskItem;
import com.ronick.dao.MysqlDaoFactory;
import com.ronick.database.DB;

/**
 * Servlet implementation class deleteTask
 */
@WebServlet("/deleteTask")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MysqlDaoFactory mdf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTask() {
        super();
         
    }
    public void init() throws ServletException{
    	mdf = MysqlDaoFactory.getInstance();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String idTask =   request.getParameter("id") ;
		
		if( idTask!=null ) {
			//delete
			int id = Integer.parseInt(idTask);
			TaskItem task = mdf.getTaskDao().getTaskById(id);
			task.setActive(0);
			mdf.getTaskDao().editTask(task);			

			response.setContentType("application/json");
			    
			PrintWriter out = response.getWriter();
			out.print("{\"state\":\"ok\"}");
			out.flush();
		}
		
	}

}
