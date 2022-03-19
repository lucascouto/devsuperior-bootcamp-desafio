package com.lucascouto.ClientProject.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucascouto.ClientProject.dto.ClientDTO;
import com.lucascouto.ClientProject.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(
			@RequestParam(defaultValue = "0") Integer page, 
			@RequestParam(defaultValue = "12") Integer linesPerPage, 
			@RequestParam(defaultValue = "ASC") String direction, 
			@RequestParam(defaultValue = "name") String orderBy
		) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ClientDTO> clients = service.findAll(pageRequest);
		return ResponseEntity.ok().body(clients);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDto) {
		ClientDTO client = service.create(clientDto);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(client.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(client);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDto, @PathVariable Long id) {
		ClientDTO client = service.update(clientDto, id);
		return ResponseEntity.ok().body(client);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
