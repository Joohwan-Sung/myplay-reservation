package com.ss501.myplayreservation.domain.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss501.myplayreservation.domain.reservation.entity.Playground;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, Long>{
	List<Playground> findByCorporateMember_CorporateId(Long corporateId);
}
