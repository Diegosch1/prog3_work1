package diegoschi.project1.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.controller.JSONController;
import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.exceptions.TypeMessage;
import diegoschi.project1.model.Person;
import diegoschi.project1.utils.DateUtil;

@Service
public class PersonService {

  JSONController json;
  private UptcList<Person> people;
  String peopleConst;

  @Autowired
  public PersonService(@Value("${main_peopleJSON}") String main_peopleJSON) {
    this.json = new JSONController();
    this.people = json.readPeopleFromJson(main_peopleJSON);
    this.peopleConst = main_peopleJSON;
    setPeopleConst(main_peopleJSON);
    loadPeople();
  }

  public List<Person> loadPeople() {
    return people;
  }

  public List<Person> orderName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personNameComparator());
    return peopleAux;
  }

  public List<Person> getPeople() {
    List<Person> peopleAux = new UptcList<Person>();
    for (Person person : this.people) {
      peopleAux.add(person);
    }
    return peopleAux;
  }

  public List<Person> orderLastName(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personLastNameComparator());
    return peopleAux;
  }

  public void addPerson(Person person) {
    people.add(person);
    updateJSON();
  }

  public void deletePerson(Person person) {
      people.remove(person);
      updateJSON();
  }

  public void updateJSON(){
    json.writePeopleToJson(people, getPeopleConst());
  }

  public void editPerson(int index, Person person) {    
      people.set(index, person);
      updateJSON();
  }

  public List<Person> orderAge(List<Person> peopleAux) {
    Collections.sort(peopleAux, ComparatorService.personAgeComparator());
    return peopleAux;
  }

  public List<Person> getOver18() {
    List<Person> peopleAux = new UptcList<Person>();
    for (Person person : people) {
      if (DateUtil.getAge(person.getBirthDate()) >= 18) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public List<Person> getPersonGender(String gender) {
    List<Person> peopleAux = new UptcList<Person>();
    for (Person person : people) {
      if (person.getGender().equals(gender)) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public List<Person> getPeopleName(String name) {
    List<Person> peopleAux = new UptcList<Person>();
    for (Person person : people) {
      if (person.getName().equals(name)) {
        peopleAux.add(person);
      }
    }
    return peopleAux;
  }

  public Person getPersonByDocNum(String docNum) throws ProjectExeption {    
    for (Person person : people) {
      if (person.getDocNum().equals(docNum)) {
        return person;
      }
    }
    throw new ProjectExeption(TypeMessage.NOT_FOUND);
  }

  public String getPeopleConst() {
    return peopleConst;
  }

  public void setPeopleConst(String peopleConst) {
    this.peopleConst = peopleConst;
  }


  
}
