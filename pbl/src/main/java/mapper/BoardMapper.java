package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import domain.Board;

public interface BoardMapper {
	
	
	List<Board> list();
	
	Board selectOne(Long bno);
}
