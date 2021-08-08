package com.ss501.myplayreservation.domain.reservation.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss501.myplayreservation.domain.reservation.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	
	//List<Reservation> findReservationById(Long id);
	
	//List<Reservation> findByApplicant_ApplicantId(Long applicantId);
	List<Reservation> findByApplicant_PersonalId(Long applicantId);
	
	//List<Reservation> findLatestReservationByApplicant_ApplicantId(Long applicantId, Pageable pageable);
	List<Reservation> findLatestReservationByApplicant_PersonalId(Long applicantId, Pageable pageable);
	
	//List<Reservation> findPreviousReservationByApplicant_ApplicantId(Long applicantId, Long id, Pageable pageable);
	List<Reservation> findPreviousReservationByApplicant_PersonalId(Long applicantId, Long id, Pageable pageable);
	
	//List<Reservation> findByPlayground_CorporateId(Long corporateId);
	List<Reservation> findByPlayground_CorporateMember_CorporateId(Long corporateId);
}
