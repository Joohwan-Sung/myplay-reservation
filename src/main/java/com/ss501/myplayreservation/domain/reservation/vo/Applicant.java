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
public class Applicant {
	@Column(name="applicantId")
	private Long applicantId;
	
	@Column(name="applicantEmail")
	private String applicantEmail;
	
	@Column(name="applicantName")
	private String applicantName;
	
	@Column(name="applicantMobile")
	private String applicantMobile;
}
