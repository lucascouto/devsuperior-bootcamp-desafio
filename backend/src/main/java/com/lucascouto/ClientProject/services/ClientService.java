package com.lucascouto.ClientProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucascouto.ClientProject.dto.ClientDTO;
import com.lucascouto.ClientProject.entities.Client;
import com.lucascouto.ClientProject.repositories.ClientRepository;
import com.lucascouto.ClientProject.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(client -> new ClientDTO(client));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Client with id " + id + " not found!")
			);
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO create(ClientDTO clientDto) {
		Client client = new Client();
		copyClientDtoToClient(clientDto, client);
		client = repository.save(client);
		
		return new ClientDTO(client);
	}
	
	private void copyClientDtoToClient(ClientDTO dto, Client entity) {
		
		entity.setName(dto.getName());
		entity.setIncome(dto.getIncome());
		entity.setCpf(dto.getCpf());
		entity.setChildren(dto.getChildren());
		entity.setBirthDate(dto.getBirthDate());
		
	}
	
}
