package com.springBoot.examenOpdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.SportService;

@Controller
@RequestMapping("/sports")
public class SportController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SportService sportService;
	
	@GetMapping(value = "/list")
	public String listContacts(Model model) {
		model.addAttribute("sportList", sportService.findAll());
		return "list";
	}
}
