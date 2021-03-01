package jpabook.jshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class JshopApplication {

	public static void main(String[] args) {
		
//		Hello hello = new Hello();
//		hello.setTest("test");
//		String data = hello.getTest();
//		System.out.println("data = " + data);
//		
		SpringApplication.run(JshopApplication.class, args);
	}

}
