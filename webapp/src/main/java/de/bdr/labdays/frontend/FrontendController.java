package de.bdr.labdays.frontend;

import javax.servlet.http.HttpSession;

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
	public String index(HttpSession session, Model model) {
		
		System.out.println("calling api-server " + apiserverUrl);
		
		Response response = this.restTemplate.getForObject(apiserverUrl + "/greeting?name={value}", Response.class,
				"foo");
		model.addAttribute("value", response.getContent());
		model.addAttribute("sessionId", session.getId());
		return "index";
	}

}