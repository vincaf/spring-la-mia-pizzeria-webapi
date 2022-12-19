package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/")
	public String index(Model model) {

		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		return "index";
	}

	@GetMapping("/create")
	public String createPizza(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);

		return "pizza-create";
	}

	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/create";
		}

		pizzaService.save(pizza);
		return "redirect:/pizza";
	}

	@GetMapping("/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {

		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();

		model.addAttribute("pizza", pizza);

		return "pizza-edit";
	}

	@PostMapping("/edit")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/edit/" + pizza.getId();
		}

		pizzaService.save(pizza);
		return "redirect:/pizza";
	}

	@GetMapping("/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {

		pizzaService.deleteById(id);

		return "redirect:/index";
	}

	@GetMapping("/search")
	public String getSearchPizzaByName(Model model, @RequestParam(name = "query", required = false) String query) {

		List<Pizza> pizzas = query == null ? pizzaService.findAll() : pizzaService.findByName(query);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("query", query);
		return "pizza-search";
	}
}