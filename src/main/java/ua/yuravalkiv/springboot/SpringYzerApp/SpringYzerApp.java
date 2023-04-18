package ua.yuravalkiv.springboot.SpringYzerApp;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Disabled
public class SpringYzerApp {

	@Test
	public static void main(String[] args) {


		SpringApplication.run(SpringYzerApp.class, args);
	}

}
