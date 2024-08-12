package com.callor.eventpang.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.eventpang.models.EventVO;
import com.callor.eventpang.models.UserVO;
import com.callor.eventpang.service.EventService;
import com.callor.eventpang.utils.XssSanitizer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/board")
public class BoardController {

	private final EventService eventService;

	public BoardController(EventService eventService) {
		super();
		this.eventService = eventService;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search(String search, Model model) {
		List<EventVO> events = eventService.findBySearch(search);
		model.addAttribute("events", events);
		return "board/search";
	}

	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public String showEventsByCategory(@PathVariable("category") String category, Model model) {
	    List<EventVO> events = eventService.findEventsByCategory(category);
	    String categoryTitle = getCategoryTitle(category);

	    model.addAttribute("events", events);
	    model.addAttribute("categoryTitle", categoryTitle);
	    return "board/event-list"; 
	}

	private String getCategoryTitle(String category) {
	    switch (category) {
	        case "big-event":
	            return "대박이벤트";
	        case "minor-event":
	            return "소소한 이벤트";
	        case "benefit":
	            return "혜택";
	        case "community":
	            return "커뮤니티";
	        default:
	            return "이벤트";
	    }
	}
	
	@RequestMapping(value = "/events/latest", method = RequestMethod.GET)
	public String showLatestEvents(Model model) {
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime oneWeekAgo = now.minusWeeks(1);

	    List<EventVO> latestEvents = eventService.findEventsByWriteTimeBetween(oneWeekAgo, now);

	    model.addAttribute("events", latestEvents);
	    model.addAttribute("categoryTitle", "최신 이벤트");
	    return "board/event-list";
	}

	@RequestMapping(value = "/events/popular", method = RequestMethod.GET)
	public String showPopularEvents(Model model) {
	    List<EventVO> popularEvents = eventService.findTop10EventsByViews();

	    model.addAttribute("events", popularEvents);
	    model.addAttribute("categoryTitle", "인기 이벤트");
	    return "board/event-list";
	}

	@RequestMapping(value = "/events/closing-soon", method = RequestMethod.GET)
	public String showClosingSoonEvents(Model model) {
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime oneWeekLater = now.plusWeeks(1);

	    List<EventVO> closingSoonEvents = eventService.findEventsByEndTimeBetween(now, oneWeekLater);

	    model.addAttribute("events", closingSoonEvents);
	    model.addAttribute("categoryTitle", "마감 임박 이벤트");
	    return "board/event-list";
	}

	@RequestMapping(value = "/events/announcing-soon", method = RequestMethod.GET)
	public String showAnnouncingSoonEvents(Model model) {
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime oneWeekLater = now.plusWeeks(1);

	    List<EventVO> announcingSoonEvents = eventService.findEventsByWinningTimeBetween(now, oneWeekLater);

	    model.addAttribute("events", announcingSoonEvents);
	    model.addAttribute("categoryTitle", "발표 임박 이벤트");
	    return "board/event-list";
	}
	
	@RequestMapping(value = "/view/{evt_num}", method = RequestMethod.GET)
	public String viewEvent(@PathVariable("evt_num") int evt_num, Model model) {
	    EventVO event = eventService.findByNum(evt_num);

	    if (event == null) {
	        return "redirect:/board/list"; 
	    }

	    Map<String, String> categoryMap = eventService.splitCategory(event.getEvt_category());

	    String formattedWritedTime = eventService.formatDateTime(event.getEvt_writed_time());
	    String formattedStartTime = eventService.formatDateTime(event.getEvt_start_time());
	    String formattedEndTime = eventService.formatDateTime(event.getEvt_end_time());
	    String formattedWinningTime = eventService.formatDateTime(event.getEvt_winning_time());

	    model.addAttribute("event", event);
	    model.addAttribute("mainCategory", categoryMap.get("mainCategory"));
	    model.addAttribute("subCategory", categoryMap.get("subCategory"));
	    model.addAttribute("formattedWritedTime", formattedWritedTime);
	    model.addAttribute("formattedStartTime", formattedStartTime);
	    model.addAttribute("formattedEndTime", formattedEndTime);
	    model.addAttribute("formattedWinningTime", formattedWinningTime);

	    return "board/event-view"; 
	}

	@RequestMapping(value = "/event-write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/user/login";
		}

		return "board/event-write";
	}

	@RequestMapping(value = "/event-write", method = RequestMethod.POST)
	public String create(EventVO event, HttpSession session, String category, String detailCategory, Model model) {
	    UserVO user = (UserVO) session.getAttribute("USER");

	    if (user == null) {
	        return "redirect:/user/login";
	    }

	    String sanitizedContent = XssSanitizer.sanitize(event.getEvt_body());
	    event.setEvt_body(sanitizedContent);

	    String combinedCategory = category + "," + detailCategory;
	    event.setEvt_category(combinedCategory);
	    event.setEvt_userid(user.getUser_id());
	    event.setEvt_writed_time(new Date());
	    event.setEvt_recommend(0);
	    event.setEvt_views(0);

	    eventService.saveEvent(event);
	    model.addAttribute("event", event);
	    return "board/event-view"; 
	}

}
