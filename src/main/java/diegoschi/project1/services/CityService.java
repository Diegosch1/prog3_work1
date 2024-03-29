package diegoschi.project1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.controller.JSONController;
import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.exceptions.TypeMessage;
import diegoschi.project1.model.City;
import diegoschi.project1.model.Person;

@Service
public class CityService {
    JSONController json;
    private UptcList<City> cities;
    PersonService pService;
    String cityConst;
    String peopleConst;

    @Autowired
public CityService(@Value("${main_citiesJSON}") String main_citiesJSON,
                   @Value("${main_peopleJSON}") String main_peopleJSON) {
    this.json = new JSONController();
    this.cities = json.readCitiesFromJson(main_citiesJSON);
    this.pService = new PersonService(main_peopleJSON);
    this.cityConst = main_citiesJSON;
    this.peopleConst = main_peopleJSON;

    setCityConst(main_citiesJSON);
    loadCityList();
}

    public UptcList<City> loadCityList() {
        return cities;
    }

    public List<City> getCityList() {
        List<City> citiesAux = new UptcList<City>();
        for (City city : cities) {
            citiesAux.add(city);
        }
        return citiesAux;
    }

    public City getCityByDaneCode(String code) throws ProjectExeption{
        List<City> cities = getCityList();
        for (City city : cities) {
            if (city.getDaneCode().equals(code)) {
                return city;
            }
        }
        throw new ProjectExeption(TypeMessage.NOT_FOUND);
    }

    public boolean isSameCity(City city1, City city2) {
        return (city1.getDaneCode().equals(city2.getDaneCode())) && (city1.getCityName().equals(city2.getCityName()));
    }

    public boolean cityHasPersons(City city) {
        List<Person> people = pService.getPeople();
        for (Person person : people) {
            if (isSameCity(city, person.getCity())) {
                return true;
            }
        }
        return false;
    }

    public void addCity(City city) {
        cities.add(city);
        updateJSON();
    }

    public void updateJSON() {
        json.writeCitiesToJson(cities, getCityConst());
    }

    public void deleteCity(City city) {
        if (!cityHasPersons(city)) {
            cities.remove(city);
            System.out.println(city.getCityName() + " deleted");
            updateJSON();
        } else {
            System.out.println(city.getCityName() + " has persons registered. Delete failed");
        }
    }

    public String getCityConst() {
        return cityConst;
    }

    public void setCityConst(String cityConst) {
        this.cityConst = cityConst;
    }

    public String getPeopleConst() {
        return peopleConst;
    }

    public void setPeopleConst(String peopleConst) {
        this.peopleConst = peopleConst;
    }
    

}
