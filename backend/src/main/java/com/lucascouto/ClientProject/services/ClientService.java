package com.lucascouto.ClientProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucascouto.ClientProject.dto.ClientDTO;
import com.lucascouto.ClientProject.entities.Client;
import com.lucascouto.ClientProject.repositories.ClientRepository;
import com.lucascouto.ClientProject.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(client -> new ClientDTO(client));
	}

	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Client with id " + id + " not found!")
			);
		return new ClientDTO(client);
	}
	
}
