package com.ss501.myplayreservation.domain.reservation.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ss501.myplayreservation.domain.reservation.vo.Address;
import com.ss501.myplayreservation.domain.reservation.vo.PlaygroundOwner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Playground microservice의 Evnent를 받아서 저장하기 위한 Entity Class</p>
 * 일부 property 명이 중복되어서 원래의 Playground property명과 다름 
 * @author Jack
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "playground")
public class Playground {
	@Id
	@Column(name = "id")
	private Long playgroundId;
	
	@Column(name = "corporateMember")
	@Embedded
	private PlaygroundOwner corporateMember;
	
	@Column(name = "name")
	private String playgroundName;
	
	@Column(name = "address")
	@Embedded
	private Address playgroundAddress;
	
	@Column(name = "gpsCoordinates")
	private String gpsCoordinates;
	
	@Column(name = "capacity")
	private Integer capacity;
	
	@Column(name = "startTime")
	private String openTime;
	
	@Column(name = "finishTime")
	private String closeTime;
	
	@Column(name = "priceHour")
	private Long priceHour;
	
	@Column(name = "playgroundType")
	private String playgroundType;
	
	@Column(name = "playgroundLevelType")
	private String playgroundLevelType;
}
