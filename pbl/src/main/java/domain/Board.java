package domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	private Long bno;
	private String title;
	private String content;
	private String id;
	private String regdate;
	private String moddate;
	private Integer cnt;
}
