package domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED) @EqualsAndHashCode(exclude = "id")
public class Competition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne @Setter @Getter
	private Sport sport;
	
	@NotNull @Getter @Setter
	//TODO: De  datum  moet  liggen  tussen  26  juli 2024 en 11 augustus 2024.
	private LocalDate date;

	@NotBlank @Getter @Setter
	@Pattern(regexp = "^([8-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid hour format. Use HH:mm")
	private String time;
	
	@NotBlank(message = "Stadium name is required") @Getter @Setter
	private String stadium;
	
    @NotBlank(message = "Olympic number 1 is required") @Getter @Setter
    @Pattern(regexp = "^(?!0)(?!.*(.)\\1)[0-9]{5}$", message = "Invalid Olympic number format")
    private String olympicNumber1;
	
    @NotBlank(message = "Olympic number 2 is required") @Getter @Setter
    @Pattern(regexp = "^[0-9]+$", message = "Invalid Olympic number format")
	private String olympicNumber2;
	
    @Nullable @Getter @Setter
	private String discipline1;
    
    @Nullable @Getter @Setter
	private String discipline2;
	
    @NotNull(message = "Ticket quantity is required") @Getter @Setter
    @Range(min = 1, max = 50, message = "Total tickets must be between 1 and 50")
    private int totalTickets;
    
    @NotNull(message = "Ticket price is required") @Getter @Setter
    @Range(min = 0, max = 149, message = "Price per ticket must be between 0 and 149")
    private double pricePerTicket;
    
    @NotNull(message = "Remaining tickets is required") @Getter @Setter
    @Range(min = 1, max = 49, message = "Ticket left must be between 1 and 49")
    private int ticketLeft;
    
    public Competition (LocalDate date, String time, String stadium, String olympicNumber1, String olympicNumber2, 
    		String discipline1, String discipline2, int totalTickets, double pricePerTicket, int ticketLeft) {
    	setDate(date);
    	setDiscipline1(discipline1);
    	setDiscipline2(discipline2);
    	setOlympicNumber1(olympicNumber1);
    	setOlympicNumber2(olympicNumber2);
    	setPricePerTicket(pricePerTicket);
    	setStadium(stadium);
    	setTicketLeft(ticketLeft);
    	setTime(time);
    	setTotalTickets(totalTickets);
    }
}
