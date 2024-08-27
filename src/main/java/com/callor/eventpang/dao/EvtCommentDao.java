package com.callor.eventpang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.callor.eventpang.models.EvtCommentVO;

public interface EvtCommentDao {
	@Select("SELECT * FROM tbl_evtcomment ORDER BY ec_num")
	public List<EvtCommentVO> selectAll();
	
	@Select("SELECT * FROM tbl_evtcomment WHERE ec_num = #{ec_num}")
	public EvtCommentVO findByNum(int num);
	
	@Delete("DELETE FROM tbl_evtcomment WHERE ec_num = #{ec_num}")
	public int deleteByNum(int num);
	
	public int update(EvtCommentVO evtComment);

	public int insert(EvtCommentVO evtComment);
	
}
