package br.com.edmilson.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class SwaggerUIController {
	@RequestMapping(value = "/")
		public String index() {
			return "redirect:swagger-ui.html";
		}
}

