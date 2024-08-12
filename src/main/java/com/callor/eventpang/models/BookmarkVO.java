package com.callor.eventpang.models;

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
public class BookmarkVO {
	private int bm_num;//	NUMBER(9, 0)		PRIMARY KEY,
	private String bm_userid;//	VARCHAR2(50)	NOT NULL	,
	private int bm_evtnum;//	NUMBER(9, 0)	NOT NULL	,
}
