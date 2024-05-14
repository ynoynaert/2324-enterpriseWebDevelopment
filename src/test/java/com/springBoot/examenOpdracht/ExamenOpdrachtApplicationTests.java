package com.springBoot.examenOpdracht;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import perform.PerformRest;

@SpringBootTest
@AutoConfigureMockMvc
class ExamenOpdrachtApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PerformRest performRest;

	@Test
	public void testHomePageRedirect() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isFound()).andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	public void testAccessDeniedPage() throws Exception {
		mockMvc.perform(get("/403")).andExpect(status().isOk()).andExpect(view().name("403"));
	}

}
