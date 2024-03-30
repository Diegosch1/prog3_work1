package diegoschi.project1.dtos;

import java.time.LocalDate;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.exceptions.TypeMessage;
import diegoschi.project1.model.Person;
import diegoschi.project1.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
     private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private int age;


    public static PersonDto fromPerson(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setLastName(person.getLastName());
        personDto.setGender(person.getGender());
        personDto.setBirthDate(person.getBirthDate());
        personDto.setAge(DateUtil.getAge(person.getBirthDate()));
        return personDto;
    }


    public static UptcList<PersonDto> fromPeople(UptcList <Person> people) {
        UptcList <PersonDto> personDto = new UptcList<>();
        for (Person person : people) {
            personDto.add(PersonDto.fromPerson(person));
        }
        return personDto;
    }

    public static Person fromPersonDto(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        person.setGender(personDto.getGender());
        person.setBirthDate(personDto.getBirthDate());
        return person;
    }

    public static void validaPerson(PersonDto personDto) throws ProjectExeption {
        if (personDto.getName() == null || 
        personDto.getLastName() == null || 
        personDto.getGender() == null || 
        personDto.getBirthDate() == null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }


}
