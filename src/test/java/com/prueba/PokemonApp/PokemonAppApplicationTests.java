package com.prueba.PokemonApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PokemonAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetPokemonByIdOrName() throws Exception {
		mockMvc.perform(get("/api/pokemon/25")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(25))
				.andExpect(jsonPath("$.name").value("pikachu"));

		mockMvc.perform(get("/api/pokemon/pikachu")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(25))
				.andExpect(jsonPath("$.name").value("pikachu"));

		mockMvc.perform(get("/api/pokemon/picachu")).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("El Pokemon con nombre o ID picachu no se encontró."));
	}

}
