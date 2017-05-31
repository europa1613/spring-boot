package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private RestTemplate restTemplate;

	@Autowired
	public QuoteService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public Quote getRamdomQuote() {
		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote.toString());
		return quote;
	}

}
