package com.ss501.myplayreservation.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss501.myplayreservation.domain.reservation.entity.Reservation;
import com.ss501.myplayreservation.service.ReservationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reservation/api/v1")
@AllArgsConstructor
public class ReservationController {
	private ReservationService reservationService;
	private static final Logger log = LoggerFactory.getLogger(ReservationController.class);
	
	@PostMapping("/reservations")
	public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
		log.info("Json : " + reservation.toString());
		Reservation newReservation = reservationService.saveReservation(reservation);
		
		return ResponseEntity.ok().body(newReservation);
	}
	/*public ResponseEntity<Void> saveReservation(@RequestBody Reservation reservation) {
		if(!reservationService.saveReservation(reservation)) { 
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); 
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}*/
	
	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getAllReservation(@RequestParam Optional<Long> applicantId, @RequestParam Optional<Long> corporateId) {
		Optional<List<Reservation>> myReservation = null;
		
        try {
        	if (ObjectUtils.isEmpty(applicantId) == false) {
        		log.info("Request Param applicantId: " + applicantId.toString());
            	myReservation = Optional.of(reservationService.findByApplicantId(applicantId.get()));
        	} else if (ObjectUtils.isEmpty(corporateId) == false) {
        		log.info("Request Param corporateId: " + corporateId.toString());
        		myReservation = Optional.of(reservationService.findByCorporateId(corporateId.get()));
        	} else {
        		log.info("No Request Param!!");
        		myReservation = Optional.ofNullable(reservationService.findAll());
        	}
        	
		    return new ResponseEntity<List<Reservation>>(myReservation.get(), HttpStatus.OK);
        } catch(Exception e) {
 			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/reservations/{id}")
	public ResponseEntity<Reservation> getReservationById(@PathVariable("id") final Long id)
	{
		try {
			log.debug("/reservations/"+id);
			Optional<Reservation> myReservation = Optional.of(reservationService.findById(id));
		
			return new ResponseEntity<Reservation>(myReservation.get(), HttpStatus.OK);
			
		}catch(Exception e)
		{
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*@GetMapping("/reservations/applicant/{applicantId}")
	public ResponseEntity<List<Reservation>> getReservationByApplicantId(@PathVariable("applicantId") final Long applicantId)
	{
		try {
			log.debug("/reservations/applicant/"+applicantId);
			Optional<List<Reservation>> myReservation = Optional.of(reservationService.findByApplicantId(applicantId));
		
			return new ResponseEntity<List<Reservation>>(myReservation.get(), HttpStatus.OK);
			
		}catch(Exception e)
		{
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/reservations/playground/corporateId/{corporateId}")
	public ResponseEntity<List<Reservation>> getReservationByCorporateId(@PathVariable("corporateId") final Long corporateId)
	{
		try {
			
			Optional<List<Reservation>> myReservation = Optional.of(reservationService.findByCorporateId(corporateId));
		
			return new ResponseEntity<List<Reservation>>(myReservation.get(), HttpStatus.OK);
			
		}catch(Exception e)
		{
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	@PutMapping("/reservations/{id}")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation, @PathVariable Long id) {
		log.info("Json : " + reservation.toString());
		
		Optional<Reservation> myReservation = Optional.of(reservationService.findById(id));
		
        if (myReservation.get() == null) {
            log.error("reservation with id " + id + " not found");
            return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
        }
        
        log.info("Reservation found by " + id);
        
        Reservation currentReservation = myReservation.get();
        currentReservation.setStatusType(reservation.getStatusType());
		
		Reservation changedReservation = reservationService.updateReservation(id, currentReservation);
		
		return new ResponseEntity<Reservation>(changedReservation, HttpStatus.OK);
	}
}
