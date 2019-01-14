package br.com.edmilson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edmilson.model.Cliente;
import br.com.edmilson.repository.ClienteRepository;

@Service
public class ClienteImpl implements ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void save(Cliente pessoa) {
		// TODO Auto-generated method stub
		clienteRepository.save(pessoa);
	}

	@Override
	public void delete(Cliente pessoa) {
		// TODO Auto-generated method stub
		clienteRepository.delete(pessoa);
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		List<Cliente> lista = clienteRepository.findAll();

		return lista;
	}

	@Override
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		Cliente item = clienteRepository.getOne(id);
		return item;
	}

	@Override
	public boolean isExiste(Cliente pessoa) {
		// TODO Auto-generated method stub
		return clienteRepository.existsById(pessoa.getId());
	}

	

}
