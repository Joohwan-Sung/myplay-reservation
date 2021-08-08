package com.ss501.myplayreservation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ss501.myplayreservation.domain.reservation.entity.Playground;
import com.ss501.myplayreservation.domain.reservation.repository.PlaygroundRepository;
import com.ss501.myplayreservation.service.PlaygroundService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaygroundServiceImpl implements PlaygroundService{
	PlaygroundRepository playgroundRepository;
	
	@Override
	public List<Playground> findByCorporateId(Long corporateId) {
		List<Playground> allPlayground = playgroundRepository.findByCorporateMember_CorporateId(corporateId);
		return allPlayground;
	}
	
	@Override
	public Playground findById(Long id) {
		Optional<Playground> playground = playgroundRepository.findById(id);
		
		return playground.get();
	}
}
