package diegoschi.project1.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.model.City;
import diegoschi.project1.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONController {
    public void writePeopleToJson(UptcList<Person> people, String fileName) {
        JSONArray jsonArray = new JSONArray();

        for (Person person : people) {
            JSONObject jsonPerson = new JSONObject();
            jsonPerson.put("docType", person.getDocType());
            jsonPerson.put("docNum", person.getDocNum());
            jsonPerson.put("names", person.getNames());
            jsonPerson.put("lastNames", person.getLastNames());
            jsonPerson.put("gender", person.getGender());
            jsonPerson.put("birthDate", person.getBirthDate());

            //create JSONObject to store person.getCity();
            JSONObject jsonCity = new JSONObject();
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
                String names = jsonObject.getString("names");
                String lastNames = jsonObject.getString("lastNames");
                String gender = jsonObject.getString("gender");
                String birthDate = jsonObject.getString("birthDate");

                //read the city object
                JSONObject city = jsonObject.getJSONObject("city");
                String cityName = city.getString("cityName");
                int daneCode = city.getInt("daneCode");
                
                people.add(new Person(docType, docNum, names, lastNames, gender, birthDate, new City(cityName,daneCode)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }
}
