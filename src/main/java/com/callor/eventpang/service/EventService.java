package com.callor.eventpang.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.callor.eventpang.models.EventVO;

public interface EventService {

	public int saveEvent(EventVO event);

	public List<EventVO> selectAll();

	public EventVO findByNum(int evt_num);

	public int updateEvent(EventVO event);

	public int deleteEvent(int evt_num);
	
	public List<EventVO> findEventsByCategory(String category);
	
	public Map<String, String> splitCategory(String category);
	
	public String formatDateTime(Date date);
	
	public List<EventVO> findBySearch(String search);
	
	public List<EventVO> findEventsByWriteTimeBetween(LocalDateTime start, LocalDateTime end);

	public List<EventVO> findTop10EventsByViews();
	
	public List<EventVO> findEventsByEndTimeBetween(LocalDateTime start, LocalDateTime end);

	public List<EventVO> findEventsByWinningTimeBetween(LocalDateTime start, LocalDateTime end);
}