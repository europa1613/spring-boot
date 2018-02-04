package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

	private QuoteService service;

	@Autowired
	public QuoteController(QuoteService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/quote")
	public Quote getRamdomQuote() {
		return service.getRandomQuote();
	}

}
