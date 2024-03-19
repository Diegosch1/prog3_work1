package diegoschi.project1.model;

import java.time.LocalDate;

import diegoschi.project1.exceptions.ProjectExeption;
import diegoschi.project1.exceptions.TypeMessage;
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

    public static void validPerson(Person person) throws ProjectExeption {
        if (person.getName() == null ||
                person.getLastName() == null ||
                person.getGender() == null ||
                person.getBirthDate() == null ||
                person.getDocType() == null ||
                person.getDocNum() == null ||
                person.getCity() == null ||
                person.getCity().getCityName() == null || 
                person.getCity().getDaneCode() == null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }
}
