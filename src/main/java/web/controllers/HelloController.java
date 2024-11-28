package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Выполняю задание 2.3.1!");
		messages.add("Это Spring MVC + Hibernate приложение");
		messages.add("Версия 1.0 SNAPSHOT");
		model.addAttribute("messages", messages);
		return "index";
	}
	
}