package com.rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest.entity.Ticket;

public interface TicketBookingRepository extends CrudRepository<Ticket, Integer> {
	Ticket findByEmail(String email);
}
