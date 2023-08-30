package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/findIdResult.do")
public class FindIdResultController extends HttpServlet{

	private static final long serialVersionUID = -3664501897454960878L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("FindIdResultController doPost...1");
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		logger.info("Find_id name : "+name);
		logger.info("Find_id email : "+email);
		
		UserDTO user = service.selectUserByNameAndEmail(name, email);
		req.setAttribute("user", user);
		logger.info("user : "+user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findIdResult.jsp");
		dispatcher.forward(req, resp);
		
	}
}
