package br.com.edmilson.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseModel<E> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6910422564822353831L;
	private int codigo;
	private String mensagem;
	private E objeto;

	public ResponseModel(int codigo, String mensagem, E objeto) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.objeto = objeto;

	}

}
