package diegoschi.project1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    String cityName;
    String daneCode;

    public static boolean isValidCity(City city){
        if (city == null||city.getDaneCode() == null || city.getCityName()==null) {
            return false;
        } else {
            return true;
        }
    }
}
