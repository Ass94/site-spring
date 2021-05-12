package br.com.digite.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digite.model.Cliente;
import br.com.digite.model.Endereco;
import br.com.digite.model.dto.ClienteDTO;
import br.com.digite.repositories.ClienteRepository;
import br.com.digite.repositories.EnderecoRepository;
import br.com.digite.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private EmailService emailService;

	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		endRepo.save(cliente.getEndereco());
		emailService.sendConfirmationHtmlEmail(cliente);
		return cliente;
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getTelefone(), dto.getMensagem());
		Endereco end = new Endereco(null, dto.getCep(), dto.getLogradouro(), dto.getNumero(), dto.getBairro(), dto.getCidade(), dto.getEstado());
		cliente.setEndereco(end);
		end.setCliente(cliente);
		return cliente;
	}

}
