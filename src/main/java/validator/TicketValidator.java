package validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Competition;
import domain.MyUser;
import domain.Ticket;
import repository.TicketRepository;

public class TicketValidator implements Validator {
	@Autowired
	private TicketRepository tr;

	@Override
	public boolean supports(Class<?> clazz) {
		return Ticket.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ticket ticket = (Ticket) target;
		MyUser user = ticket.getOwner();
		Competition competition = ticket.getCompetition();
		Optional<Long> userHasTicketsForCompetition = tr.AmountOfTicketByOwnerAndCompetition(user, competition);
		int intUserHasTicketsForCompetition = userHasTicketsForCompetition.map(Long::intValue).orElse(0);
		Optional<Long> userHasTicket = tr.AmountOfTicketByOwner(user);
		int intUserHasTickets = userHasTicket.map(Long::intValue).orElse(0);

		if(intUserHasTicketsForCompetition +  ticket.getAmount() > 20)
			errors.rejectValue("amount", "ticket.amount.max.competition", new Object[] {"20"}, null);
		if(intUserHasTickets + ticket.getAmount() > 100) 
			errors.rejectValue("amount", "ticket.amount.total", new Object[] {"100"}, null);
		if(competition.getTicketLeft() < ticket.getAmount())
			errors.rejectValue("amount", "ticket.amount.ticketLeft");
	}

}
