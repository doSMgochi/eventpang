package com.callor.eventpang.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.callor.eventpang.models.EventVO;

public interface EventDao {
	@Select("SELECT * FROM tbl_event ORDER BY evt_num")
	public List<EventVO> selectAll();

	@Select("SELECT * FROM tbl_event WHERE evt_num = #{evt_num}")
	public EventVO findByNum(int num);

	@Delete("DELETE FROM tbl_event WHERE evt_num = #{evt_num}")
	public int deleteByNum(int num);

	public int update(int num);

	public int insert(EventVO eventVO);

	@Update("UPDATE tbl_event SET evt_views = evt_views + 1 WHERE evt_num = #{evt_num}")
	public int updateViews(int evt_num);

	@Select("SELECT * FROM tbl_event WHERE evt_body LIKE '%' || #{search} || '%' OR evt_tags LIKE '%' || #{search} || '%' OR evt_title LIKE '%' || #{search} || '%' OR evt_host LIKE '%' || #{search} || '%' OR evt_reward LIKE '%' || #{search} || '%'")
	public List<EventVO> findBySearch(String search);

	@Select("SELECT * FROM tbl_event WHERE evt_writed_time BETWEEN #{start} AND #{end} ORDER BY evt_writed_time DESC")
	public List<EventVO> findEventsByWriteTimeBetween(@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end);

	@Select("SELECT * FROM (SELECT * FROM tbl_event ORDER BY evt_views DESC) WHERE ROWNUM <= 10")
	public List<EventVO> findTop10EventsByViews();

	@Select("SELECT * FROM tbl_event WHERE evt_end_time BETWEEN #{start} AND #{end} ORDER BY evt_end_time ASC")
	public List<EventVO> findEventsByEndTimeBetween(@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end);

	@Select("SELECT * FROM tbl_event WHERE evt_winning_time BETWEEN #{start} AND #{end} ORDER BY evt_winning_time ASC")
	public List<EventVO> findEventsByWinningTimeBetween(@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end);
}
