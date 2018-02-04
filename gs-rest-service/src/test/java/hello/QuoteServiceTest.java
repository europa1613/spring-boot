package hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteServiceTest {
	
	//private static final String QUOTE_URL = "http://gturnquist-quoters.cfapps.io/api/random";
	
	/*
	 * https://spring.io/guides/gs/consuming-rest/
	 * https://github.com/spring-guides/gs-consuming-rest/blob/master/complete/src/test/java/hello/ApplicationTest.java
	 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/web/client/MockRestServiceServer.html
	 * https://stackoverflow.com/questions/31739939/restful-services-test-with-resttemplate
	 */
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@InjectMocks
	private QuoteService service;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void contextLoads() {
		assertNotNull(restTemplate);
		assertNotNull(service);
	}
	
	@Test
	public void getRandomQuote_should_Get_A_Quote() {
		Quote randomQuote = service.getRandomQuote();
		System.out.println(randomQuote);
		assertNotNull(randomQuote);
		assertEquals("success", randomQuote.getType());
		assertNotNull(randomQuote.getValue());
		assertThat(randomQuote.getValue().getId());
	}

}
