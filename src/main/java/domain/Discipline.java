package domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
public class Discipline {

	private static final long serialVersionUID = 1L;

	@Id @Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Getter
	@Setter
	private String name;
	
	@ManyToOne
	@Setter
	@Getter
	private Sport sport;
	
	@ManyToMany(mappedBy = "disciplines")
	@Getter
	@Setter
	private List<Competition> competitions = new ArrayList<>();
	
	public Discipline(String name) {
		setName(name);
	}
}
