package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import domain.Sport;

public class SportServiceImpl implements SportService {

    private static List<Sport> list = new ArrayList<>();
    
    static {
    	
        List<String> athleticsDisciplines = Arrays.asList("Running", "Long Jump", "High Jump", "Shot Put");
        List<String> gymnasticsDisciplines = Arrays.asList("Artistic Gymnastics", "Rhythmic Gymnastics", "Trampoline");
        List<String> volleyballCompetitions = Arrays.asList("Indoor", "Beach", "National League");
        List<String> judoDisciplines = Arrays.asList("Throwing", "Grappling", "Ground Fighting");
        
        List<String> athleticsComp = Arrays.asList("5/8/2024 18:00 Parijs");
        List<String> gymnasticsComp = Arrays.asList("5/8/2024 18:00 Parijs");
        List<String> volleyballComp = Arrays.asList("5/8/2024 18:00 Parijs");
        List<String> basketballComp = Arrays.asList("5/8/2024 18:00 Parijs");
        List<String> judoComp = Arrays.asList("5/8/2024 18:00 Parijs");

        list.add(new Sport(1L, "Athletics", athleticsDisciplines, athleticsComp));
        list.add(new Sport(2L, "Gymnastics", gymnasticsDisciplines, gymnasticsComp));
        list.add(new Sport(3L, "Volleyball", volleyballCompetitions, volleyballComp));
        list.add(new Sport(4L, "Basketball", null, basketballComp));
        list.add(new Sport(5L, "Judo", judoDisciplines, judoComp));
    }

    @Override
    public List<Sport> findAll() {
        return list;
    }

    @Override
    public Sport findById(Long id) {
        return list.stream().filter(c -> id.compareTo(c.getId()) == 0).findFirst().orElse(null);
    }

    @Override
    public Sport save(Sport contact) {  
    	   
       ListIterator<Sport> it = list.listIterator();
       boolean updated = false;
       while (it.hasNext()) {
    	   Sport c = it.next();
            if (contact.getId().compareTo(c.getId())==0) {
                it.set(contact);
                updated = true;
            }
        }
        if (!updated)
        {
        	list.add(contact);
        }
        return contact;
    }
}
