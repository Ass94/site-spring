package br.com.digite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digite.model.Cliente;
import br.com.digite.model.Endereco;
import br.com.digite.repositories.ClienteRepository;
import br.com.digite.repositories.EnderecoRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public void iniciarBancoDados() {
		Endereco end = new Endereco(null, "06550-000", "Rua 8", "80", "JD. Bom Jesus", "Pirapora", "São Paulo");
		Cliente cliente = new Cliente(null, "Alex", "alex94tu@gmail.com", "(11)957818337", "Instalaçãor de Internet");
		cliente.setEndereco(end);
		end.setCliente(cliente);
		enderecoRepository.save(end);
		clienteRepository.save(cliente);
	}

}
