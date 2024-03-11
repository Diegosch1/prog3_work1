package diegoschi.project1.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class JSONController {
    public void writePeopleToJson(UptcList<Person> people, String fileName) {
        JSONArray jsonArray = new JSONArray();

        for (Person person : people) {
            JSONObject jsonPerson = new JSONObject();
            
            jsonPerson.put("name", person.getName());
            jsonPerson.put("lastName", person.getLastName());
            jsonPerson.put("gender", person.getGender());
            jsonPerson.put("birthDate", person.getBirthDate().toString());  
                   
            jsonArray.put(jsonPerson);
        }

        try {
            Files.write(Paths.get(fileName), jsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UptcList<Person> readPeopleFromJson(String fileName) {
        UptcList<Person> people = new UptcList<>();

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);                
                
                String name = jsonObject.getString("name");
                String lastName = jsonObject.getString("lastName");
                String gender = jsonObject.getString("gender");         
                LocalDate birthDate = LocalDate.parse(jsonObject.getString("birthDate"));

                people.add(new Person(name, lastName, gender, birthDate));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }
}
