package domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data @NoArgsConstructor (access = AccessLevel.PROTECTED) @AllArgsConstructor @Table(name = "users") @Builder
public class MyUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@OneToMany(mappedBy = "owner") @Getter
//	private List<Ticket> tickets = new ArrayList<>();
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
    private Role role;
	
//	public void addTicket(Ticket ticket) {
//		tickets.add(ticket);
//	}
	
}
