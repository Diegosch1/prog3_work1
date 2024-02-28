package diegoschi.project1.services;

import org.json.JSONObject;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.model.City;
import diegoschi.project1.model.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class PersonServices {

    UptcList<Person> people = new UptcList<Person>();

    public UptcList<Person> test() {

        City city1 = new City("New York", 1);
        City city2 = new City("Los Angeles", 2);
        City city3 = new City("Chicago", 3);
        City city4 = new City("Houston", 4);
        City city5 = new City("Miami", 5);

        Person person1 = new Person("Passport", "ABC123", "John", "Doe", "Male", "1990-05-15", city1);
        Person person2 = new Person("ID", "DEF456", "Jane", "Smith", "Female", "1985-10-20", city2);
        Person person3 = new Person("Driver's License", "GHI789", "Michael", "Johnson", "Male", "1978-03-25", city3);
        Person person4 = new Person("Passport", "JKL012", "Emily", "Brown", "Female", "1995-12-03", city4);
        Person person5 = new Person("ID", "MNO345", "David", "Martinez", "Male", "1980-07-08", city5);

        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);

        return people;
    }
    public Person parsePerson(JSONObject jsonObject) {
        Person person = new Person();
        City city = new City();

        person.setDocType(jsonObject.getString("docType"));
        person.setDocNum(jsonObject.getString("docNum"));
        person.setNames(jsonObject.getString("names"));
        person.setLastNames(jsonObject.getString("lastNames"));
        person.setGender(jsonObject.getString("gender"));
        person.setBirthDate(jsonObject.getString("birthDate"));
        JSONObject birthCityObj = jsonObject.getJSONObject("city");
        city.setDaneCode(birthCityObj.getInt("daneCode"));
        city.setCityName(birthCityObj.getString("cityName"));
        person.setCity(city);
        return person;
    }
}
