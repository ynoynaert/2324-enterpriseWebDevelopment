package validator;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Competition;
import repository.CompetitionRepository;

public class CompetitionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Competition.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Competition competition = (Competition) target;

		// DATE
		if (competition.getDate().isBefore(LocalDate.of(2024, 7, 26)))
			errors.rejectValue("date", "competition.date.min", null, "2024-07-26");
		if (competition.getDate().isAfter(LocalDate.of(2024, 8, 11)))
			errors.rejectValue("date", "competition.date.max", null, "2024-08-11");
		// TIME
		if (competition.getTime().isBefore(LocalTime.of(8, 0)))
			errors.rejectValue("time", "competition.time.min");
		// DISCIPLINES
		if(competition.getDisciplines().size() > 2) {
			errors.rejectValue("disciplines", "competition.disciplines.size", null, "2");
		}
		// OLYMPIC NUMBER ONE
		try {
			int number1 = Integer.parseInt(competition.getOlympicNumber1());
			if (competition.getOlympicNumber1().length() != 5)
				errors.rejectValue("olympicNumber1", "competition.olympic1.size");
			if (competition.getOlympicNumber1().charAt(0) == '0')
				errors.rejectValue("olympicNumber1", "competition.olympic1.start");
			if (competition.getOlympicNumber1().charAt(0) == competition.getOlympicNumber1()
					.charAt(competition.getOlympicNumber1().length() - 1))
				errors.rejectValue("olympicNumber1", "competition.olympic1.digits");
		} catch (NumberFormatException e) {
			errors.rejectValue("olympicNumber1", "competition.olympic1.numeric");
		}
		
		// OLYMPIC NUMBER TWO
		try {
			int number2 = Integer.parseInt(competition.getOlympicNumber2());
			int number1 = Integer.parseInt(competition.getOlympicNumber1());
			if (number2 < number1 - 1000 || number2 > number1 + 1000)
				errors.rejectValue("olympicNumber2",
						"competition.olympic2.range");
		} catch (NumberFormatException e) {
			errors.rejectValue("olympicNumber2", "competition.olympic2.numeric");
		}

	}
}
