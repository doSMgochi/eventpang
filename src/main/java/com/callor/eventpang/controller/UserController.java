package com.callor.eventpang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.eventpang.models.UserVO;
import com.callor.eventpang.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVO userVO, Model model, HttpSession httpSession) {

		log.debug("폼에서 전달받은 데이터 : {}", userVO.toString());

		int ret = userService.join(userVO);
		if (ret < 1) {
			model.addAttribute("JOIN_MSG", "FAIL");
			return "user/join";
		}

		UserVO user = userService.findById(userVO.getUser_id());
		if (user != null) {
			httpSession.setAttribute("USER", user);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(HttpSession httpSession, Model model) {
		UserVO userVO = (UserVO) httpSession.getAttribute("USER");
		if (userVO == null) {
			return "user/login";
		}
		model.addAttribute("USER", userVO);
		return "user/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(UserVO userVO, 
	                     @RequestParam("current_password") String currentPassword, 
	                     @RequestParam(value = "new_password", required = false) String newPassword, 
	                     Model model, HttpSession session) {

	    UserVO sessionUser = (UserVO) session.getAttribute("USER");
	    if (sessionUser == null) {
	        model.addAttribute("MODIFY_MSG", "로그인이 필요합니다.");
	        return "user/login";
	    }

	  
	    if (!sessionUser.getUser_password().equals(currentPassword)) {
	        model.addAttribute("MODIFY_MSG", "현재 비밀번호가 올바르지 않습니다.");
	        return "user/modify"; 
	    }

	 
	    if (newPassword != null && !newPassword.isEmpty()) {
	        userVO.setUser_password(newPassword);
	    } else {
	        userVO.setUser_password(currentPassword);
	    }

	    int ret = userService.modify(userVO);

	    if (ret < 1) {
	        model.addAttribute("MODIFY_MSG", "정보 수정에 실패했습니다.");
	        return "user/modify"; 
	    }

	    session.setAttribute("USER", userService.findById(userVO.getUser_id()));
	    return "redirect:/"; 
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(required = false, defaultValue = "") String err, HttpSession httpSession, Model model) {
		UserVO userVO = (UserVO) httpSession.getAttribute("USER");
		if (userVO != null) {
			return "redirect:/"; 
		}
		if (err.equalsIgnoreCase("NEED")) {
			model.addAttribute("MSG", "* 로그인이 필요합니다");
		}
		return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVO userVO, HttpSession httpSession, Model model) {
		UserVO user = userService.findById(userVO.getUser_id());
		if (user == null || !user.getUser_password().equals(userVO.getUser_password())) {
			model.addAttribute("MSG", "* 아이디 또는 비밀번호가 잘못되었습니다.");
			return "user/login";
		}

		httpSession.setAttribute("USER", user);
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("USER");
		return "redirect:/";
	}

	@RequestMapping(value = "/quit", method = RequestMethod.GET)
	public String delete(@RequestParam(required = false, defaultValue = "") String err, HttpSession httpSession, Model model) {
		UserVO userVO = (UserVO) httpSession.getAttribute("USER");
		if (userVO == null) {
			return "user/login";
		}
		int ret = userService.deleteById(userVO.getUser_id());
		if (ret < 1) {
			model.addAttribute("QUIT_MSG", "* 회원 탈퇴에 실패했습니다.");
			return "user/login";
		}
		model.addAttribute("QUIT_MSG", "* 회원 탈퇴에 성공했습니다.");
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/check_id", method = RequestMethod.GET)
	@ResponseBody
	public String checkId(@RequestParam("user_id") String userId) {
		UserVO userVO = userService.findById(userId);
		if (userVO != null) {
			return "EXISTS";
		}
		return "AVAILABLE";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		return null;
	}
}
