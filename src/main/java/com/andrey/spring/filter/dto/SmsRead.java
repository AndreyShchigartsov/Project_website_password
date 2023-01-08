package com.andrey.spring.filter.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class SmsRead {
    String sms;
    LocalDate time;
    String loginSender;
}
