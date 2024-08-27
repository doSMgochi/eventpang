package com.callor.eventpang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

	@RequestMapping(value = "/search", method = RequestMethod.GET)
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
	public String viewEvent(@PathVariable("evt_num") int evt_num, Model model, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER");
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
		model.addAttribute("loggedInUser", user);

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

	@RequestMapping(value = "/event-delete/{evt_num}", method = RequestMethod.POST)
	public String delete(@PathVariable("evt_num") int evt_num, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER");

		if (user == null) {
			return "redirect:/user/login";
		}

		EventVO event = eventService.findByNum(evt_num);
		if (event == null || !event.getEvt_userid().equals(user.getUser_id())) {
			return "redirect:/board/event-view/" + evt_num;
		}

		eventService.deleteEvent(evt_num);
		return "redirect:/";
	}

	@RequestMapping(value = "/event-modify/{evt_num}", method = RequestMethod.GET)
	public String modify(@PathVariable("evt_num") int evt_num, Model model, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER");
		EventVO event = eventService.findByNum(evt_num);

		if (user == null) {
			return "redirect:/user/login";
		}

		if (!event.getEvt_userid().equals(user.getUser_id())) {
			return "redirect:/board/event-view/" + evt_num;
		}

		String[] categories = event.getEvt_category().split(",");
		String category = categories.length > 0 ? categories[0] : "";
		String detailCategory = categories.length > 1 ? categories[1] : "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String startDate = dateFormat.format(event.getEvt_start_time());
		String endDate = dateFormat.format(event.getEvt_end_time());
		String winningDate = dateFormat.format(event.getEvt_winning_time());

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("winningDate", winningDate);
		model.addAttribute("event", event);
		model.addAttribute("category", category);
		model.addAttribute("detailCategory", detailCategory);

		return "board/event-write";
	}

	@RequestMapping(value = "/event-modify", method = RequestMethod.POST)
	public String update(EventVO event, HttpSession session, String category, String detailCategory, Model model) {
		UserVO user = (UserVO) session.getAttribute("USER");

		// 로그 추가: 메서드 진입 시점
		log.debug("Entering event-modify POST request");

		// 로그 추가: 전달된 파라미터 확인
		log.debug("EventVO: {}", event);
		log.debug("Category: {}", category);
		log.debug("DetailCategory: {}", detailCategory);
		log.debug("Logged in User: {}", user != null ? user.getUser_id() : "No user");
		log.debug("Event UserID: '{}' (length: {})", event.getEvt_userid(), event.getEvt_userid().length());
		log.debug("Logged in UserID: '{}' (length: {})", user.getUser_id(), user.getUser_id().length());

		if (user == null || !user.getUser_id().trim().equals(event.getEvt_userid().trim())) {
		    log.debug("User is not authenticated or not authorized. Redirecting to login.");
		    return "redirect:/user/login";
		}

		String sanitizedContent = XssSanitizer.sanitize(event.getEvt_body());
		event.setEvt_body(sanitizedContent);

		String combinedCategory = category + "," + detailCategory;
		event.setEvt_category(combinedCategory);
		
		 // 날짜 형식 수동 변환
		try {
	        SimpleDateFormat inputFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
	        SimpleDateFormat outputFormatter = new SimpleDateFormat("yy/MM/dd");
	        
	        if (event.getEvt_writed_time() != null) {
	            Date date = inputFormatter.parse(event.getEvt_writed_time().toString());
	            event.setEvt_writed_time(outputFormatter.parse(outputFormatter.format(date)));
	        }

	        if (event.getEvt_start_time() != null) {
	            Date date = inputFormatter.parse(event.getEvt_start_time().toString());
	            event.setEvt_start_time(outputFormatter.parse(outputFormatter.format(date)));
	        }

	        if (event.getEvt_end_time() != null) {
	            Date date = inputFormatter.parse(event.getEvt_end_time().toString());
	            event.setEvt_end_time(outputFormatter.parse(outputFormatter.format(date)));
	        }

	        if (event.getEvt_winning_time() != null) {
	            Date date = inputFormatter.parse(event.getEvt_winning_time().toString());
	            event.setEvt_winning_time(outputFormatter.parse(outputFormatter.format(date)));
	        }

	    } catch (ParseException e) {
	        e.printStackTrace();
	        return "redirect:/error";
	    }


		// 로그 추가: 이벤트 업데이트 전
		log.debug("Updating event with combined category: {}", combinedCategory);

		eventService.updateEvent(event);

		// 로그 추가: 업데이트 완료 후
		log.debug("Event updated successfully, redirecting to view page.");

		model.addAttribute("event", event);
		return "redirect:/board/view/" + event.getEvt_num();
	}
}
