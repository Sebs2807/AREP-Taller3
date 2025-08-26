package eci.escuelaing.edu.co.controllers;

import eci.escuelaing.edu.co.annotations.*;

@RestController
public class HelloController {

    @GetMapping("/hola")
    public String saludo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hola " + name;
    }
}
