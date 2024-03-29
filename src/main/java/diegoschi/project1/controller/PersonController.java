package diegoschi.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.model.Person;
import diegoschi.project1.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

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
    public ResponseEntity<Object> addNormalPerson(@RequestBody Person person) {
        try {
            Person.validPerson(person);
            personService.addPerson(person);
            personService.updateJSON();
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());

        }
    }

    @DeleteMapping("/deletePerson/{docNum}")
    public ResponseEntity<Object> deleteNormalPerson(@PathVariable String docNum) {
        try {
            Person targetPerson = personService.getPersonByDocNum(docNum);
            personService.deletePerson(targetPerson);
            personService.updateJSON();
            System.out.println(targetPerson.getName() + " was deleted successfully");

            return ResponseEntity.status(HttpStatus.OK).body(targetPerson);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PutMapping("/editPerson/{docNum}")
    public ResponseEntity<Object> editNormalPerson(@PathVariable String docNum, @RequestBody Person updatedPerson) {
        try {
            Person targetPerson = personService.getPersonByDocNum(docNum);

            targetPerson.setLastName(updatedPerson.getLastName());
            targetPerson.setGender(updatedPerson.getGender());
            targetPerson.setCity(updatedPerson.getCity());
            targetPerson.setDocType(updatedPerson.getDocType());
            targetPerson.setName(updatedPerson.getName());
            targetPerson.setBirthDate(updatedPerson.getBirthDate());

            personService.updateJSON();

            return ResponseEntity.status(HttpStatus.OK).body(targetPerson);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
