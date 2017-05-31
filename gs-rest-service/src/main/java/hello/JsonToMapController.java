package hello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JsonToMapController {

	private RestTemplate restTemplate;

	@Autowired
	public JsonToMapController(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/json2map")
	public Map<String, String> getJsonToMap() {
		Map<String, String> response = restTemplate.getForObject("http://localhost:8080/map2json", HashMap.class);
		return response;
	}

}
