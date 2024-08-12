package com.callor.eventpang.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventVO {
    private int evt_num;// NUMBER(9, 0) PRIMARY KEY,
    private String evt_userid;// VARCHAR2(50) NOT NULL,
    private String evt_host;// VARCHAR2(100) NOT NULL,
    private String evt_link;// VARCHAR2(500) NOT NULL,
    private String evt_title;// NVARCHAR2(500) NOT NULL,
    private String evt_category;// NVARCHAR2(50) NOT NULL,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evt_writed_time;// DATE NOT NULL,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evt_start_time;// DATE NOT NULL,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evt_end_time;// DATE NOT NULL,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evt_winning_time;// DATE NOT NULL,
    private int evt_winner;// NUMBER(4, 0) NOT NULL,
    private String evt_reward;// NVARCHAR2(500) NOT NULL,
    private int evt_recommend;// NUMBER(6, 0) NOT NULL,
    private String evt_body;// NVARCHAR2(2000) NOT NULL,
    private String evt_tags;// NVARCHAR2(500),
    private int evt_views;// NUMBER(9, 0) NOT NULL,
    
}
