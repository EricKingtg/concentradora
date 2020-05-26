package com.mx.org.b.concentradora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.org.b.concentradora.entity.Usuarios;

@RestController
public class TestController {

	@GetMapping("/usuarios")
	public ResponseEntity<Usuarios> usuarios(){
		Usuarios user = new Usuarios("Test","Testpass");
		return new ResponseEntity<Usuarios>(user, HttpStatus.OK);
	}
	
}
