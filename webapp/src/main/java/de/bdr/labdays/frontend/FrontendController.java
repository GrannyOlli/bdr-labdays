package de.bdr.labdays.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;

@Controller
public class FrontendController {

	public static final class Response {
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}

	@Value("${apiserver.url}")
	private String apiserverUrl;

	private RestTemplate restTemplate;

	public FrontendController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@GetMapping("/app")
	public String index(Model model) {
		Response response = this.restTemplate.getForObject(apiserverUrl + "/greeting?name={value}", Response.class,
				"foo");
		model.addAttribute("value", response.getContent());
		return "index";
	}

}