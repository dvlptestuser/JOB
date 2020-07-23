package com.pk.assistant;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping("/")
	public String loginPage(Map<String, Object> model) {

		return "login";
	}

	@PostMapping("/home")
	public String home(@ModelAttribute UserDetails login, Model model) {
		System.out.println("" + login.getUsername());

		if ("Prakash".equalsIgnoreCase(login.getUsername()) && "Pass@1234".equals(login.getPassword())) {

			return "homepage";
		} else {
			model.addAttribute("msg", "Invalid credentials");
			return "login";
		}

	}
}
