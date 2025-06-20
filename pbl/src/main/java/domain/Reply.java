package domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Alias("reply")
public class Reply {
	private Long rno;
	private String content;
	private String id;
	private String regdate;
	private Long bno;
}
