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
public class PlaygroundOwner {
	@Column(name = "corporateid")
	private Long corporateId;
	
	@Column(name = "corporateName")
	private String name;
}
