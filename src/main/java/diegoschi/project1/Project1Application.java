package diegoschi.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.uptc.ejercicio1.models.UptcList;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
		System.out.println("hello world");
		UptcList<String> list = new UptcList<>();
		list.add("auuaa");
		list.show();
	}

}
