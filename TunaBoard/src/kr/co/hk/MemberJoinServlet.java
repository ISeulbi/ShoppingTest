package kr.co.hk;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.MemberDAO;
import dbpkg.MemberVO;
import dbpkg.Utils;

@WebServlet("/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO vo = MemberDAO.getMaxCustno();
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("memberJoin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberVO vo = new MemberVO();
		
		String str = request.getParameter("custno");
		int custno = Integer.parseInt(str);
		String custname = request.getParameter("custname");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    String joindate = request.getParameter("joindate");
	    String grade = request.getParameter("grade");
	    String city = request.getParameter("city");
	    
	    vo.setCustno(custno);
	    vo.setCustname(custname);
	    vo.setPhone(phone);
	    vo.setAddress(address);
	    vo.setJoindate(joindate);
	    vo.setGrade(grade);
	    vo.setCity(city);
	    
	    //MemberDAO.insert(vo);
	    
	    request.setAttribute("msg", "회원등록이 완료되었습니다.");
	    request.setAttribute("vo", vo);
	    Utils.dispatcher("memberJoin", request, response);
	    
	}

}
