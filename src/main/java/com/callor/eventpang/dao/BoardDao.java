package com.callor.eventpang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.callor.eventpang.models.BoardVO;

public interface BoardDao {
	@Select("SELECT * FROM tbl_board ORDER BY bd_num")
	public List<BoardVO> selectAll();

	@Select("SELECT * FROM tbl_board WHERE bd_num = #{bd_num}")
	public BoardVO findByNum(int num);

	@Delete("DELETE FROM tbl_board WHERE bd_num = #{bd_num}")
	public int deleteByNum(int num);

	public int update(BoardVO board);

	public int insert(BoardVO boardVO);
	
	@Update("UPDATE tbl_board SET bd_views = bd_views + 1 WHERE bd_num = #{bd_num}")
	public int updateViews(int bd_num);

	@Select("SELECT * FROM tbl_board WHERE bd_body LIKE '%' || #{search} || '%' OR bd_title LIKE '%' || #{search} || '%'")
	public List<BoardVO> findBySearch(String search);
}
