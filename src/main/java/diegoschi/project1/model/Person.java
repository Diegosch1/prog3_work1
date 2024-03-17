package diegoschi.project1.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String docType;
    private String docNum;
    private String name;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private City city;

    public static boolean validPerson(Person person) {
        boolean valid = true;
        if (person.getName() == null ||
                person.getLastName() == null ||
                person.getGender() == null ||
                person.getBirthDate() == null ||
                person.getDocType() == null ||
                person.getDocNum() == null ||
                person.getCity() == null ||
                person.getCity().getCityName() == null || 
                person.getCity().getDaneCode() == null) {
            valid = false;
        }
        return valid;
    }
}
