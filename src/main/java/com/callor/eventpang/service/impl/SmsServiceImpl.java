package com.callor.eventpang.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.callor.eventpang.service.SmsService;

public class SmsServiceImpl implements SmsService {
    private final String ACCOUNT_SID = "";
    private final String AUTH_TOKEN = "";
    private final String FROM_NUMBER = "";
// testPang123456789!
    public SmsServiceImpl() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSms(String to, String body) {
        Message.creator(new PhoneNumber(to), new PhoneNumber(FROM_NUMBER), body).create();
    }
}
