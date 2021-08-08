package com.ss501.myplayreservation.domain.reservation.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	private Long id;
	
	private Long reservationId;
	
	private String paymentStatus;
	
	private Integer price;
	
	private Date approvalDate;
}

