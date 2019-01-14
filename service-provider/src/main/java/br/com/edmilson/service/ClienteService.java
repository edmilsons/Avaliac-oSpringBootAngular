package br.com.edmilson.service;

import java.util.List;

import br.com.edmilson.model.Cliente;

public interface ClienteService {
	
void save(Cliente pessoa);
	
	void delete(Cliente pessoa);
	
	List<Cliente> findAll();
	
	Cliente findOne(Long id);
	
	boolean isExiste(Cliente pessoa);

}
