package diegoschi.project1.utils;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {
    public static int getAge(LocalDate date) {
        LocalDate now = LocalDate.now();
        int aux = Period.between(date,now).getYears();
        return aux;
    }
    
}
