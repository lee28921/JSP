package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 7807099506362753862L;
	
	private Logger logger = Logger.getGlobal();
	
	
	@Override
	public void init() throws ServletException {
		logger.info("ListController init...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ListController doGet...");
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
