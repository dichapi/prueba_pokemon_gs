package com.prueba.PokemonApp.servicio;

import com.prueba.PokemonApp.dto.PokemonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    @Autowired
    private RestTemplate restTemplate;

    public PokemonDTO getPokemonById(int id) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;
        try {
            PokemonDTO response = restTemplate.getForObject(url, PokemonDTO.class);
            System.out.println("response: " + response);
            return response;
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            return null;
        }
    }

    public PokemonDTO getPokemonByName(String name) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;
        try {
            PokemonDTO response = restTemplate.getForObject(url, PokemonDTO.class);
            System.out.println("response: " + response);
            return response;
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            return null;
        }
    }
}
