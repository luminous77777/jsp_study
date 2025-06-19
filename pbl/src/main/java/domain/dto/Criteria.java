package domain.dto;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

	private int page = 1;
	private int amount = 10;
	private int cno = 2;
	private String type=""; // T 제목, C내용 I작성자
	private String keyword="";
	
	public Criteria(int page, int amount, int cno) {
		this.page = page;
		this.amount = amount;
		this.cno = cno;
	}
	
	 public int getOffset() {
		 int offset = amount * (page-1);
		 return offset;
	 }
	 
	 
	 public String[] getTypes() {
		 String[] arr = null;
		 if(type != null && !type.equals("")) {
			 arr = type.split("");
		 }
		 return arr;
	 }

	 public static Criteria init(HttpServletRequest req) {
		Criteria cri = new Criteria();
		
		try {
			cri.cno = Integer.parseInt(req.getParameter("cno"));			
			cri.page = Integer.parseInt(req.getParameter("page"));
			cri.amount = Integer.parseInt(req.getParameter("amount"));
			cri.type = req.getParameter("type");
			cri.keyword =  URLDecoder.decode(req.getParameter("keyword"), "utf-8");
		} catch (Exception e) {}
		return cri;
	}
	 
	 public String getQs() {
		 String[] strs = {"cno=" + cno, "amount=" + amount, "type="+type, "keyword="+ keyword};	
		 String str = String.join("&", strs);
		return str; 
	 }
	 
	 public String getQs2() { // 게시글 상세 보기 , 글 수정, 글 작성 등에 사용 //el에서 qs 라고 호출
		 return getQs() + "&page=" + page;
	 }

	
}
