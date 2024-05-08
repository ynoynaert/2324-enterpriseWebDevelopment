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
			errors.rejectValue("date", "date has to be after 26/07/2024");
		if (competition.getDate().isAfter(LocalDate.of(2024, 8, 11)))
			errors.rejectValue("date", "date has to be before 11/08/2024");
		// TIME
		if (competition.getTime().isBefore(LocalTime.of(8, 0)))
			errors.rejectValue("time", "time has to be after 8:00");
		// DISCIPLINES
		if(competition.getDisciplines().size() > 2) {
			errors.rejectValue("disciplines", "max 2 disciplines allowed");
		}
		// OLYMPIC NUMBER ONE
		if (competition.getOlympicNumber1().length() != 5)
			errors.rejectValue("olympicNumber1", "Olympic number1 must be 5 digits long");
		if (competition.getOlympicNumber1().charAt(0) == '0')
			errors.rejectValue("olympicNumber1", "Olympic number1 cannot start with 0");
		if (competition.getOlympicNumber1().charAt(0) == competition.getOlympicNumber1()
				.charAt(competition.getOlympicNumber1().length() - 1))
			errors.rejectValue("olympicNumber1", "First and last digits of Olympic number1 must be different");
		// OLYMPIC NUMBER TWO
		try {
			int number2 = Integer.parseInt(competition.getOlympicNumber2());
			int number1 = Integer.parseInt(competition.getOlympicNumber1());
			if (number2 < number1 - 1000 || number2 > number1 + 1000)
				errors.rejectValue("olympicNumber2",
						"Olympic number2 is not within the valid range of Olympic number1 ± 1000");
		} catch (NumberFormatException e) {
			errors.rejectValue("olympicNumber2", "Olympic number2 must be a numeric value");
		}

	}
}
