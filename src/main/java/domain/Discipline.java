package domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@JsonPropertyOrder({ "discipline_id", "discipline_name", "discipline_sport" })
public class Discipline {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("discipline_id")
	private Long id;

	@NotNull(message = "{validition.empty}")
	@NotBlank(message = "{validition.empty}")
	@Getter
	@Setter
	@JsonProperty("discipline_name")
	private String name;

	@ManyToOne
	@Setter
	@Getter
	@JsonProperty("discipline_sport")
	private Sport sport;

	@ManyToMany(mappedBy = "disciplines")
	@Getter
	@Setter
	@JsonIgnore
	private List<Competition> competitions = new ArrayList<>();

	public Discipline(String name) {
		setName(name);
	}
}
