package diegoschi.project1.services;

import java.util.Collections;
import java.util.List;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.controller.JSONController;
import diegoschi.project1.model.Person;
import diegoschi.project1.utils.DateUtil;

public class PersonService {
  JSONController json = new JSONController();
  private UptcList<Person> people = json.readPeopleFromJson("peopleJSON");

  public PersonService() {
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

public List<Person> getPeopleGender(String gender) {
  List<Person> peopleAux = new UptcList<Person>();
  for (Person person : people) {
    if (person.getGender().equals(gender)) {
      peopleAux.add(person);
    }
  }
  return peopleAux;
}



}
