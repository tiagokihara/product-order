package br.com.tiagokihara.productorder.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fornecedor {

	@Id
	private String cnpj;
	private String nome;

	public Fornecedor() {
	}

	public Fornecedor(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Fornecedor that = (Fornecedor) o;
		return Objects.equals(cnpj, that.cnpj) && Objects.equals(nome, that.nome);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cnpj, nome);
	}
}
