package com.ss501.myplayreservation.domain.reservation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatusType {
	RESERVED,
	CONFIRMED,
	CANCELED,
	FINISHED
}
