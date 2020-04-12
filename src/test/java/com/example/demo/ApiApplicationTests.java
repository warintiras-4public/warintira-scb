package com.example.demo;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ApiController.class)
public class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApiService service;

	@Test
	public void helloTest() throws Exception {
		when(service.hello()).thenReturn("Hello, Mock");
		this.mockMvc
			.perform(get("/hello"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello, Mock")));
	}

	@Test
	public void encodeTest() throws Exception {
		String result = "b->k->l->k";
		when(service.encode(anyString())).thenReturn(result);
		this.mockMvc
			.perform(post("/encode").contentType(MediaType.APPLICATION_JSON).content("Sk"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString(result)));
			verify(service).encode("Sk");
		}
}