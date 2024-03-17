package diegoschi.project1.services;

import java.util.List;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.controller.JSONController;
import diegoschi.project1.model.City;
import diegoschi.project1.model.Person;

public class CityService {
    JSONController json = new JSONController();
    private UptcList<City> cities = json.readCitiesFromJson("citiesJSON");
    PersonService pService = new PersonService();

    public CityService() {
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

    public City getCityByDaneCode(String code) {
        List<City> cities = getCityList();
        for (City city : cities) {
            if (city.getDaneCode().equals(code)) {                
                return city;
            }
        }
        return null;
    }

    public boolean cityHasPersons(City city) {
        List<Person> persons = pService.getPeople();
        boolean thereIsPeople = false;
        for (Person person : persons) {
            if ((person.getCity().getDaneCode()).equals(city.getDaneCode())) {
                thereIsPeople = true;
            }
        }
        return thereIsPeople;
    }
    
    public void addCity(City city) {
        cities.add(city);
        json.writeCitiesToJson(cities, "citiesJSON");
    }

    public void updateJSON(){
        json.writeCitiesToJson(cities, "citiesJSON");
    }

    public void deleteCity(City city) {
        System.out.println(city.getDaneCode());
        System.out.println(city.getCityName());
        if (!cityHasPersons(city)) {
            System.out.println("City has persons registered. Delete failed");            
        } else {            
            cities.remove(city);
            json.writeCitiesToJson(cities, "citiesJSON");
        }
    }

}
