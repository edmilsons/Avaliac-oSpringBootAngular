package br.com.edmilson.enums;

import lombok.Getter;

public enum TiposRiscosEnum {
	
	A("A"), B("B"), C("C");

@Getter public String tipoRisco;

	TiposRiscosEnum(String tipoRisco) {
		this.tipoRisco = tipoRisco;
	}
}
