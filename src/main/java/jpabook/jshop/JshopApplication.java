package jpabook.jshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

	@Bean
	Hibernate5Module hibernate5Module() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
//		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,	true);
		return hibernate5Module;
	}

}
