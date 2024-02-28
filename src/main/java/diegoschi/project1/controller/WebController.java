package diegoschi.project1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.model.Person;
import diegoschi.project1.services.PersonServices;

@RestController
@RequestMapping("/")
public class WebController {

    PersonServices pServices = new PersonServices();
    JSONController jsonController = new JSONController();

    @GetMapping("/people")
    public UptcList<Person> getPerson() {    
        pServices.test();
        jsonController.writePeopleToJson(pServices.getPeople(),"peopleJSON.json");
        return jsonController.readPeopleFromJson("peopleJSON.json");        
    }
    @GetMapping("/person/sortedByName")
    public void getJSONPerso() {

    }
    @GetMapping("/person/sortedByAge")
    public void getJSONPerson() {

    }   
}