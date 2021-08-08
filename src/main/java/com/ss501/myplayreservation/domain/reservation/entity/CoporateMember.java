package com.ss501.myplayreservation.domain.reservation.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ss501.myplayreservation.domain.reservation.vo.Address;
import com.ss501.myplayreservation.domain.reservation.vo.ConfirmInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "coporate_member")
public class CoporateMember { //extends Member{
	@Id
	@Column (name = "id")
	private Long id;
	
	@Column (name = "loginPassword")
	private String loginPassword;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "email")
	private String email;	
	
	@Column (name = "tel")
	private String  tel;
	
	@Column (name = "coporateNo")
	private String coporateNo;
	
	@Column (name = "pgNo")
	private String pgNo;
	
	@Column (name = "address")
	@Embedded
	private Address address;
	
	@Column (name = "statusType")
	private String statusType;
	
	@Column (name = "confirmInfo")
	@Embedded
	private ConfirmInfo confirmInfo;
}
