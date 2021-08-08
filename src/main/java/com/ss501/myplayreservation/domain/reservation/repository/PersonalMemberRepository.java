package com.ss501.myplayreservation.domain.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss501.myplayreservation.domain.reservation.entity.PersonalMember;

@Repository
public interface PersonalMemberRepository extends JpaRepository<PersonalMember, Long> {
//	List<PersonalMember> findAllPersonalMember();
	List<PersonalMember> findAllPersonalMemberByPersonalId(Long id);
//	List<PersonalMember> findAllPersonalMemberByFavoriteType(String favoriteType);	
//	PersonalMember savePersonalMember(PersonalMember personalMember);
}
