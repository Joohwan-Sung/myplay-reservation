package com.ss501.myplayreservation.domain.reservation.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CorporateMemberChanged extends AbstractEvent {
	private String stateMessage = "법인 회원 변경됨";
	
	private Long id;
	
	private String name;
	
	private String email;	
	
	private String  tel;
	
	private String corporateNo;
	
	private String pgNo;
	
	private String address;
	
	private String statusType;
	
	private String confirmDate;
	
	private String filePath;
	
	public CorporateMemberChanged(){

    }
}

