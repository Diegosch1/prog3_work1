package diegoschi.project1.services;

import java.util.Comparator;
import diegoschi.project1.model.*;

public class ComparatorService {
    
    public static  Comparator<Person> personNameComparator(){
        return new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    public static  Comparator<Person> personLastNameComparator(){
        return new Comparator<Person>() {

            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        };
    }

    public static Comparator<Person> personAgeComparator() {

        return new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getBirthDate().compareTo(o1.getBirthDate());
            }
        };
        
       
    }

}
