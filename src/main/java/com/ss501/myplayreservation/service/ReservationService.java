package com.ss501.myplayreservation.service;

import java.util.List;

import com.ss501.myplayreservation.domain.reservation.entity.Reservation;

public interface ReservationService {
	public Reservation saveReservation(Reservation reservation);
	List<Reservation> findAll();
	Reservation findById(Long id);
	List<Reservation> findByApplicantId(Long applicantId);
    List<Reservation> findLatestReservationByApplicantId(Long applicantId);	
    List<Reservation> findPreviousReservationByApplicantId(Long applicantId, Long id);
    List<Reservation> findByCorporateId(Long corporateId);
    public Reservation updateReservation(Long id, Reservation reservation);
}
