package diegoschi.project1.controller;


import co.edu.uptc.controller.JMFileReader;
import co.edu.uptc.controller.JMFileWriter;
import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.model.JMArray;
import co.edu.uptc.model.JMObject;
import diegoschi.project1.model.City;
import diegoschi.project1.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@SuppressWarnings("")
public class JSONController {
    public void writePeopleToJson(UptcList<Person> people, String fileName) {
        JMArray<JMObject> jsonArray = new JMArray<>();

        for (Person person : people) {
            JMObject jsonPerson = new JMObject();
            JMObject jsonCity = new JMObject();

            jsonPerson.put("docType", person.getDocType());
            jsonPerson.put("docNum", person.getDocNum());
            jsonPerson.put("name", person.getName());
            jsonPerson.put("lastName", person.getLastName());
            jsonPerson.put("gender", person.getGender());
            jsonPerson.put("birthDate", person.getBirthDate().toString());
            // jsonCity attibutes
            jsonCity.put("cityName", person.getCity().getCityName());
            jsonCity.put("daneCode", person.getCity().getDaneCode());

            jsonPerson.put("city", jsonCity);

            jsonArray.add(jsonPerson);
        }

        //dirección del directorio principal
        String projectDirectory = System.getProperty("user.dir");                        
        System.out.println(projectDirectory);

        // Escribir el contenido del JSON al archivo
        JMFileWriter writer = new JMFileWriter();
        writer.writeToFile(projectDirectory+"/"+fileName, jsonArray);    
    }

    public UptcList<Person> readPeopleFromJson(String fileName) {
        UptcList<Person> people = new UptcList<>();

        try {
            String projectDirectory = System.getProperty("user.dir");
            String filePath = projectDirectory+"/"+fileName;

            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            JMFileReader reader = new JMFileReader();
            JMArray<JMObject> jsonArray = reader.getJMArrayfromString(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JMObject JMObject = (JMObject) jsonArray.get(i);

                String docType = (String) JMObject.get("docType");
                String docNum = (String) JMObject.get("docNum");
                String name = (String) JMObject.get("name");
                String lastName = (String) JMObject.get("lastName");
                String gender = (String) JMObject.get("gender");
                LocalDate birthDate = LocalDate.parse((String) JMObject.get("birthDate"));
                JMObject jsonCity = (JMObject) JMObject.getInnerJMObject("city");
                City city = new City((String) jsonCity.get("daneCode"), (String) jsonCity.get("cityName"));

                people.add(new Person(docType, docNum, name, lastName, gender, birthDate, city));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }

    public void writeCitiesToJson(UptcList<City> cities, String fileName) {
        JMArray<JMObject> jsonArray = new JMArray<>();

        for (City city : cities) {
            JMObject jsonCity = new JMObject();

            jsonCity.put("daneCode", city.getDaneCode());
            jsonCity.put("cityName", city.getCityName());

            jsonArray.add(jsonCity);
        }     
            //dirección del directorio principal
            String projectDirectory = System.getProperty("user.dir");                        
            System.out.println(projectDirectory);

            // Escribir el contenido del JSON al archivo
            JMFileWriter writer = new JMFileWriter();
            writer.writeToFile(projectDirectory+"/"+fileName, jsonArray);        
    }

    public UptcList<City> readCitiesFromJson(String fileName) {
        UptcList<City> cities = new UptcList<>();

        try {
            String projectDirectory = System.getProperty("user.dir");
            String filePath = projectDirectory+"/"+fileName;

            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            JMFileReader reader = new JMFileReader();
            JMArray<JMObject> jsonArray = reader.getJMArrayfromString(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JMObject JMObject = (JMObject) jsonArray.get(i);
                String cityName = (String) JMObject.get("cityName");
                String daneCode = (String) JMObject.get("daneCode");
                City city = new City(daneCode, cityName);
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
