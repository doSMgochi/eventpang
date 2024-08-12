package com.callor.eventpang.models;

import java.sql.Date;

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
public class ApplyVO {
    private int ap_num;// NUMBER(9, 0) PRIMARY KEY,
    private int ap_evtnum;// NUMBER(9, 0) NOT NULL,
    private String ap_userid;// VARCHAR2(50) NOT NULL,
    private Date ap_time;// DATE NOT NULL,
    private String ap_status;// VARCHAR2(10) NOT NULL,
}
