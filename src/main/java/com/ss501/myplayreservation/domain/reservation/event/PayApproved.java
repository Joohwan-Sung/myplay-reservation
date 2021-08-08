package com.ss501.myplayreservation.domain.reservation.event;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayApproved extends AbstractEvent {
	private String stateMessage = "결제가 승인됨";
	
	private Long id;
	
	private Long reservationId;
	
	private String paymentStatus;
	
	private Integer price;
	
	private Date approvalDate;
	
	public PayApproved(){

    }
}

