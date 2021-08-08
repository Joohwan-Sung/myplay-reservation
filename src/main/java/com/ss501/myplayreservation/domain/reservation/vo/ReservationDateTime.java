package com.ss501.myplayreservation.domain.reservation.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReservationDateTime {
	@Column(name="date")
	private String date;
	
	@Column(name="startTime")
	private String startTime;
	
	@Column(name="finishTime")
	private String finishTime;
}
