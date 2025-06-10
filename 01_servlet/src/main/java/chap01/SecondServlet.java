package chap01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/SecondServlet", "/servlet2"})
public class SecondServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//기본 응답 형태 text/plain 순수 텍스트 
		
		//다른 종류, text/html, text/plain, text/xml, application/json
		// image/png -> 만들어진 이미지 캡차등
		//mime-type
//		resp.setContentType("text/html; charset=iso-8859-1");
		resp.setContentType("text/html; charset=euc-kr");
		PrintWriter out = resp.getWriter();
		out.write("자바문법이다.");
		System.out.println("서버 콘솔에 출력");
		out.println("<h1>화면에 출력</h1>");
	}
	
}