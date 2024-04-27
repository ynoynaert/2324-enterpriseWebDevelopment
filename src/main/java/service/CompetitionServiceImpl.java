package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import domain.Competition;

public class CompetitionServiceImpl implements CompetitionService{
	
    private static List<Competition> list = new ArrayList<>();
    
    static {
    	
        List<Competition> athleticsComp = Arrays.asList(new Competition(1L, LocalDate.of(2024, 5, 10), "10:00", "BORDEAUX STADIUM" ,"4x100m", 100, 10.0, 100));
        List<Competition> gymnasticsComp = Arrays.asList(new Competition(1L, LocalDate.of(2024, 5, 10), "15:30", "BORDEAUX STADIUM" , "4x100m", 100, 10.0, 100));
        List<Competition> volleyballComp = Arrays.asList(new Competition(1L, LocalDate.of(2024, 5, 10), "10:00", "BORDEAUX STADIUM" , "4x100m", 100, 10.0, 100));
        List<Competition> basketballComp = Arrays.asList(new Competition(1L, LocalDate.of(2024, 5, 10), "15:30", "BORDEAUX STADIUM" , "4x100m", 100, 10.0, 100));
        List<Competition> judoComp = Arrays.asList(new Competition(1L, LocalDate.of(2024, 5, 10), "10:00", "BORDEAUX STADIUM" , "4x100m", 100, 10.0, 100));
    }
    
    @Override
    public List<Competition> findAll() {
        return list;
    }

    @Override
    public Competition findById(Long id) {
        return list.stream().filter(c -> id.compareTo(c.getId()) == 0).findFirst().orElse(null);
    }

    @Override
    public Competition save(Competition competition) {  
    	   
       ListIterator<Competition> it = list.listIterator();
       boolean updated = false;
       while (it.hasNext()) {
    	   Competition c = it.next();
            if (competition.getId().compareTo(c.getId())==0) {
                it.set(competition);
                updated = true;
            }
        }
        if (!updated)
        {
        	list.add(competition);
        }
        return competition;
    }
}
