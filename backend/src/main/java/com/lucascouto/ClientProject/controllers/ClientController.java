package com.lucascouto.ClientProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucascouto.ClientProject.dto.ClientDTO;
import com.lucascouto.ClientProject.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
		Page<ClientDTO> clients = service.findAll(pageable);
		return ResponseEntity.ok().body(clients);
	}

}
