package diegoschi.project1.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.model.City;
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
            JSONObject jsonCity = new JSONObject();
            
            jsonPerson.put("docType", person.getDocType());
            jsonPerson.put("docNum", person.getDocNum());                
            jsonPerson.put("name", person.getName());
            jsonPerson.put("lastName", person.getLastName());
            jsonPerson.put("gender", person.getGender());
            jsonPerson.put("birthDate", person.getBirthDate().toString());
            //jsonCity attibutes 
            jsonCity.put("cityName", person.getCity().getCityName());
            jsonCity.put("daneCode", person.getCity().getDaneCode());

            jsonPerson.put("city", jsonCity);
                   
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
                
                String docType = jsonObject.getString("docType");
                String docNum = jsonObject.getString("docNum");
                String name = jsonObject.getString("name");
                String lastName = jsonObject.getString("lastName");
                String gender = jsonObject.getString("gender");         
                LocalDate birthDate = LocalDate.parse(jsonObject.getString("birthDate"));
                JSONObject jsonCity = jsonObject.getJSONObject("city"); 
                City city = new City(jsonCity.getString("cityName"),jsonCity.getString("daneCode"));
                
                people.add(new Person(docType, docNum, name, lastName, gender, birthDate, city));
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }

    public void writeCitiesToJson(UptcList<City> cities, String fileName) {
        JSONArray jsonArray = new JSONArray();

        for (City city : cities) {
            JSONObject jsonCity = new JSONObject();

            jsonCity.put("daneCode", city.getDaneCode());            
            jsonCity.put("cityName", city.getCityName());
                   
            jsonArray.put(jsonCity);
        }

        try {
            Files.write(Paths.get(fileName), jsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UptcList<City> readCitiesFromJson(String fileName) {
        UptcList<City> cities = new UptcList<>();

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);                            
                City city = new City(jsonObject.getString("cityName"),jsonObject.getString("daneCode"));                
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
