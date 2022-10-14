package com.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Ticket;
import com.rest.repository.TicketBookingRepository;

@Service
public class TicketBookingService {

	@Autowired
	private TicketBookingRepository ticketBookingRepository;
	
	public Ticket createTicket(Ticket ticket) {
		return ticketBookingRepository.save(ticket);
	}
	public Ticket getTicketById(Integer ticketId) {
		return ticketBookingRepository.findOne(ticketId);
	}
	public Iterable<Ticket> getAllBookedTickets() {
		return ticketBookingRepository.findAll();
	}
	public void deleteTicket(Integer ticketId) {
		ticketBookingRepository.delete(ticketId);
	}
	public Ticket updateTicket(Integer ticketId, String newEmail) {
		Ticket ticketFromDb = ticketBookingRepository.findOne(ticketId);
		ticketFromDb.setEmail(newEmail);
		Ticket upadedTicket = ticketBookingRepository.save(ticketFromDb);
		return upadedTicket;
	}
	public Ticket getTicketByEmail(String email) {
		return ticketBookingRepository.findByEmail(email);
	}
}