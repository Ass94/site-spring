package br.com.digite.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String mensagem;

	@OneToOne(mappedBy = "cliente")
	private Endereco endereco;

	public Cliente() {
	}

	public Cliente(Long id, String nome, String email, String telefone, String mensagem) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.mensagem = mensagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Olá, ");
		builder.append("Você recebeu uma solicitação de assinatura no site, segue os dados para contato abaixo:\n");
		builder.append("Nome: ");
		builder.append(getNome());
		builder.append("E-mail: ");
		builder.append(getEmail());
		builder.append("Endereço:\n");
		builder.append("CEP: ");
		builder.append(getEndereco().getCep());
		builder.append("Logradouro: ");
		builder.append(getEndereco().getLogradouro());
		builder.append("Número: ");
		builder.append(getEndereco().getNumero());
		builder.append("Bairro: ");
		builder.append(getEndereco().getBairro());
		builder.append("Cidade: ");
		builder.append(getEndereco().getCidade());
		builder.append("Estado: ");
		builder.append(getEndereco().getEstado());
		builder.append("Mensagem: ");
		builder.append(getMensagem());
		return builder.toString();
		
	}

}
