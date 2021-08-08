package com.ss501.myplayreservation.service;

import java.util.List;

import com.ss501.myplayreservation.domain.reservation.entity.Playground;

public interface PlaygroundService {
	public List<Playground> findByCorporateId(Long corporateId);
	public Playground findById(Long id);
}
