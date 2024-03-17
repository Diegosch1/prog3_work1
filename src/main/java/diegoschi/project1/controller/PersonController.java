package diegoschi.project1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import diegoschi.project1.dtos.Person2Dto;
import diegoschi.project1.dtos.PersonDto;
import diegoschi.project1.model.Person;
import diegoschi.project1.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
    PersonService personService = new PersonService();

    @GetMapping()
    public List<Person> getPeople() {
        List<Person> peopleAux = personService.getPeople();
        return peopleAux;
    }

    @GetMapping("/all2")
    public List<Person2Dto> getPeople2() {
        List<Person> peopleAux = personService.getPeople();
        return Person2Dto.fromPeople(peopleAux);
    }

    @GetMapping("/orderName")
    public List<PersonDto> getPeopleOrderName() {
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(personService.orderName(peopleAux));
    }

    @GetMapping("/orderLastName")
    public List<PersonDto> getPeopleOrderLastName() {
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(personService.orderLastName(peopleAux));
    }

    @GetMapping("/orderAge")
    public List<PersonDto> getPeopleOrderAge() {
        List<Person> peopleAux = personService.getPeople();
        return PersonDto.fromPeople(personService.orderAge(peopleAux));
    }

    @GetMapping("/over18")
    public List<PersonDto> getPeopleOver18() {
        return PersonDto.fromPeople(personService.getOver18());
    }

    @GetMapping("/findByGender/{gender}")
    public List<PersonDto> getPersonGender(@PathVariable String gender) {
        return PersonDto.fromPeople(personService.getPersonGender(gender));
    }

    @GetMapping("/findByName/{name}")
    public List<PersonDto> getPeopleName(@PathVariable String name) {
        return PersonDto.fromPeople(personService.getPeopleName(name));
    }

    @PostMapping("/addPerson")
    public ResponseEntity<?> addNormalPerson(@RequestBody Person person) {
        if (Person.validPerson(person)) {
            personService.addPerson(person);
            System.out.println(person.getName() + " was added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            System.out.println("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/deletePerson/{docNum}")
    public ResponseEntity<?> deleteNormalPerson(@PathVariable String docNum) {
        if (!docNum.equals(null)) {
            Person targetPerson = personService.getPersonByDocNum(docNum);
            personService.deletePerson(targetPerson);
            System.out.println(targetPerson.getName() + " was deleted successfully");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            System.out.println("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/editPerson/{docNum}")
public ResponseEntity<?> editNormalPerson(@PathVariable String docNum, @RequestBody Person updatedPerson) {
    if (updatedPerson != null) {
        
        Person targetPerson = personService.getPersonByDocNum(docNum);

        if (targetPerson != null) {
            
            targetPerson.setLastName(updatedPerson.getLastName());
            targetPerson.setGender(updatedPerson.getGender());
            targetPerson.setCity(updatedPerson.getCity());
            targetPerson.setDocType(updatedPerson.getDocType());
            targetPerson.setName(updatedPerson.getName());
            targetPerson.setBirthDate(updatedPerson.getBirthDate());

            personService.updateJSON();

            System.out.println(targetPerson.getName() + " was edited successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            System.out.println("Person with document number " + docNum + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } else {
        System.out.println("Bad Request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


    @PostMapping("/addPersonDto")
    public ResponseEntity<?> addPersonDto(@RequestBody PersonDto personDto) {
        if (PersonDto.validaPerson(personDto)) {
            personService.addPerson(PersonDto.fromPersonDto(personDto));
            System.out.println(personDto.getName() + " was added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            System.out.println("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
