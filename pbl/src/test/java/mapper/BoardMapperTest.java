package mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class BoardMapperTest {
	
	private BoardMapper boardMapper = MybatisUtil.getSqlSession().getMapper(BoardMapper.class);;
	
	
	@Test
	public void addTest() {
		int result = 1+1;
		assertEquals(2, result);
	}
	
	@Test
	@DisplayName("단일 게시글 조회용 테스트")
	public void testSelectOne() {
		// given
		Long bno = 1L;
		
		// when
		Board board = boardMapper.selectOne(1L);
		
		//then~ actual, expect
		assertNotNull(board);
		
		log.info("{}", board);
	}
	
	//list test
	@Test
	@DisplayName("목록 조회 1페이지")
	public void testLiset() {
		Criteria cri = new Criteria(1,10,2);
		List<Board> list = boardMapper.list(cri);
		list.forEach(b -> log.info("{}", b.getTitle()));
		
		
	}
	
	@Test
	@DisplayName("목록 조회 검색테스트")
	public void testLiset2() {
		Criteria cri = new Criteria(1,10,2,"TI","제목");
		log.info(Arrays.toString(cri.getTypes()));
		List<Board> list = boardMapper.list(cri);
		list.forEach(b -> log.info("{}", b.getTitle()));
		
		
	}
	
	@Test
	@DisplayName("글 수정 테스트")
	public void testUpdate() {
		Long bno = 1020L;
		Board board = boardMapper.selectOne(bno);
		board.setTitle("제목만 수정하기 테스트");
		
		boardMapper.update(board);
	}
	
	
}
