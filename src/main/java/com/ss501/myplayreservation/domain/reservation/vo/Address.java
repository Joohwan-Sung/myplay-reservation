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
public class Address {
	@Column (name = "zipCode")
	private String zipCode;
	
	@Column (name = "baseAddress")
	private String baseAddress;
	
	@Column (name = "detailAddress")
	private String detailAddress;
}
