package com.prueba.PokemonApp.controlador;

import com.prueba.PokemonApp.dto.ErrorDTO;
import com.prueba.PokemonApp.dto.PokemonDTO;
import com.prueba.PokemonApp.servicio.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService servicioPokemon;

    @GetMapping("/{idOrName}")
    public ResponseEntity<Object> getPokemonByIdOrName(@PathVariable(value = "idOrName") String idOrName)    {
        System.out.println("Ejecutando controlador...");
        try {
            PokemonDTO pokemon = null;
            if (idOrName.matches("\\d+")) {
                pokemon = servicioPokemon.getPokemonById(Integer.parseInt(idOrName));
            } else {
                pokemon = servicioPokemon.getPokemonByName(idOrName);
            }
            if (pokemon != null)
                return ResponseEntity.ok(pokemon);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("El Pokemon con nombre o ID " + idOrName + " no se encontró."));

        } catch (NumberFormatException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno.");
        }
    }
}
