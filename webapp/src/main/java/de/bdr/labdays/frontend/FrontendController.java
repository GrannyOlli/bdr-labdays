package de.bdr.labdays.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

	@GetMapping("/app")
	public String index() {
		return "index";
	}

}