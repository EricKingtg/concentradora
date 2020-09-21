package com.mx.org.concentradora.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.org.concentradora.entity.Response;
import com.mx.org.concentradora.model.RequestModel;
import com.mx.org.concentradora.service.TaeService;

@RestController
@RequestMapping(value = "/v1")
public class ConcentradoraController {

	@Autowired
	@Qualifier("taeService")
	private TaeService taeService;

	@PostMapping("/tae")
	public ResponseEntity<Response> pagos(@RequestBody RequestModel in) {
		return new ResponseEntity<Response>(taeService.taeTelcel(in), HttpStatus.OK);
	}

	@GetMapping("/tae/{idtrx}")
	public ResponseEntity<RequestModel> transacciones(@PathVariable Long idtrx) {
		Optional<RequestModel> response = taeService.consultaTae(idtrx);
		if(response.isPresent()) {
			return new ResponseEntity<RequestModel>(response.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}