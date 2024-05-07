package com.springBoot.examenOpdracht;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domain.Competition;
import domain.MyUser;
import domain.Role;
import domain.Sport;
import domain.Stadium;
import repository.CompetitionRepository;
import repository.SportRepository;
import repository.StadiumRepository;
import repository.UserRepository;
import service.OlympicService;

@Component
public class InitDataConfig implements CommandLineRunner {
	
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
    private SportRepository sportRepository;
	@Autowired
	private StadiumRepository stadiumRepository;	
	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private OlympicService olympicService;
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) {
    	
    	MyUser user = MyUser.builder().username("user").role(Role.USER).password(encoder.encode("Password")).build();
        MyUser admin = MyUser.builder().username("admin").role(Role.ADMIN).password(encoder.encode("Password")).build();

    	Sport athletics = new Sport("Athletics");
    	Stadium s1 = new Stadium("Olympic Stadium");
    	Stadium s6 = new Stadium("Athletics Stadium");
    	Stadium s11 = new Stadium("Olympic Athletics Park");
    	Stadium s16 = new Stadium("Olympic Track Field");
    	Competition c1 = new Competition(LocalDate.of(2024, 5, 15), LocalTime.of(14, 0), "13579", "14579", "Sprint & High Jump", null, 50, 25.0, 48);
    	Competition c6 = new Competition(LocalDate.of(2024, 5, 17), LocalTime.of(15, 30), "24680", "25680", "Long Jump & Discus Throw", null, 40, 22.5, 1);
    	Competition c11 = new Competition(LocalDate.of(2024, 5, 16), LocalTime.of(13, 0), "35791", "36791", "Marathon & Shot Put", null, 46, 20.0, 1);
    	Competition c16 = new Competition(LocalDate.of(2024, 5, 17), LocalTime.of(11, 30), "46802", "47802", "Hurdles & Pole Vault", null, 37, 23.0, 15);

    	Sport gymnastics = new Sport("Gymnastics");
    	Stadium s2 = new Stadium("Gymnastics Arena");
    	Stadium s7 = new Stadium("Gymnastics Palace");
    	Stadium s12 = new Stadium("Gymnastics Hall");
    	Stadium s17 = new Stadium("Gymnastics Center");    	
    	Competition c2 = new Competition(LocalDate.of(2024, 5, 18), LocalTime.of(11, 0), "57913", "58913", "Floor Exercise & Rings", null, 30, 30.0, 16);
    	Competition c7 = new Competition(LocalDate.of(2024, 5, 19), LocalTime.of(10, 0), "68024", "69024", "Vault & Parallel Bars", null, 25, 35.0, 23);
    	Competition c12 = new Competition(LocalDate.of(2024, 5, 18), LocalTime.of(14, 30), "79135", "70135", "Balance Beam & Horizontal Bar", null, 35, 32.5, 20);
    	Competition c17 = new Competition(LocalDate.of(2024, 5, 19), LocalTime.of(16, 0), "80246", "81246", "Trampoline & Uneven Bars", null, 30, 27.5, 10);

    	Sport volleyball = new Sport("Volleybal");
    	Stadium s3 = new Stadium("Volleyball Complex");
    	Stadium s8 = new Stadium("Volleyball Center");
    	Stadium s13 = new Stadium("Volleyball Arena");
    	Stadium s18 = new Stadium("Volleyball Hall");
    	Competition c3 = new Competition(LocalDate.of(2024, 5, 20), LocalTime.of(17, 30), "91357", "92357", "Indoor Volleyball", null, 28, 20.0, 8);
    	Competition c8 = new Competition(LocalDate.of(2024, 5, 21), LocalTime.of(16, 45), "12468", "13468", "Beach Volleyball", null, 27, 18.0, 7);
    	Competition c13 = new Competition(LocalDate.of(2024, 5, 20), LocalTime.of(19, 15), "23579", "14579","Beach Volleyball", "Mixed Volleyball", 19, 25.0, 9);
    	Competition c18 = new Competition(LocalDate.of(2024, 5, 21), LocalTime.of(15, 45), "34680", "25680", "Sitting Volleyball", null, 13, 22.0, 3);

    	Sport basketball = new Sport("Basketball");
    	Stadium s4 = new Stadium("Basketball Arena");
    	Stadium s9 = new Stadium("Basketball Complex");
    	Stadium s14 = new Stadium("Basketball Stadium");
    	Stadium s19 = new Stadium("Basketball Hall");
    	Competition c4 = new Competition(LocalDate.of(2024, 5, 22), LocalTime.of(19, 0), "45791", "36791", "3x3 Basketball", null, 46, 35.0, 40);
    	Competition c9 = new Competition(LocalDate.of(2024, 5, 23), LocalTime.of(18, 30), "56802", "47802", "Women's Basketball", "Men's Basketball", 45, 28.0, 30);
    	Competition c14 = new Competition(LocalDate.of(2024, 5, 22), LocalTime.of(20, 30), "67913", "58913", "Slam Dunk Contest", null, 40, 30.0, 20);
    	Competition c19 = new Competition(LocalDate.of(2024, 5, 23), LocalTime.of(17, 45), "78024", "69024", "Women's Basketball", "3x3 Basketball", 50, 32.0, 1);

    	Sport judo = new Sport("Judo");
    	Stadium s5 = new Stadium("Judo Center");
    	Stadium s10 = new Stadium("Judo Arena");
    	Stadium s15 = new Stadium("Judo Hall");
    	Competition c5 = new Competition(LocalDate.of(2024, 5, 25), LocalTime.of(13, 45), "89135", "70135", "Lightweight & Heavyweight", null, 40, 40.0, 21);
    	Competition c10 = new Competition(LocalDate.of(2024, 5, 26), LocalTime.of(12, 15), "90246", "81246", "Team Judo", null, 35, 45.0, 20);
    	Competition c15 = new Competition(LocalDate.of(2024, 5, 25), LocalTime.of(16, 0), "13570", "92357", "Individual Kata", null, 40, 38.0, 4);
    	Competition c20 = new Competition(LocalDate.of(2024, 5, 26), LocalTime.of(14, 45), "12469", "13468", null, null, 35, 42.0, 1);

    	c1.setSport(athletics);
    	c6.setSport(athletics);
    	c11.setSport(athletics);
    	c16.setSport(athletics);
    	c1.setStadium(s1);
    	c6.setStadium(s6);
    	c11.setStadium(s11);
    	c16.setStadium(s16);
    	s1.setSport(athletics);
    	s6.setSport(athletics);
    	s11.setSport(athletics);
    	s16.setSport(athletics);
    	athletics.addCompetition(c1);
    	athletics.addCompetition(c6);
    	athletics.addCompetition(c11);
    	athletics.addCompetition(c16);
    	athletics.addStadium(s1);
    	athletics.addStadium(s6);
    	athletics.addStadium(s11);
    	athletics.addStadium(s16);
    	
    	c2.setSport(gymnastics);
    	c7.setSport(gymnastics);
    	c12.setSport(gymnastics);
    	c17.setSport(gymnastics);
    	c2.setStadium(s2);
    	c7.setStadium(s7);
    	c12.setStadium(s12);
    	c17.setStadium(s17);
    	s2.setSport(gymnastics);
    	s7.setSport(gymnastics);
    	s12.setSport(gymnastics);
    	s17.setSport(gymnastics);
    	gymnastics.addCompetition(c2);
    	gymnastics.addCompetition(c7);
    	gymnastics.addCompetition(c12);
    	gymnastics.addCompetition(c17);
    	gymnastics.addStadium(s2);
    	gymnastics.addStadium(s7);
    	gymnastics.addStadium(s12);
    	gymnastics.addStadium(s17);    	

    	c3.setSport(volleyball);
    	c8.setSport(volleyball);
    	c13.setSport(volleyball);
    	c18.setSport(volleyball);
    	c3.setStadium(s3);
    	c8.setStadium(s8);
    	c13.setStadium(s13);
    	c18.setStadium(s18);
    	s3.setSport(volleyball);
    	s8.setSport(volleyball);
    	s13.setSport(volleyball);
    	s18.setSport(volleyball);
    	volleyball.addCompetition(c3);
    	volleyball.addCompetition(c8);
    	volleyball.addCompetition(c13);
    	volleyball.addCompetition(c18);
    	volleyball.addStadium(s3);
    	volleyball.addStadium(s8);
    	volleyball.addStadium(s13);
    	volleyball.addStadium(s18);
    	
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
    	basketball.addCompetition(c4);
    	basketball.addCompetition(c9);
    	basketball.addCompetition(c14);
    	basketball.addCompetition(c19);
    	basketball.addStadium(s4);
    	basketball.addStadium(s9);
    	basketball.addStadium(s14);
    	basketball.addStadium(s19);
    	
    	c5.setSport(judo);
    	c10.setSport(judo);
    	c15.setSport(judo);
    	c20.setSport(judo);
    	c5.setStadium(s5);
    	c10.setStadium(s10);
    	c15.setStadium(s15);
    	s5.setSport(judo);
    	s10.setSport(judo);
    	s15.setSport(judo);
    	judo.addCompetition(c5);
    	judo.addCompetition(c10);
    	judo.addCompetition(c15);
    	judo.addCompetition(c20);
    	judo.addStadium(s5);
    	judo.addStadium(s10);
    	judo.addStadium(s15);
    	
    	usersRepository.save(admin);
    	usersRepository.save(user);
    	
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
    	
    	olympicService.makeTickets(athletics.getId(), c1.getId(), 25.0, 50);
    	olympicService.makeTickets(athletics.getId(), c6.getId(), 22.5, 40);
    	olympicService.makeTickets(athletics.getId(), c11.getId(), 20.0, 46);
    	olympicService.makeTickets(athletics.getId(), c16.getId(), 23.0, 37);    	
    	olympicService.makeTickets(gymnastics.getId(), c2.getId(), 30.0, 30);
    	olympicService.makeTickets(gymnastics.getId(), c7.getId(), 35.0, 25);
    	olympicService.makeTickets(gymnastics.getId(), c12.getId(), 32.55, 35);
    	olympicService.makeTickets(gymnastics.getId(), c17.getId(), 27.5, 30);    	
    	olympicService.makeTickets(volleyball.getId(), c3.getId(), 20.0, 28);
    	olympicService.makeTickets(volleyball.getId(), c8.getId(), 18.0, 27);
    	olympicService.makeTickets(volleyball.getId(), c13.getId(), 25.0, 19);
    	olympicService.makeTickets(volleyball.getId(), c18.getId(), 22.0, 13);    	
    	olympicService.makeTickets(basketball.getId(), c4.getId(), 35.0, 46);
    	olympicService.makeTickets(basketball.getId(), c9.getId(), 28.0, 45);
    	olympicService.makeTickets(basketball.getId(), c14.getId(), 30.0, 40);
    	olympicService.makeTickets(basketball.getId(), c19.getId(), 32.0, 50);    	
    	olympicService.makeTickets(judo.getId(), c5.getId(), 40.0, 40);
    	olympicService.makeTickets(judo.getId(), c10.getId(), 45.0, 35);
    	olympicService.makeTickets(judo.getId(), c15.getId(), 38.0, 40);
    	olympicService.makeTickets(judo.getId(), c20.getId(), 42.0, 35);
    }

}