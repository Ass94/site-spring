package br.com.digite.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digite.model.Cliente;
import br.com.digite.model.dto.ClienteDTO;
import br.com.digite.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin("*")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clientes = service.findAll();
		return ResponseEntity.ok().body(clientes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody ClienteDTO dto) {
		Cliente cliente = service.fromDTO(dto);
		cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
