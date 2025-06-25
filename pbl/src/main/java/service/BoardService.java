package service;

import java.util.List;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.apache.ibatis.session.SqlSession;

import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import mapper.BoardMapper;
import util.MybatisUtil;

@Slf4j
public class BoardService {
	public List<Board> list(Criteria cri){
		try (SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper= session.getMapper(BoardMapper.class);
			List<Board> list = mapper.list(cri);
			
			return list; // 1page에 10 개씩
 		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	
	public Board findBy(Long bno) {
		try (SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.selectOne(bno);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public void write(Board board) {
		try (SqlSession session = MybatisUtil.getSqlSession(false)){ //트렌젝션하게 작동
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.insert(board);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			board.getAttachs().forEach(a -> {
				a.setBno(board.getBno());
				attachMapper.insert(a);
			});
			session.commit();
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
	public long getCount(Criteria cri) {
		try (SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCount(cri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void modify(Board board) {
		try (SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.update(board);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void remove(Long bno) {
		try (SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			mapper.delete(bno);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
	

	
}
