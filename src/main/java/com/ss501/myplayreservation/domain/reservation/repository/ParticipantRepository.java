package com.ss501.myplayreservation.domain.reservation.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss501.myplayreservation.domain.reservation.entity.Participant;
import com.ss501.myplayreservation.domain.reservation.entity.Reservation;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	List<Participant> findAllParticipantById(Long id);
	List<Reservation> findByReservationId(Long id);
	Optional<Participant> findById(Long id);
}
