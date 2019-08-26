package com.async.asyncdemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void async_test() throws Exception {
		this.mockMvc.perform(get("/jokes/async"))
					.andDo(print())
					.andExpect(status().isOk());
	}
	
	@Test
	public void async_test_multiple() throws Exception {
		for (int i = 0; i < 100; i++)
			this.mockMvc.perform(get("/jokes/async"))
						.andDo(print())
						.andExpect(status().isOk());
	}
}