package com.callor.eventpang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.callor.eventpang.models.UserVO;

public interface UserDao {
	
	@Select("SELECT * FROM tbl_user")
	public List<UserVO> selectAll();
	
	@Select("SELECT * FROM tbl_user WHERE USER_ID = #{id}")
	public UserVO findById(String id);

	@Delete("DELETE FROM tbl_user WHERE USER_ID = #{id}")
	public int deleteById(String id);
	
	public int update(UserVO userVO);
	
	public int insert(UserVO userVO);
}
