package com.callor.eventpang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.callor.eventpang.models.BdCommentVO;

public interface BdCommentDao {
	@Select("SELECT * FROM tbl_bdcomment ORDER BY bc_num")
	public List<BdCommentVO> selectAll();
	
	@Select("SELECT * FROM tbl_bdcomment WHERE bc_num = #{bc_num}")
	public BdCommentVO findByNum(int num);
	
	@Delete("DELETE FROM tbl_bdcomment WHERE bc_num = #{bc_num}")
	public int deleteByNum(int num);
	
	public int update(BdCommentVO bdComment);

	public int insert(BdCommentVO bdComment);
	
}
