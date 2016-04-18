package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to run the service
 * @author Ashish
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Main {

	public static void main(String[] args) 
	{
		SpringApplication.run(Main.class,args);

	}

}
