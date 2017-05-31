package hello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapToJsonController {

	@RequestMapping("/map2json")
	public Map<String, String> getMapJson() {
		return new HashMap<String, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				put("A", "a");
				put("B", "b");
				put("C", "c");
				put("D", "d");
				put("E", "e");
				put("F", "f");
				put("G", "g");
				put("H", "h");
				put("I", "i");
			}
		};
	}
}
