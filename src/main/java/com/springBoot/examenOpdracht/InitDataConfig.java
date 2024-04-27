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
    	
    	Competition c1 = new Competition(LocalDate.of(2024, 5, 15), "14:00", "Olympic Stadium", "13579", "14579", "Sprint & High Jump", null, 50, 25.0, 48);
    	Competition c6 = new Competition(LocalDate.of(2024, 5, 17), "15:30", "Athletics Stadium", "24680", "25680", "Long Jump & Discus Throw", null, 40, 22.5, 1);
    	Competition c11 = new Competition(LocalDate.of(2024, 5, 16), "13:00", "Olympic Athletics Park", "35791", "36791", "Marathon & Shot Put", null, 46, 20.0, 1);
    	Competition c16 = new Competition(LocalDate.of(2024, 5, 17), "11:30", "Olympic Track Field", "46802", "47802", "Hurdles & Pole Vault", null, 37, 23.0, 15);
    	
    	Competition c2 = new Competition(LocalDate.of(2024, 5, 18), "11:00", "Gymnastics Arena", "57913", "58913", "Floor Exercise & Rings", null, 30, 30.0, 16);
    	Competition c7 = new Competition(LocalDate.of(2024, 5, 19), "10:00", "Gymnastics Palace", "68024", "69024", "Vault & Parallel Bars", null, 25, 35.0, 23);
    	Competition c12 = new Competition(LocalDate.of(2024, 5, 18), "14:30", "Gymnastics Hall", "79135", "70135", "Balance Beam & Horizontal Bar", null, 35, 32.5, 20);
    	Competition c17 = new Competition(LocalDate.of(2024, 5, 19), "16:00", "Gymnastics Center", "80246", "81246", "Trampoline & Uneven Bars", null, 30, 27.5, 10);
    	
    	Competition c3 = new Competition(LocalDate.of(2024, 5, 20), "17:30", "Volleyball Hall", "91357", "92357", "Indoor Volleyball", null, 28, 20.0, 8);
    	Competition c8 = new Competition(LocalDate.of(2024, 5, 21), "16:45", "Volleyball Center", "12468", "13468", "Beach Volleyball", null, 27, 18.0, 7);
    	Competition c13 = new Competition(LocalDate.of(2024, 5, 20), "19:15", "Volleyball Arena", "23579", "14579","Beach Volleyball", "Mixed Volleyball", 19, 25.0, 9);
    	Competition c18 = new Competition(LocalDate.of(2024, 5, 21), "15:45", "Volleyball Complex", "34680", "25680", "Sitting Volleyball", null, 13, 22.0, 3);
    	
    	Competition c4 = new Competition(LocalDate.of(2024, 5, 22), "19:00", "Basketball Arena", "45791", "36791", "3x3 Basketball", null, 46, 35.0, 40);
    	Competition c9 = new Competition(LocalDate.of(2024, 5, 23), "18:30", "Basketball Complex", "56802", "47802", "Women's Basketball", "Men's Basketball", 45, 28.0, 30);
    	Competition c14 = new Competition(LocalDate.of(2024, 5, 22), "20:30", "Basketball Stadium", "67913", "58913", "Slam Dunk Contest", null, 40, 30.0, 20);
    	Competition c19 = new Competition(LocalDate.of(2024, 5, 23), "17:45", "Basketball Hall", "78024", "69024", "Women's Basketball", "3x3 Basketball", 50, 32.0, 1);
    	
    	Competition c5 = new Competition(LocalDate.of(2024, 5, 25), "13:45", "Judo Center", "89135", "70135", "Lightweight & Heavyweight", null, 40, 40.0, 21);
    	Competition c10 = new Competition(LocalDate.of(2024, 5, 26), "12:15", "Judo Arena", "90246", "81246", "Team Judo", null, 35, 45.0, 20);
    	Competition c15 = new Competition(LocalDate.of(2024, 5, 25), "16:00", "Judo Hall", "13570", "92357", "Individual Kata", null, 40, 38.0, 4);
    	Competition c20 = new Competition(LocalDate.of(2024, 5, 26), "14:45", "Judo Arena", "12468", "13468", null, null, 35, 42.0, 1);

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