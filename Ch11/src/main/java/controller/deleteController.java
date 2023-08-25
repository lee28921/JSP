package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/delete.do")
public class deleteController extends HttpServlet{

	private static final long serialVersionUID = 8160178550372876254L;
	
	// 로거생성
	private Logger logger =  Logger.getGlobal();
	
	private MemberService service = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("deleteController doGet...1");
		
		String uid = req.getParameter("uid");
		service.deleteMember(uid);
		
		resp.sendRedirect("/Ch11/list.do");
	}
	
}
