package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;

@WebServlet("/board/modify")
@Slf4j
public class Modify extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = Criteria.init(req);

		if (req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근입니다", "/board/list", req, resp);
			return;
		}
		Long bno = Long.parseLong(req.getParameter("bno"));

		if (req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인후 글 작성해주세요", "/member/login?bno=" + bno + "&" + cri.getQs2(), req, resp, true);
			return;
		}

		BoardService service = new BoardService();
		Board borad = service.findBy(bno);
		req.setAttribute("cri", cri);
		req.setAttribute("board", borad);
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//세션체크
		Criteria cri = Criteria.init(req);
		if( req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인후 글 작성해주세요", "/member/login?"+cri.getQs2(), req, resp, true);
			return;
		}
		//파라미터 수집
		
		
		String bno = req.getParameter("bno");
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근입니다", "/board/list", req, resp);
			return;
		}
		
		BoardService service = new BoardService();
		service.remove(Long.valueOf(req.getParameter("bno")));
		AlertUtil.alert("글이 수정되었습니다", "/board/list?" + cri.getQs2(), req, resp);
	}
}
