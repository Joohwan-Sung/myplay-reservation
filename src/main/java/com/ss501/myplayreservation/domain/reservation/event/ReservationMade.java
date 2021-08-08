package com.ss501.myplayreservation.domain.reservation.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationMade extends AbstractEvent {

	private String stateMessage = "예약이 이루어짐";
	
	private Long id;
	
	private Long applicantId;
	
	private String applicantEmail;
	
	private String applicantName;
	
	private String applicantMobile;
	
	private Long playgroundId;
	
	private Long corporateId;
	
	private String date;

	private String startTime;
	
	private String finishTime;
	
	private String statusType;
	
	public ReservationMade(){
        super();
    }
}

