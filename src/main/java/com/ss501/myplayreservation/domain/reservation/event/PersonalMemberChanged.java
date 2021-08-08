package com.ss501.myplayreservation.domain.reservation.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalMemberChanged extends AbstractEvent {
	private String stateMessage = "개인 회원 변경됨";
	
	private Long id ;
	
	private String email;
	
	private String name;
	
	private String  mobile;
	
	private String address;
	
	private String statusType;
	
	private String levelType;
	
	private String favoriteType;
	
	private Long point;
	
	private Long mileage;
	
	public PersonalMemberChanged(){

    }
}

