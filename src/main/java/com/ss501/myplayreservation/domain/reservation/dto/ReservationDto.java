package com.ss501.myplayreservation.domain.reservation.dto;

import java.util.ArrayList;
import java.util.List;

import com.ss501.myplayreservation.domain.reservation.entity.Participant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	private Long id;
	
	private Long applicantId;
	
	private String applicantEmail;
	
	private String applicantName;
	
	private String applicantMobile;
	
	private List<Participant> participants = new ArrayList<>();
	
	private Long playgroundId;
	
	private String playgroundName;
	
	private Long corporateId;
	
	private String corporateName;
	
	private String playgroundAddress;
	
	private String gpsCoordinates;
	
	private Integer capacity;
	
	private String playgroundStartTime;
	
	private String playgroudFinishTime;
	
	private Long priceHour;
	
	private String playgroundType;
	
	private String reservationDateTime;
	
	private String reservationStatusType;
	
	private Long paymentId;
	
	private String cardNo;
	
	private Long paymentAmount;
	
	private String statusType;
	
	public void addParicipant(Participant participant) {
    	this.participants.add(participant);
    }
}
