package com.ss501.myplayreservation.domain.reservation.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ss501.myplayreservation.MyplayReservationApplication;
import com.ss501.myplayreservation.domain.reservation.enums.ReservationStatusType;
import com.ss501.myplayreservation.domain.reservation.event.ReservationCanceled;
import com.ss501.myplayreservation.domain.reservation.event.ReservationMade;
import com.ss501.myplayreservation.domain.reservation.repository.ReservationRepository;
import com.ss501.myplayreservation.domain.reservation.vo.ReservationDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="reservation")
// Entity 순환 참조를 방지하기 위해 JsonIdentityInfo tag 사용
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation {
	private static final Logger log = LoggerFactory.getLogger(Reservation.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	// PersonalMember Entity를 read만 하기 위해서 단방향으로 관계 설정함
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "applicant_id")
	private PersonalMember applicant;
	
	// Playground Entity를 read만 하기 위해서 단방향으로 관계 설정함
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "playground_id")
	private Playground playground;
	
	@Column(name="reservationDateTime")
	@Embedded
	private ReservationDateTime reservationDateTime;
	
	// Foreign key로 관계 설정하여 저장하기 위해서 양방향 관계 설정함
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private List<Participant> participants = new ArrayList<>();
	
	@Column(name="statusType")
	@Enumerated(EnumType.STRING)
	private ReservationStatusType statusType;
	
	public void addParticipant(Participant participant) {
		this.participants.add(participant);
		participant.setReservation(this); // reservation_id에 관계 주입
	}
	
	/**
	 * Reservation entity Producer
	 */
	@PostPersist
	public void publishReservationMade() {
		log.info("--- Reservation made event in");
		// Reservation 가 만들어지면 PayApproved 이벤트 발행
		ReservationMade reservationMade = new ReservationMade();
		BeanUtils.copyProperties(this, reservationMade);
		reservationMade.publishAfterCommit();
		
		log.info("--- Reservation event publishAfterCommit");
	}
	
	/**
	 * Reservation entity Producer
	 */
	@PostUpdate
    private void publishReservationCancelled(){
		log.info("--- Reservation canceled event in");
		if( "CANCELED".equals(this.getStatusType().toString())){
            // 이벤트를 발송하기 위하여 예약 정보를 조회
            ReservationRepository reservationRepository = MyplayReservationApplication.applicationContext.getBean(ReservationRepository.class);
            Optional<Reservation> reservationOptional = reservationRepository.findById(this.getId());
            if (reservationOptional.isPresent()) {
	            Reservation reservation = reservationOptional.get();
	
	            ReservationCanceled reservationCanceled = new ReservationCanceled();
	            BeanUtils.copyProperties(reservation, reservationCanceled);
	            reservationCanceled.publish();
	            log.info("--- Reservation changed event publish");
            }
        } else {
        	log.info("--- Reservation changed event not match");
        }
    }
}
