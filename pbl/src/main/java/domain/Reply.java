package domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reply {
	private Long rno;
	private String content;
	private String id;
	private String regdate;
	private Long bno;
}
