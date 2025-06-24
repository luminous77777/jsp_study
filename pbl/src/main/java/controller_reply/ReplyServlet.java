package controller_reply;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Reply;
import domain.Review;
import lombok.extern.slf4j.Slf4j;
import service.ReplyService;

@WebServlet("/reply/*")
@Slf4j
public class ReplyServlet extends HttpServlet{
	
	private static final String ID = "/reply/";
	
	private String getURI(HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf(ID) + ID.length());
		return uri;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = getURI(req);
		ReplyService service = new ReplyService();
		Gson gson = new Gson();
		String ret = "";
		if(uri.startsWith("list") || uri.equals("*")) {// json을 반환하는 api
			//여기까지오면
			log.info("uri 테스트 {}",uri);
			String tmp = "list/";
			if(uri.contains(tmp)) {
				String[] tmps = uri.split("/");
				
				if(tmps.length > 1) {
					Long bno = Long.valueOf(tmps[1]);
					Long lastRno = null;
					if(tmps.length > 2) {
						lastRno = Long.valueOf(tmps[2]);
					}
					ret = gson.toJson(service.list(bno,lastRno));
				}
			}
				
//			ret = gson.toJson(service.list());
//			ret = get.toJson(service.list());
			
			

		}
		else { // 단일 조회, /review/1
			ret = gson.toJson(service.findBy(Long.parseLong(uri)));
		}
		resp.setContentType("application/json; charset=utf-8"); //마임타입
		resp.getWriter().print(ret);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		new ReplyService().remove(rno);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", "true")));
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//insert의 목적
		String ret= String.join("", req.getReader().lines().toList());
		Reply reply = new Gson().fromJson(ret, Reply.class);
		
		// 이 시점 rno > null
		new ReplyService().register(reply);
		// 이 시점 rno < not null
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result",true,"reply",reply)));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//modifiy의 목적
		String uri = getURI(req);
		Long rno = Long.valueOf(uri);
		
		String ret= String.join("", req.getReader().lines().toList());
		Reply reply = new Gson().fromJson(ret, Reply.class);
		new ReplyService().modify(reply);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new Gson().toJson(Map.of("result", true)));
	}
	
	
}
