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
public class BdCommentVO {
	private int bc_num;//	NUMBER(9, 0)		PRIMARY KEY,
	private int bc_bdnum;//	NUMBER(9, 0)	NOT NULL	,
	private String bc_userid;//	VARCHAR2(50)	NOT NULL	,
	private String bc_body;//	nVARCHAR2(2000)	NOT NULL	,
	private Date bc_writed_time;//	DATE	NOT NULL	,
	private int bc_bcnum;//	NUMBER(9, 0)		,
	private int bc_recommend;//	NUMBER(6, 0)	NOT NULL	,
}
