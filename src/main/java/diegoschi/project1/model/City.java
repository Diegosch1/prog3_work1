package diegoschi.project1.model;

import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.exceptions.TypeMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class City {
    String cityName;
    String daneCode;
    public City(String daneCode, String cityName){
        this.daneCode = daneCode;
        this.cityName = cityName;
    }

    public static void isValidCity(City city) throws ProjectExeption{
        if (city == null||city.getDaneCode() == null || city.getCityName()==null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        } 
    }
}
