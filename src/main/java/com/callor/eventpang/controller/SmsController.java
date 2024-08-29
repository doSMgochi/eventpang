//package com.callor.eventpang.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.callor.eventpang.service.SmsService;
//
//@Controller
//public class SmsController {
//
//    private SmsService smsService;
//
//    @Autowired
//    public void setSmsService(SmsService smsService) {
//        this.smsService = smsService;
//    }
//
//    @RequestMapping("/send-sms")
//    public String sendSms(@RequestParam("to") String to, @RequestParam("body") String body) {
//        smsService.sendSms(to, body);
//        return "success";
//    }
//}