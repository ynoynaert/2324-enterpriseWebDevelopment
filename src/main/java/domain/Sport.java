package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@JsonPropertyOrder({ "sport_id", "sport_name" })
public class Sport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonPropertyOrder("sport_id")
	private Long id;

	@Getter
	@Setter
	@NotBlank(message = "{validition.empty}")
	@NotNull(message = "{validition.empty}")
	@JsonPropertyOrder("sport_name")
	private String name;

	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "sport")
	private List<Competition> competitions = new ArrayList<>();

	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "sport")
	private List<Stadium> stadiums = new ArrayList<>();

	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "sport")
	private List<Discipline> disciplines = new ArrayList<>();

	public Sport(String name) {
		setName(name);
	}
	
	public Sport(Long id, String name) {
		setId(id);
		setName(name);
	}

	public void addCompetition(Competition comp) {
		competitions.add(comp);
	}

	public void addStadium(Stadium stad) {
		stadiums.add(stad);
	}
}
