package br.com.digite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digite.model.Endereco;
import br.com.digite.repositories.EnderecoRepository;
import br.com.digite.service.exception.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	
	public Endereco findById(Long id) {
		Optional<Endereco> endereco = repo.findById(id);
		return endereco.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Endereco.class.getName()));
	}
	
	public Endereco insert(Endereco endereco) {
		return repo.save(endereco);
	}
	
	public List<Endereco> findAll() {
		return repo.findAll();
	}

}
