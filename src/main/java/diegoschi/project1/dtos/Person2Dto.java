package diegoschi.project1.dtos;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.model.Person;
import diegoschi.project1.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person2Dto {
     private String name;
    private String lastName;
    private int age;


    public static Person2Dto fromPerson(Person person) {
        Person2Dto person2Dto = new Person2Dto();
        person2Dto.setName(person.getName());
        person2Dto.setLastName(person.getLastName());
        person2Dto.setAge(DateUtil.getAge(person.getBirthDate()));
        return person2Dto;
    }


    public static UptcList<Person2Dto> fromPeople(UptcList <Person> people) {
        UptcList <Person2Dto> personDto = new UptcList<>();
        for (Person person : people) {
            personDto.add(Person2Dto.fromPerson(person));
        }
        return personDto;
    }

}
