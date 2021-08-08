package com.ss501.myplayreservation.domain.reservation.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ss501.myplayreservation.domain.reservation.vo.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Member microservice의 Evnent를 받아서 저장하기 위한 Entity Class</p>
 * 일부 property 명이 중복되어서 원래의 PersonalMember property명과 다름 
 * @author Jack
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "personal_member")
public class PersonalMember { // extends Member{
	@Id
	@Column (name = "id")
	private Long personalId ;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "loginPassword")
	private String loginPassword;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "mobile")
	private String  mobile;
	
	@Column (name = "address")
	@Embedded
	private Address memberAddress;
	
	@Column (name = "statusType")
	private String memberStatusType;
	
	@Column (name = "levelType")
	private String memberLevelType;
	
	@Column (name = "favoriteType")
	private String memberFavoriteType;
	
	@Column (name = "point")
	private Long point;
	
	@Column (name = "mileage")
	private Long mileage;
}
