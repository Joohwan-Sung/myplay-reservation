package com.ss501.myplayreservation.domain.reservation.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationCanceled extends AbstractEvent {

	private String stateMessage = "예약이 취소됨";
	
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
	
	public ReservationCanceled(){
        super();
    }
}

