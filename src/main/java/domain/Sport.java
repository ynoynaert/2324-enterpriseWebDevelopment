package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "id")
public class Sport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@NotBlank
	private String name;

	@Getter
	@OneToMany(mappedBy = "sport")
	private List<Competition> competitions = new ArrayList<>();

	@Getter
	@OneToMany(mappedBy = "sport")
	private List<Stadium> stadiums = new ArrayList<>();

	public Sport(String name) {
		setName(name);
	}

	public void addCompetition(Competition comp) {
		competitions.add(comp);
	}

	public void addStadium(Stadium stad) {
		stadiums.add(stad);
	}
}
