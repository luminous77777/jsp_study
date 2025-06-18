package domain.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
public class PageDto {
	private Criteria cri;
	private long total;

	private int start;
	private int end;
	
	private boolean left;
	private boolean right;
	
	private boolean doubleLeft;
	private boolean doubleRight;
	private int realEnd;
	
	public PageDto(Criteria cri, long total) {
		this.cri = cri;
		this.total = total;
		
		// total = 123;
		// page = 1;
		// amount = 10;
		//start = 1
		//end = 10
		end = (cri.getPage()+9) / 10 * 10;  // 총10개의 버튼을 고정하기 때문에 리터럴을 사용
		start = end - 9;
		
		realEnd = (int) ((total + cri.getAmount() - 1) / cri.getAmount());  //과일 상자 문제
		 
		if(end > realEnd) {
			end = realEnd;
		}
		
		left = start > 1;
		right = end < realEnd;
		
		doubleLeft = cri.getPage() > 1;
		doubleRight = cri.getPage() < realEnd;
		
	}
}
