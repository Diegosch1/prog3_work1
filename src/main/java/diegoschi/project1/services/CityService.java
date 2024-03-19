package diegoschi.project1.services;

import java.util.List;

import co.edu.uptc.ejercicio1.models.UptcList;
import diegoschi.project1.controller.JSONController;
import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.exceptions.TypeMessage;
import diegoschi.project1.model.City;
import diegoschi.project1.model.Person;

public class CityService {
    JSONController json = new JSONController();
    private UptcList<City> cities = json.readCitiesFromJson("citiesJSON.json");
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
        json.writeCitiesToJson(cities, "citiesJSON");
    }

    public void updateJSON() {
        json.writeCitiesToJson(cities, "citiesJSON");
    }

    public void deleteCity(City city) {
        if (!cityHasPersons(city)) {
            cities.remove(city);
            System.out.println(city.getCityName() + " deleted");
            json.writeCitiesToJson(cities, "citiesJSON");
        } else {
            System.out.println(city.getCityName() + " has persons registered. Delete failed");
        }
    }

}
