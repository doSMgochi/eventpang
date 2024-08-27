package com.callor.eventpang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.eventpang.service.EmailService;

@Controller
public class NotificationController {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/send-email")
    public String sendEmail(
        @RequestParam("to") String to,         // 받는 사람의 이메일 주소
        @RequestParam("subject") String subject,  // 이메일 제목
        @RequestParam("content") String content   // 이메일 내용
    ) {
        emailService.sendEmail(to, subject, content);
        return "success";
    }
}
