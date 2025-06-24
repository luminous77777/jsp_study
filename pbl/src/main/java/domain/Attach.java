package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
	
	private String uuid;
	private String path;
	private boolean image;
	private String origin;
	private Long bno;
	private Long rno;
	private int odr;
	
	public Attach(String uuid, String path, boolean image, String origin, Long bno, Long rno) {
		super();
		this.uuid = uuid;
		this.path = path;
		this.image = image;
		this.origin = origin;
		this.bno = bno;
		this.rno = rno;
	}
}
