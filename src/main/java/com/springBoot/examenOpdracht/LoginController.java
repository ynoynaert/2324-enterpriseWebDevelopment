package com.springBoot.examenOpdracht;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		log.info("Get login");

		if (error != null) {
			model.addAttribute("error", "Foute naam of paswoord!");
		}
		if (logout != null) {
			model.addAttribute("msg", "Je bent succesvol uitgelogd.");
		}
		return "logIn";
	}
}
