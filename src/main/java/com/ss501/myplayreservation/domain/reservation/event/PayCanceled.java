package com.ss501.myplayreservation.domain.reservation.event;

import java.util.Date;

import lombok.Data;

@Data
public class PayCanceled extends AbstractEvent {
	private String stateMessage = "결제가 취소됨";
	
	private Long id;
	
	private Long reservationId;
	
	private String paymentStatus;
	
	private Integer price;
	
	private Date approvalDate;
	
	public PayCanceled(){

    }
}

