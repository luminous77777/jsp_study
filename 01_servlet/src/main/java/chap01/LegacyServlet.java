package chap01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LegacyServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("레거시 호출됨");
		resp.setContentType("text/html; charset=euc-kr");
		PrintWriter out = resp.getWriter();
		out.write("자바문법이다.");
	}
	
}
