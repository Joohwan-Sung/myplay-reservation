package com.ss501.myplayreservation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ss501.myplayreservation.domain.reservation.entity.Participant;
import com.ss501.myplayreservation.domain.reservation.entity.Reservation;
import com.ss501.myplayreservation.domain.reservation.repository.ReservationRepository;
import com.ss501.myplayreservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	private final ReservationRepository reservationRepository;
	private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Value("${api.payment.url}")
	private String apiPaymentUrl;
	
	@Transactional
	public Reservation saveReservation(Reservation reservation) {
		// 1. Reservation Entity와 Foreign Key로 연결
		log.debug("Reservation Entity와 Foreign Key로 연결");
		List<Participant> participants = reservation.getParticipants();
		reservation.setParticipants(new ArrayList<Participant>());
		
		for (Participant aNewParticipant : participants) {
			reservation.addParticipant(aNewParticipant);
		}
		
		// 2. Reservation Entity 저장
		log.debug("Reservation Entity 저장");
		//participantRepository.saveAll(reservation.getParticipants());
		Reservation newReservation = reservationRepository.save(reservation);
		
		return newReservation;
	}

	@Override
	public List<Reservation> findAll() {
		Optional<List<Reservation>> allReservation = Optional.ofNullable(reservationRepository.findAll());
		
		return allReservation.get();//Lists.newArrayList(allReservation.get());
	}

	@Override
	public Reservation findById(Long id) {
		Optional<Reservation> aReservation = reservationRepository.findById(id);
		
		return aReservation.get();
	}

	@Override
	public List<Reservation> findByApplicantId(Long applicantId) {
		//List<Reservation> allReservation = reservationRepository.findByApplicant_ApplicantId(applicantId);
		List<Reservation> allReservation = reservationRepository.findByApplicant_PersonalId(applicantId);
		
		return allReservation;
	}

	@Override
	public List<Reservation> findLatestReservationByApplicantId(Long applicantId) {
		//Optional<List<Reservation>> lastestReservation = Optional.ofNullable(reservationRepository.findLatestReservationByApplicant_ApplicantId(applicantId, PageRequest.of(0, 10)));
		Optional<List<Reservation>> lastestReservation = Optional.ofNullable(reservationRepository.findLatestReservationByApplicant_PersonalId(applicantId, PageRequest.of(0, 10)));
		
		return lastestReservation.get();
	}

	@Override
	public List<Reservation> findPreviousReservationByApplicantId(Long applicantId, Long id) {
		//Optional<List<Reservation>> previousReservation = Optional.ofNullable(reservationRepository.findPreviousReservationByApplicant_ApplicantId(applicantId, id, PageRequest.of(0, 10)));
		Optional<List<Reservation>> previousReservation = Optional.ofNullable(reservationRepository.findPreviousReservationByApplicant_PersonalId(applicantId, id, PageRequest.of(0, 10)));
		
		return previousReservation.get();
	}
	
	@Override
	public Reservation updateReservation(Long id, Reservation reservation) {
		Reservation changedReservation = reservationRepository.save(reservation);
		return changedReservation;
	}

	@Override
	public List<Reservation> findByCorporateId(Long corporateId) {
		//List<Reservation> allReservation = reservationRepository.findByPlayground_CorporateId(corporateId);
		List<Reservation> allReservation = reservationRepository.findByPlayground_CorporateMember_CorporateId(corporateId);
		
		return allReservation;
	}
}
