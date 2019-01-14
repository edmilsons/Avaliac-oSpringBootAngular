package br.com.edmilson.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.edmilson.enums.TiposRiscosEnum;
import br.com.edmilson.model.Cliente;
import br.com.edmilson.model.ResponseModel;
import br.com.edmilson.service.ClienteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ClienteController {

	@Autowired
	private ClienteService pessoaRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param pessoa
	 * @return
	 */

	@RequestMapping(value = "/pessoa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel<Cliente> salvar(@Valid @RequestBody Cliente pessoa) {

		try {
			if (TiposRiscosEnum.B == pessoa.getRisco()) {
				pessoa.setTaxa(10.0);
			} else if (TiposRiscosEnum.C == pessoa.getRisco()) {
				pessoa.setTaxa(20.0);
			} else {
				pessoa.setTaxa(0.0);
			}
			this.pessoaRepository.save(pessoa);

			return new ResponseModel<Cliente>(1, "Registro salvo com sucesso!",pessoa);

		} catch (Exception e) {

			return new ResponseModel<Cliente>(0, e.getMessage(),pessoa);
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * 
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/pessoa", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel<Cliente> atualizar(@Valid @RequestBody Cliente pessoa) {

		try {

			Cliente update = pessoaRepository.findOne(pessoa.getId());
			if (update.getId() > 0) {
				if (TiposRiscosEnum.B == pessoa.getRisco()) {
					pessoa.setTaxa(10.0);
				} else if (TiposRiscosEnum.C == pessoa.getRisco()) {
					pessoa.setTaxa(20.0);
				} else {
					pessoa.setTaxa(0.0);
				}

				update.setLimiteCredito(pessoa.getLimiteCredito());
				update.setNome(pessoa.getNome());
				update.setRisco(pessoa.getRisco());
				this.pessoaRepository.save(update);

			} else {
				return new ResponseModel<Cliente>(0, "Registro não Encontrado!",pessoa);
			}

			return new ResponseModel<Cliente>(1, "Registro atualizado com sucesso!",pessoa);

		} catch (Exception e) {

			return new ResponseModel<Cliente>(0, e.getMessage(),pessoa);
		}
	}

	/**
	 * CONSULTAR TODAS AS PESSOAS
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pessoa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Cliente> consultar() {

		return this.pessoaRepository.findAll();
	}

	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Cliente buscar(@PathVariable("id") Long id) {

		return this.pessoaRepository.findOne(id);
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel<Cliente>  excluir(@PathVariable("id") Long id) {

		Cliente pessoaModel = pessoaRepository.findOne(id);

		try {

			pessoaRepository.delete(pessoaModel);

			return new ResponseModel<Cliente>(1, "Registro excluido com sucesso!",pessoaModel);

		} catch (Exception e) {
			return new ResponseModel<Cliente>(0, e.getMessage(),pessoaModel);
		}
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/pessoa/riscos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<String> listarRiscos() {

		List<TiposRiscosEnum> lista = Arrays.asList(TiposRiscosEnum.values());
		List<String> riscos = new ArrayList<String>();

		for (int i = 0; i < lista.size(); i++) {
			riscos.add(lista.get(i).getTipoRisco());
		}
		return riscos;
	}

}
