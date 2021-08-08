package com.ss501.myplayreservation.domain.reservation.event;

import com.ss501.myplayreservation.domain.reservation.vo.Address;
import com.ss501.myplayreservation.domain.reservation.vo.PlaygroundOwner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaygroundChanged {
	private Long playgroundId;
	
	private PlaygroundOwner corporateMember;
	
	private String playgroundName;
	
	private Address playgroundAddress;
	
	private String gpsCoordinates;
	
	private Integer capacity;
	
	private String openTime;
	
	private String closeTime;
	
	private Long priceHour;
	
	private String playgroundType;
	
	private String playgroundLevelType;
	
}
