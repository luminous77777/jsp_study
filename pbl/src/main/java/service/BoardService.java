package service;

import java.util.List;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.apache.ibatis.session.SqlSession;

import domain.Board;
import lombok.extern.slf4j.Slf4j;
import mapper.BoardMapper;
import util.MybatisUtil;

@Slf4j
public class BoardService {
	public List<Board> list(){
		try (SqlSession session = MybatisUtil.getSqlSession()){
			BoardMapper mapper= session.getMapper(BoardMapper.class);
			return mapper.list();
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
	
}
