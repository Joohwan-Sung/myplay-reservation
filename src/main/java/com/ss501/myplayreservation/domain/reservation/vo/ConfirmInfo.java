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
public class ConfirmInfo {
	@Column (name = "confirmDate")
	private String confirmDate;
	
	@Column (name = "filePath")
	private String filePath;
}
