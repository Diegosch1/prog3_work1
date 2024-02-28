package diegoschi.project1.model;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    String docType;
    String docNum;
    String names;
    String lastNames;
    String gender;
    String birthDate;
    City city;
}

