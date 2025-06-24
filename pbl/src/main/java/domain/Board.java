package domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Alias("board") //xml config에 tyalias에 설정 domain
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private Long bno;
	private String title;
	private String content;
	private String id;
	private String regdate;
	private String moddate;
	private Integer cnt;
	private Integer cno;
	
	
	public Board(Long bno, String title, String content, String id, String regdate, String moddate, Integer cnt,
			Integer cno) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.moddate = moddate;
		this.cnt = cnt;
		this.cno = cno;
	}


	@Builder.Default
	private List<Attach> attachs = new ArrayList<>();
	
}
