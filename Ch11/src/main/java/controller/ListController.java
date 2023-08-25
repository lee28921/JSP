package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.MemberDTO;
import service.MemberService;

@WebServlet("/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 7807099506362753862L;
	
	private MemberService service = MemberService.INSTANCE;
	
	// 로거생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void init() throws ServletException {
		logger.info("ListController init...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ListController doGet...");
		
		List<MemberDTO> members = service.selectMembers();
		
		req.setAttribute("members", members);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
