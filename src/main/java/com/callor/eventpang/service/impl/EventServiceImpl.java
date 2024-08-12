package com.callor.eventpang.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.callor.eventpang.dao.EventDao;
import com.callor.eventpang.models.EventVO;
import com.callor.eventpang.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	protected final EventDao eventDao;

	public EventServiceImpl(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public int saveEvent(EventVO event) {
		return eventDao.insert(event); 
	}

	@Override
	public List<EventVO> selectAll() {
		return eventDao.selectAll();
	}

	@Override
	public EventVO findByNum(int evt_num) {
	     eventDao.updateViews(evt_num);
		return eventDao.findByNum(evt_num);
	}

	@Override
	public int updateEvent(EventVO event) {
		return 0;
	}

	@Override
	public int deleteEvent(int evt_num) {
		return 0;
	}

	@Override
	public List<EventVO> findEventsByCategory(String category) {
		List<EventVO> allEvents = eventDao.selectAll(); 
		return allEvents.stream().filter(event -> {
			String[] categories = event.getEvt_category().split(",");
			return categories[0].equals(category);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Map<String, String> splitCategory(String category) {
	    String[] categories = category.split(",", 2);
	    String mainCategory = categories.length > 0 ? categories[0].trim() : "";
	    String subCategory = categories.length > 1 ? categories[1].trim() : "";

	    // 카테고리 매핑
	    switch (mainCategory) {
	        case "benefit":
	            mainCategory = "혜택";
	            break;
	        case "big-event":
	            mainCategory = "대박이벤트";
	            break;
	        case "minor-event":
	            mainCategory = "소소한 이벤트";
	            break;
	        case "community":
	            mainCategory = "커뮤니티";
	            break;
	        default:
	            break;
	    }

	    Map<String, String> categoryMap = new HashMap<>();
	    categoryMap.put("mainCategory", mainCategory);
	    categoryMap.put("subCategory", subCategory);
	    return categoryMap;
	}
	
	@Override
	public String formatDateTime(Date date) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 E요일", Locale.KOREAN);
	    return formatter.format(date);
	}

	@Override
	public List<EventVO> findBySearch(String search) {
		List<EventVO> searchEvents = eventDao.findBySearch(search); 
		return searchEvents;
	}

	@Override
	public List<EventVO> findEventsByWriteTimeBetween(LocalDateTime start, LocalDateTime end) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneWeekAgo = now.minusWeeks(1);
		return eventDao.findEventsByWriteTimeBetween(oneWeekAgo, now);
	}

	@Override
	public List<EventVO> findTop10EventsByViews() {
		return eventDao.findTop10EventsByViews();
	}

	@Override
	public List<EventVO> findEventsByEndTimeBetween(LocalDateTime start, LocalDateTime end) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneWeekLater = now.plusWeeks(1);
		return eventDao.findEventsByEndTimeBetween(now, oneWeekLater);
	}

	@Override
	public List<EventVO> findEventsByWinningTimeBetween(LocalDateTime start, LocalDateTime end) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneWeekLater = now.plusWeeks(1);
		return eventDao.findEventsByWinningTimeBetween(now, oneWeekLater);
	}
}
