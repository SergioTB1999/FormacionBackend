package com.ejercicio3.block6pathvariableheaders.controller;


import com.ejercicio3.block6pathvariableheaders.model.Resultados;
import com.ejercicio3.block6pathvariableheaders.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class Controller {


    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return user;
    }

    @GetMapping("user/{id}")
    public String devuelveId(@PathVariable int id){
        return "La id del usuario es: " + id;
    }

    @PutMapping("/post")
    public ResponseEntity<Map<String,String>> devuelveMapa (@RequestParam Map<String,String> parametros){
        return ResponseEntity.ok(parametros);
    }

    @GetMapping("/header")
    public ResponseEntity<String> devuelveHeader(
            @RequestHeader(value = "h1", required = true) String param1,
            @RequestHeader(value = "H2", required = true) String param2){
        String responseMessage = "El parámetro h1 es: " + param1 + " y el parámetro H2 es: " + param2;

        return ResponseEntity.ok(responseMessage);
    }


    @PostMapping("/all")
    public ResponseEntity<Resultados> devuelveTodosLosDatos(
            @RequestBody(required = false) String body,
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> requestParams) {

        List<String> headerList = new ArrayList<>(headers.values());
        List<String> requestParamList = new ArrayList<>(requestParams.values());

        Resultados response = new Resultados(body, headerList, requestParamList);

        return ResponseEntity.ok(response);
    }




}
