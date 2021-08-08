package com.ss501.myplayreservation.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	private Long id;
	private String cardNo;
	private Long reservationId;
	private Long price;
}
