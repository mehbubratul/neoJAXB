package com.mehbub.neoJAXB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class NeoJaxbApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoJaxbApplication.class, args);

//		ApplicationContext ctx = SpringApplication.run(NeoJaxbApplication.class, args);
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
	}

}
