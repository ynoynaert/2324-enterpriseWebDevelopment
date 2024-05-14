package com.springBoot.examenOpdracht;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domain.Competition;
import domain.Discipline;
import domain.MyUser;
import domain.Role;
import domain.Sport;
import domain.Stadium;
import domain.Ticket;
import repository.CompetitionRepository;
import repository.DisciplineRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.TicketRepository;
import repository.UserRepository;
import service.OlympicService;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;
	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private StadiumRepository stadiumRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private OlympicService olympicService;

	private PasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public void run(String... args) {

		MyUser user = new MyUser("user", encoder.encode("Password"), Role.USER);
		MyUser admin = new MyUser("admin", encoder.encode("Password"), Role.ADMIN);
		MyUser user2 = new MyUser("persoon", encoder.encode("Password"), Role.USER);

		Sport athletics = new Sport("Athletics");
		Stadium s1 = new Stadium("Olympic Stadium");
		Stadium s6 = new Stadium("Athletics Stadium");
		Stadium s11 = new Stadium("Olympic Athletics Park");
		Stadium s16 = new Stadium("Olympic Track Field");
		Discipline da1 = new Discipline("Sprint & High Jump");
		Discipline da2 = new Discipline("Long Jump & Discus Throw");
		Discipline da3 = new Discipline("Marathon & Shot Put");
		Discipline da4 = new Discipline("Hurdles & Pole Vault");
		Competition c1 = new Competition(LocalDate.of(2024, 7, 26), LocalTime.of(14, 0), "13579", "14579", 50, 25.0,
				48);
		Competition c6 = new Competition(LocalDate.of(2024, 7, 29), LocalTime.of(15, 30), "24680", "25680", 40, 22.5,
				0);
		Competition c11 = new Competition(LocalDate.of(2024, 8, 5), LocalTime.of(13, 0), "35791", "36791", 46, 20.0, 0);
		Competition c16 = new Competition(LocalDate.of(2024, 8, 11), LocalTime.of(11, 30), "46802", "47802", 37, 23.0,
				15);

		Sport gymnastics = new Sport("Gymnastics");
		Stadium s2 = new Stadium("Gymnastics Arena");
		Stadium s7 = new Stadium("Gymnastics Palace");
		Stadium s12 = new Stadium("Gymnastics Hall");
		Stadium s17 = new Stadium("Gymnastics Center");
		Discipline dg1 = new Discipline("Floor Exercise & Rings");
		Discipline dg2 = new Discipline("Vault & Parallel Bars");
		Discipline dg3 = new Discipline("Balance Beam & Horizontal Bar");
		Discipline dg4 = new Discipline("Trampoline & Uneven Bars");
		Competition c2 = new Competition(LocalDate.of(2024, 7, 28), LocalTime.of(11, 0), "57913", "58913", 30, 30.0,
				16);
		Competition c7 = new Competition(LocalDate.of(2024, 8, 10), LocalTime.of(10, 0), "68024", "69024", 25, 35.0,
				23);
		Competition c12 = new Competition(LocalDate.of(2024, 8, 4), LocalTime.of(14, 30), "79135", "70135", 35, 32.5,
				20);
		Competition c17 = new Competition(LocalDate.of(2024, 7, 31), LocalTime.of(16, 0), "80246", "81246", 30, 27.5,
				10);

		Sport volleyball = new Sport("Volleybal");
		Stadium s3 = new Stadium("Volleyball Complex");
		Stadium s8 = new Stadium("Volleyball Center");
		Stadium s13 = new Stadium("Volleyball Arena");
		Stadium s18 = new Stadium("Volleyball Hall");
		Discipline dv1 = new Discipline("Indoor Volleyball");
		Discipline dv2 = new Discipline("Beach Volleyball");
		Discipline dv3 = new Discipline("Mixed Volleyball");
		Discipline dv4 = new Discipline("Sitting Volleyball");
		Competition c3 = new Competition(LocalDate.of(2024, 8, 6), LocalTime.of(17, 30), "91357", "92357", 28, 20.0, 8);
		Competition c8 = new Competition(LocalDate.of(2024, 8, 10), LocalTime.of(16, 45), "12468", "13468", 27, 18.0,
				7);
		Competition c13 = new Competition(LocalDate.of(2024, 7, 31), LocalTime.of(19, 15), "23579", "14579", 19, 25.0,
				9);
		Competition c18 = new Competition(LocalDate.of(2024, 7, 29), LocalTime.of(15, 45), "34680", "25680", 13, 22.0,
				3);

		Sport basketball = new Sport("Basketball");
		Stadium s4 = new Stadium("Basketball Arena");
		Stadium s9 = new Stadium("Basketball Complex");
		Stadium s14 = new Stadium("Basketball Stadium");
		Stadium s19 = new Stadium("Basketball Hall");
		Discipline db1 = new Discipline("3x3 Basketball");
		Discipline db2 = new Discipline("Women's Basketball");
		Discipline db3 = new Discipline("Men's Basketball");
		Discipline db4 = new Discipline("Slam Dunk Contest");
		Competition c4 = new Competition(LocalDate.of(2024, 7, 22), LocalTime.of(19, 0), "45791", "36791", 46, 35.0,
				40);
		Competition c9 = new Competition(LocalDate.of(2024, 8, 2), LocalTime.of(18, 30), "56802", "47802", 45, 28.0,
				30);
		Competition c14 = new Competition(LocalDate.of(2024, 8, 2), LocalTime.of(20, 30), "67913", "58913", 40, 30.0,
				20);
		Competition c19 = new Competition(LocalDate.of(2024, 7, 23), LocalTime.of(17, 45), "78024", "69024", 50, 32.0,
				0);

		Sport judo = new Sport("Judo");
		Stadium s5 = new Stadium("Judo Center");
		Stadium s10 = new Stadium("Judo Arena");
		Stadium s15 = new Stadium("Judo Hall");
		Discipline dj1 = new Discipline("Lightweight & Heavyweight");
		Discipline dj2 = new Discipline("Individual Kata");
		Discipline dj3 = new Discipline("Team Judo");
		Competition c5 = new Competition(LocalDate.of(2024, 8, 5), LocalTime.of(13, 45), "89135", "70135", 40, 40.0,
				21);
		Competition c10 = new Competition(LocalDate.of(2024, 8, 5), LocalTime.of(12, 15), "90246", "81246", 35, 45.0,
				20);
		Competition c15 = new Competition(LocalDate.of(2024, 8, 1), LocalTime.of(16, 0), "13570", "92357", 40, 38.0, 4);
		Competition c20 = new Competition(LocalDate.of(2024, 7, 29), LocalTime.of(14, 45), "12469", "13468", 35, 42.0,
				0);

		Ticket t1 = new Ticket(4);
		Ticket t2 = new Ticket(3);
		Ticket t3 = new Ticket(4);
		Ticket t4 = new Ticket(3);

		c1.setStadium(s1);
		c6.setStadium(s6);
		c11.setStadium(s11);
		c16.setStadium(s16);
		c1.setSport(athletics);
		c6.setSport(athletics);
		c11.setSport(athletics);
		c16.setSport(athletics);
		s1.setSport(athletics);
		s6.setSport(athletics);
		s11.setSport(athletics);
		s16.setSport(athletics);
		da1.setSport(athletics);
		da2.setSport(athletics);
		da3.setSport(athletics);
		da4.setSport(athletics);
		athletics.addCompetition(c1);
		athletics.addCompetition(c6);
		athletics.addCompetition(c11);
		athletics.addCompetition(c16);
		athletics.addStadium(s1);
		athletics.addStadium(s6);
		athletics.addStadium(s11);
		athletics.addStadium(s16);
		c1.addDisciplines(da1);
		c6.addDisciplines(da2);
		c11.addDisciplines(da3);
		c16.addDisciplines(da4);
		s1.addCompetition(c1);
		s6.addCompetition(c6);
		s11.addCompetition(c11);
		s16.addCompetition(c16);

		c2.setStadium(s2);
		c7.setStadium(s7);
		c12.setStadium(s12);
		c17.setStadium(s17);
		c2.setSport(gymnastics);
		c7.setSport(gymnastics);
		c12.setSport(gymnastics);
		c17.setSport(gymnastics);
		s2.setSport(gymnastics);
		s7.setSport(gymnastics);
		s12.setSport(gymnastics);
		s17.setSport(gymnastics);
		dg1.setSport(gymnastics);
		dg2.setSport(gymnastics);
		dg3.setSport(gymnastics);
		dg4.setSport(gymnastics);
		gymnastics.addCompetition(c2);
		gymnastics.addCompetition(c7);
		gymnastics.addCompetition(c12);
		gymnastics.addCompetition(c17);
		gymnastics.addStadium(s2);
		gymnastics.addStadium(s7);
		gymnastics.addStadium(s12);
		gymnastics.addStadium(s17);
		c2.addDisciplines(dg1);
		c7.addDisciplines(dg2);
		c12.addDisciplines(dg3);
		c17.addDisciplines(dg4);
		s2.addCompetition(c2);
		s7.addCompetition(c7);
		s12.addCompetition(c12);
		s17.addCompetition(c17);

		c3.setStadium(s3);
		c8.setStadium(s8);
		c13.setStadium(s13);
		c18.setStadium(s18);
		c3.setSport(volleyball);
		c8.setSport(volleyball);
		c13.setSport(volleyball);
		c18.setSport(volleyball);
		s3.setSport(volleyball);
		s8.setSport(volleyball);
		s13.setSport(volleyball);
		s18.setSport(volleyball);
		dv1.setSport(volleyball);
		dv2.setSport(volleyball);
		dv3.setSport(volleyball);
		dv4.setSport(volleyball);
		volleyball.addCompetition(c3);
		volleyball.addCompetition(c8);
		volleyball.addCompetition(c13);
		volleyball.addCompetition(c18);
		volleyball.addStadium(s3);
		volleyball.addStadium(s8);
		volleyball.addStadium(s13);
		volleyball.addStadium(s18);
		c3.addDisciplines(dv1);
		c8.addDisciplines(dv2);
		c13.addDisciplines(dv2);
		c13.addDisciplines(dv3);
		c18.addDisciplines(dv4);
		s3.addCompetition(c3);
		s8.addCompetition(c8);
		s13.addCompetition(c13);
		s18.addCompetition(c18);

		c4.setSport(basketball);
		c9.setSport(basketball);
		c14.setSport(basketball);
		c19.setSport(basketball);
		c4.setStadium(s4);
		c9.setStadium(s9);
		c14.setStadium(s14);
		c19.setStadium(s19);
		s4.setSport(basketball);
		s9.setSport(basketball);
		s14.setSport(basketball);
		s19.setSport(basketball);
		db1.setSport(basketball);
		db2.setSport(basketball);
		db3.setSport(basketball);
		db4.setSport(basketball);
		basketball.addCompetition(c4);
		basketball.addCompetition(c9);
		basketball.addCompetition(c14);
		basketball.addCompetition(c19);
		basketball.addStadium(s4);
		basketball.addStadium(s9);
		basketball.addStadium(s14);
		basketball.addStadium(s19);
		c4.addDisciplines(db1);
		c9.addDisciplines(db2);
		c9.addDisciplines(db3);
		c14.addDisciplines(db4);
		c19.addDisciplines(db2);
		c19.addDisciplines(db1);
		s4.addCompetition(c4);
		s9.addCompetition(c9);
		s14.addCompetition(c14);
		s19.addCompetition(c19);

		c5.setStadium(s5);
		c10.setStadium(s10);
		c15.setStadium(s15);
		c20.setStadium(s5);
		s5.setSport(judo);
		s10.setSport(judo);
		s15.setSport(judo);
		c5.setSport(judo);
		c10.setSport(judo);
		c15.setSport(judo);
		c20.setSport(judo);
		dj1.setSport(judo);
		dj2.setSport(judo);
		dj3.setSport(judo);
		judo.addCompetition(c5);
		judo.addCompetition(c10);
		judo.addCompetition(c15);
		judo.addCompetition(c20);
		judo.addStadium(s5);
		judo.addStadium(s10);
		judo.addStadium(s15);
		c5.addDisciplines(dj1);
		c10.addDisciplines(dj3);
		c15.addDisciplines(dj2);
		s5.addCompetition(c5);
		s5.addCompetition(c20);
		s10.addCompetition(c10);
		s15.addCompetition(c15);

		t1.setOwner(user);
		t2.setOwner(user);
		t3.setOwner(user);
		t4.setOwner(user);
		t1.setCompetition(c1);
		t2.setCompetition(c5);
		t3.setCompetition(c8);
		t4.setCompetition(c18);

		usersRepository.save(admin);
		usersRepository.save(user);
		usersRepository.save(user2);

		sportRepository.save(athletics);
		sportRepository.save(gymnastics);
		sportRepository.save(volleyball);
		sportRepository.save(basketball);
		sportRepository.save(judo);

		stadiumRepository.save(s1);
		stadiumRepository.save(s2);
		stadiumRepository.save(s3);
		stadiumRepository.save(s4);
		stadiumRepository.save(s5);
		stadiumRepository.save(s6);
		stadiumRepository.save(s7);
		stadiumRepository.save(s8);
		stadiumRepository.save(s9);
		stadiumRepository.save(s10);
		stadiumRepository.save(s11);
		stadiumRepository.save(s12);
		stadiumRepository.save(s13);
		stadiumRepository.save(s14);
		stadiumRepository.save(s15);
		stadiumRepository.save(s16);
		stadiumRepository.save(s17);
		stadiumRepository.save(s18);
		stadiumRepository.save(s19);

		disciplineRepository.save(da1);
		disciplineRepository.save(da2);
		disciplineRepository.save(da3);
		disciplineRepository.save(da4);
		disciplineRepository.save(dg1);
		disciplineRepository.save(dg2);
		disciplineRepository.save(dg3);
		disciplineRepository.save(dg4);
		disciplineRepository.save(dv1);
		disciplineRepository.save(dv2);
		disciplineRepository.save(dv3);
		disciplineRepository.save(dv4);
		disciplineRepository.save(db1);
		disciplineRepository.save(db2);
		disciplineRepository.save(db3);
		disciplineRepository.save(db4);
		disciplineRepository.save(dj1);
		disciplineRepository.save(dj2);
		disciplineRepository.save(dj3);

		competitionRepository.save(c1);
		competitionRepository.save(c2);
		competitionRepository.save(c3);
		competitionRepository.save(c4);
		competitionRepository.save(c5);
		competitionRepository.save(c6);
		competitionRepository.save(c7);
		competitionRepository.save(c8);
		competitionRepository.save(c9);
		competitionRepository.save(c10);
		competitionRepository.save(c11);
		competitionRepository.save(c12);
		competitionRepository.save(c13);
		competitionRepository.save(c14);
		competitionRepository.save(c15);
		competitionRepository.save(c16);
		competitionRepository.save(c17);
		competitionRepository.save(c18);
		competitionRepository.save(c19);
		competitionRepository.save(c20);

		ticketRepository.save(t1);
		ticketRepository.save(t2);
		ticketRepository.save(t3);
		ticketRepository.save(t4);

		olympicService.addTicketToComp(t1, user);
		olympicService.addTicketToComp(t2, user);
		olympicService.addTicketToComp(t3, user);
		olympicService.addTicketToComp(t4, user);
	}

}