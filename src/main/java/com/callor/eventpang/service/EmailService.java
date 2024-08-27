package com.callor.eventpang.service;

public interface EmailService {

	public void sendEmail(String to, String subject, String text);

	public void sendHtmlEmail(String to, String subject, String htmlContent);
}
