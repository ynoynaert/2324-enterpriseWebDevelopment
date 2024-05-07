package domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED) @EqualsAndHashCode(exclude = "id")
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @ManyToOne @Setter @Getter
    private Competition competition;
    
//    @ManyToOne @Setter @Getter
//    private MyUser owner;
	
    @Id @Getter @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotNull(message = "Ticket price is required") @Getter @Setter
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "149.99")
    private double price;

	public Ticket(double price) {
		setPrice(price);
	}
}
