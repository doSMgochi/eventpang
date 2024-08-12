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
public class EvtCommentVO {
	private int ec_num;//	NUMBER(9, 0)		PRIMARY KEY,
	private int ec_evtnum;//	NUMBER(9, 0)	NOT NULL	,
	private String ec_userid;//	VARCHAR2(50)	NOT NULL	,
	private String ec_body;//	nVARCHAR2(2000)	NOT NULL	,
	private Date ec_writed_time;//	DATE	NOT NULL	,
	private int ec_ecnum;//	NUMBER(9, 0)		,
	private int ec_recommend;//	NUMBER(6, 0)	NOT NULL	,
}
