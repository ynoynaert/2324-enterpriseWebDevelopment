package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@JsonPropertyOrder({ "stadium_id", "stadium_name", "stadium_sport" })
public class Stadium implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("stadium_id")
	private Long id;

	@NotNull(message = "{validition.empty}")
	@NotBlank(message = "{validition.empty}")
	@Getter
	@Setter
	@JsonProperty("stadium_name")
	private String name;

	@ManyToOne
	@Setter
	@Getter
	@JsonProperty("stadium_sport")
	private Sport sport;

	@OneToMany(mappedBy = "stadium")
	@Getter
	@JsonIgnore
	private List<Competition> competitions = new ArrayList<>();

	public Stadium(String name) {
		setName(name);
	}
	
	public Stadium(String name, Sport sport) {
		setName(name);
		setSport(sport);
	}

	public void addCompetition(Competition comp) {
		competitions.add(comp);
	}
}
