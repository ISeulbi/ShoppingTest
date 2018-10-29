package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.MemberDAO;
import dbpkg.MemberVO;
import dbpkg.Utils;

@WebServlet("/memberModify")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("custno");
		int custno = Integer.parseInt(str);
		
		MemberVO vo = MemberDAO.getMember(custno);
		request.setAttribute("vo", vo);
		
		Utils.dispatcher("memberModify", request, response);
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
	    
	    MemberDAO.setUpdate(vo);
	    request.setAttribute("msg", "회원정보수정이 완료되었습니다.");
	    List<MemberVO> list = MemberDAO.getList();
		request.setAttribute("data", list);
	    Utils.dispatcher("memberFind", request, response);
	    
	}
}
