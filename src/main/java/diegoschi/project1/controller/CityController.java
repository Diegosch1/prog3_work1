package diegoschi.project1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import diegoschi.project1.model.City;
import diegoschi.project1.services.CityService;
import diegoschi.project1.services.PersonService;

@RestController
@RequestMapping("/city")
public class CityController {
    PersonService personService = new PersonService();
    CityService cityService = new CityService();

    @GetMapping()
    public List<City> getCities() {
        List<City> citiesAux = cityService.getCityList();
        return citiesAux;
    }

    
    @PostMapping("/addCity")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        if (City.isValidCity(city)) {
            cityService.addCity(city);            
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            System.out.println("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/deleteCity/{daneCode}")
    public ResponseEntity<?> deleteCity(@PathVariable String daneCode) {
        if (!daneCode.equals(null)) {
            City targetCity = cityService.getCityByDaneCode(daneCode);
            cityService.deleteCity(targetCity);            
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            System.out.println("Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
