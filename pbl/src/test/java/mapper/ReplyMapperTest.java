package mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Board;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;
import util.MybatisUtil;

@Slf4j
public class ReplyMapperTest {
	//ctrl + shift + 키패드의 / 메서드 폴딩
	//ctrl + shift + 키패드의 * 메서드 언폴딩

	private ReplyMapper replyMapper = MybatisUtil.getSqlSession().getMapper(ReplyMapper.class);
	
	@Test
	@DisplayName("단일 댓글 조회용 테스트")
	public void testSelectOne() {
		// given
		Long rno = 4L;
		
		// when
		Reply reply = replyMapper.selectOne(rno);
		
		//then~ actual, expect
		
		log.info("단일 댓글 : {}", reply);
	}
	
	@Test
	@DisplayName("댓글 리스트 조회용 테스트")
	public void testListRno() {
		List<Reply> list = replyMapper.list(1016L, 35L);
		list.forEach(b -> log.info("{} {}",b.getRno(), b.getContent()));
	}
	@Test
	@DisplayName("댓글 리스트 조회용 테스트")
	public void testListRnoNull() {
		List<Reply> list = replyMapper.list(1016L, null);
		list.forEach(b -> log.info("{} {}",b.getRno(), b.getContent()));
	}
	
	@Test
	@DisplayName("글 수정 테스트")
	public void testUpdate() {
		Long rno = 4L;
		Reply reply = replyMapper.selectOne(rno);
		reply.setContent("수정하기");
		
		replyMapper.update(reply);
	}

	
	@Test
	@DisplayName("댓글 등록 테스트")
	public void testInset() {
		Reply reply = Reply.builder().content("매퍼 테스트 등록 글").id("test").bno(1016L).build();
		log.info("{}",reply);
		
		replyMapper.insert(reply);
		log.info("{}",reply);
		
	}
	
	@Test
	@DisplayName("댓글 삭제 테스트")
	public void testDelete() {
		Long rno = 122L;
		replyMapper.delete(rno);
	}
}
