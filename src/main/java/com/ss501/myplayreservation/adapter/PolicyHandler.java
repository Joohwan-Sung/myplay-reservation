package com.ss501.myplayreservation.adapter;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss501.myplayreservation.app.config.kafka.KafkaProcessor;
import com.ss501.myplayreservation.domain.reservation.entity.PersonalMember;
import com.ss501.myplayreservation.domain.reservation.entity.Playground;
import com.ss501.myplayreservation.domain.reservation.entity.Reservation;
import com.ss501.myplayreservation.domain.reservation.enums.ReservationStatusType;
import com.ss501.myplayreservation.domain.reservation.event.PayApproved;
import com.ss501.myplayreservation.domain.reservation.event.PayCanceled;
import com.ss501.myplayreservation.domain.reservation.event.PersonalMemberChanged;
import com.ss501.myplayreservation.domain.reservation.event.PlaygroundChanged;
import com.ss501.myplayreservation.domain.reservation.repository.PersonalMemberRepository;
import com.ss501.myplayreservation.domain.reservation.repository.PlaygroundRepository;
import com.ss501.myplayreservation.domain.reservation.repository.ReservationRepository;

import lombok.AllArgsConstructor;

/**
 * myplay topic Consumer class
 * @author Jack
 *
 */
@Service
@AllArgsConstructor
public class PolicyHandler {
	private final ReservationRepository reservationRepository;
	private final PersonalMemberRepository personalMemberRepository;
	private final PlaygroundRepository playgroundRepository;
	private static final Logger log = LoggerFactory.getLogger(PolicyHandler.class);
	
    @StreamListener(KafkaProcessor.INPUT)
    public void onListener(@Payload String message) {
    	System.out.println("##### listener : " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        PayApproved payApproved = null;
        try {
        	payApproved = objectMapper.readValue(message, PayApproved.class);

            System.out.println(" #### type = " + payApproved.getEventType());


            // 결제 승인 시 예약 확정 처리한다.
            if( payApproved.isMe()){
            	log.info("PayApproved Process");
                Optional<Reservation> reservationOptional = reservationRepository.findById(payApproved.getReservationId());
                Reservation reservation = reservationOptional.get();
                reservation.setStatusType(ReservationStatusType.CONFIRMED);

                reservationRepository.save(reservation);
            // 예약 취소시 예약 취소 처리한다
            } else if( payApproved.getEventType().equals(PayCanceled.class.getSimpleName())){
            	log.info("PayCanceled Process");
            	Optional<Reservation> reservationOptional = reservationRepository.findById(payApproved.getReservationId());
                Reservation reservation = reservationOptional.get();
                reservation.setStatusType(ReservationStatusType.CANCELED);

                reservationRepository.save(reservation);
            // 개인 회원 신규/변경시 개인회원 Entity를 저장한다    
            } else if( payApproved.getEventType().equals(PersonalMemberChanged.class.getSimpleName())){
            	PersonalMemberChanged personalMemberChanged = objectMapper.readValue(message, PersonalMemberChanged.class);
            	
            	PersonalMember personalMember = new PersonalMember();
            	personalMember.setPersonalId(personalMemberChanged.getId());
            	personalMember.setEmail(personalMemberChanged.getEmail());
            	personalMember.setMemberFavoriteType(personalMemberChanged.getFavoriteType());
            	personalMember.setMemberLevelType(personalMemberChanged.getLevelType());
            	personalMember.setMemberStatusType(personalMemberChanged.getStatusType());
            	personalMember.setMileage(personalMemberChanged.getMileage());
            	personalMember.setMobile(personalMemberChanged.getMobile());
            	personalMember.setName(personalMemberChanged.getName());
            	personalMember.setPoint(personalMemberChanged.getPoint());
            	
                personalMemberRepository.save(personalMember);
            // 시설물 신규/변경시 시설물 Entity를 저장한다
            } else if( payApproved.getEventType().equals(PlaygroundChanged.class.getSimpleName())){
            	PlaygroundChanged playgroundChanged = objectMapper.readValue(message, PlaygroundChanged.class);
            	
            	Playground playground = new Playground();
            	BeanUtils.copyProperties(playgroundChanged, playground);
                playgroundRepository.save(playground);
            }

        }catch (Exception e){
        	log.error(e.getMessage(), e);
        }
    }

}

