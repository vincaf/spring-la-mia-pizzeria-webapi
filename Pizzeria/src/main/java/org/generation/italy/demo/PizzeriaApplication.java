package org.generation.italy.demo;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Pizza pizza1 = new Pizza("Margherita", "pomodoro, mozzarella, basilico fresco, sale e olio");
		Pizza pizza2 = new Pizza("Marinara", "pomodoro, aglio, origano, olio");
		Pizza pizza3 = new Pizza("Calzone", "mozzarella, prosciutto, funghi e origano");

		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);

		List<Pizza> pizzas = pizzaService.findAll();

		System.err.println(pizzas);
		
		for (Pizza pizza : pizzas) {
			System.err.println("\n" + pizza);
		}
	}
}
