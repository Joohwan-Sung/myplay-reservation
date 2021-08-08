package com.ss501.myplayreservation.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ss501.myplayreservation.domain.reservation.entity.PersonalMember;
import com.ss501.myplayreservation.domain.reservation.repository.PersonalMemberRepository;
import com.ss501.myplayreservation.service.PersonalMemberService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonalMemberServiceImpl implements PersonalMemberService {
	
	private final PersonalMemberRepository personalMemberRepository;
	
	@Override
	public PersonalMember findById(Long id) {		                        
		Optional<PersonalMember> aMember = personalMemberRepository.findById(id);
		
		return aMember.get();
	}
}
