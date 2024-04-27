package com.springBoot.examenOpdracht;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Competition;
import domain.Sport;
import repository.CompetitionRepository;
import repository.SportRepository;

@Component
public class InitDataConfig implements CommandLineRunner {
	
	@Autowired
    private SportRepository sportRepository;
	@Autowired
	private CompetitionRepository competitionRepository;

    @Override
    public void run(String... args) {

    	Sport athletics = new Sport("Athletics");
    	Sport gymnastics = new Sport("Gymnastics");
    	Sport volleyball = new Sport("Volleybal");
    	Sport basketball = new Sport("Basketball");
    	Sport judo = new Sport("Judo");
    	
    	Competition c1 = new Competition(LocalDate.of(2024, 5, 15), "14:00", "Olympic Stadium", "12345", "12346", "Sprint & High Jump", null,500, 25.0, 500);
    	Competition c6 = new Competition(LocalDate.of(2024, 5, 17), "15:30", "Athletics Stadium", "12354", "12355", "Long Jump & Discus Throw", null, 400, 22.5, 400);
    	Competition c11 = new Competition(LocalDate.of(2024, 5, 16), "13:00", "Olympic Athletics Park", "12364", "12365", "Marathon & Shot Put", null, 600, 20.0, 600);
    	Competition c16 = new Competition(LocalDate.of(2024, 5, 17), "11:30", "Olympic Track Field", "12374", "12375", "Hurdles & Pole Vault", null, 700, 23.0, 700);
    	
    	Competition c2 = new Competition(LocalDate.of(2024, 5, 18), "11:00", "Gymnastics Arena", "12347", "12348", "Floor Exercise & Rings", null, 300, 30.0, 300);
    	Competition c7 = new Competition(LocalDate.of(2024, 5, 19), "10:00", "Gymnastics Palace", "12356", "12357", "Vault & Parallel Bars", null, 250, 35.0, 250);
    	Competition c12 = new Competition(LocalDate.of(2024, 5, 18), "14:30", "Gymnastics Hall", "12366", "12367", "Balance Beam & Horizontal Bar", null, 350, 32.5, 350);
    	Competition c17 = new Competition(LocalDate.of(2024, 5, 19), "16:00", "Gymnastics Center", "12376", "12377", "Trampoline & Uneven Bars", null, 300, 27.5, 300);
    	
    	Competition c3 = new Competition(LocalDate.of(2024, 5, 20), "17:30", "Volleyball Hall", "12349", "12350", "Indoor Volleyball", null, 800, 20.0, 800);
    	Competition c8 = new Competition(LocalDate.of(2024, 5, 21), "16:45", "Volleyball Center", "12358", "12359", "Beach Volleyball", null, 700, 18.0, 700);
    	Competition c13 = new Competition(LocalDate.of(2024, 5, 20), "19:15", "Volleyball Arena", "12368", "12369","Beach Volleyball", "Mixed Volleyball", 900, 25.0, 900);
    	Competition c18 = new Competition(LocalDate.of(2024, 5, 21), "15:45", "Volleyball Complex", "12378", "12379", "Sitting Volleyball", null, 800, 22.0, 800);
    	
    	Competition c4 = new Competition(LocalDate.of(2024, 5, 22), "19:00", "Basketball Arena", "12351", "12351", "3x3 Basketball", null, 600, 35.0, 600);
    	Competition c9 = new Competition(LocalDate.of(2024, 5, 23), "18:30", "Basketball Complex", "12360", "12361", "Women's Basketball", "Men's Basketball", 450, 28.0, 450);
    	Competition c14 = new Competition(LocalDate.of(2024, 5, 22), "20:30", "Basketball Stadium", "12370", "12371", "Slam Dunk Contest", null, 550, 30.0, 550);
    	Competition c19 = new Competition(LocalDate.of(2024, 5, 23), "17:45", "Basketball Hall", "12380", "12381", "Women's Basketball", "3x3 Basketball", 500, 32.0, 500);
    	
    	Competition c5 = new Competition(LocalDate.of(2024, 5, 25), "13:45", "Judo Center", "12352", "12353", "Lightweight & Heavyweight", null, 400, 40.0, 400);
    	Competition c10 = new Competition(LocalDate.of(2024, 5, 26), "12:15", "Judo Arena", "12362", "12363", "Team Judo", null, 350, 45.0, 350);
    	Competition c15 = new Competition(LocalDate.of(2024, 5, 25), "16:00", "Judo Hall", "12372", "12373", "Individual Kata", null, 400, 38.0, 400);
    	Competition c20 = new Competition(LocalDate.of(2024, 5, 26), "14:45", "Judo Arena", "12382", "12383", null, null, 350, 42.0, 350);

    	athletics.addCompetition(c1);
    	athletics.addCompetition(c6);
    	athletics.addCompetition(c11);
    	athletics.addCompetition(c16);
    	
    	gymnastics.addCompetition(c2);
    	gymnastics.addCompetition(c7);
    	gymnastics.addCompetition(c12);
    	gymnastics.addCompetition(c17);
    	
    	volleyball.addCompetition(c3);
    	volleyball.addCompetition(c8);
    	volleyball.addCompetition(c13);
    	volleyball.addCompetition(c18);
    	
    	basketball.addCompetition(c4);
    	basketball.addCompetition(c9);
    	basketball.addCompetition(c14);
    	basketball.addCompetition(c19);
    	
    	judo.addCompetition(c5);
    	judo.addCompetition(c10);
    	judo.addCompetition(c15);
    	judo.addCompetition(c20);
    	
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
    	
    	sportRepository.save(athletics);
    	sportRepository.save(gymnastics);
    	sportRepository.save(volleyball);
    	sportRepository.save(basketball);
    	sportRepository.save(judo);
    }

}