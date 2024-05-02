package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor (access = AccessLevel.PROTECTED) @EqualsAndHashCode(of = "email")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "owner") @Getter
	private List<Ticket> tickets = new ArrayList<>();
	
	@Email @Setter @Getter
	private String email;
	
	@Setter @Getter
	private String password;
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public Account(String email, String password) {
		setEmail(email);
		setPassword(password);
	}
}
