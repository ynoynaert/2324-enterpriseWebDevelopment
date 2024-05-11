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
			errors.rejectValue("date", "competition.date.min", new Object[]{"2024-07-26"}, null);
		if (competition.getDate().isAfter(LocalDate.of(2024, 8, 11)))
			errors.rejectValue("date", "competition.date.max", new Object[]{"2024-08-11"}, null);
		// TIME
		if (competition.getTime().isBefore(LocalTime.of(8, 0)))
			errors.rejectValue("time", "competition.time.min", new Object[] {"08:00"}, null);
		// DISCIPLINES
		if(competition.getDisciplines().size() > 2) {
			errors.rejectValue("disciplines", "competition.disciplines.size", new Object[]{"2"}, null);
		}
		// OLYMPIC NUMBER ONE
		try {
			int number1 = Integer.parseInt(competition.getOlympicNumber1());
			if (competition.getOlympicNumber1().length() != 5)
				errors.rejectValue("olympicNumber1", "competition.olympic1.size", new Object[] {"5"}, null);
			if (competition.getOlympicNumber1().charAt(0) == '0')
				errors.rejectValue("olympicNumber1", "competition.olympic1.start", new Object[] {"0"}, null);
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
				errors.rejectValue("olympicNumber2", "competition.olympic2.range", new Object[] {"1000"}, null);
		} catch (NumberFormatException e) {
			errors.rejectValue("olympicNumber2", "competition.olympic2.numeric");
		}

	}
}
