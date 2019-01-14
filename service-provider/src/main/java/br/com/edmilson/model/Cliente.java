/**
 * 
 */
package br.com.edmilson.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.edmilson.enums.TiposRiscosEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author edmilsons
 *
 */
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private long id;
	@Getter
	@Setter
	@NotNull
	@Size(min = 2, message = "O nome deve ter pelo menos dois caracteres")
	private String nome;
	@Getter
	@Setter
	@NotNull
	private BigDecimal limiteCredito;
	@Getter
	@Setter
	private Double taxa;
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	@NotNull
	private TiposRiscosEnum risco;

}
