package com.callor.eventpang.models;

import java.util.Date;

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
public class BoardVO {
	private int bd_num;//	NUMBER(9, 0)		PRIMARY KEY,
	private String bd_userid;//	VARCHAR2(50)	NOT NULL	,
	private String bd_title;//	nVARCHAR2(500)	NOT NULL	,
	private String bd_category;//	nVARCHAR2(50)	NOT NULL	,
	private Date bd_writed_time;//	DATE	NOT NULL	,
	private int bd_recommend;//	NUMBER(6, 0)	NOT NULL	,
	private String bd_body;//	nVARCHAR2(2000)	NOT NULL	,
	private int bd_views;//	NUMBER(9, 0)	NOT NULL	,
}
