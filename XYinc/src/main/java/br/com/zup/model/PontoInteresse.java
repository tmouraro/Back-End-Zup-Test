package br.com.zup.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "PONTO_INTERESSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PontoInteresse.findAll", query = "SELECT p FROM PontoInteresse p"),
    @NamedQuery(name = "PontoInteresse.findById", query = "SELECT p FROM PontoInteresse p WHERE p.id = :id"),
    @NamedQuery(name = "PontoInteresse.findByCoordenadas", 
    	query = "SELECT p FROM PontoInteresse p WHERE "
    		+"SQRT((p.coordenadaX-:coordenadaX)*(p.coordenadaX-:coordenadaX)+(p.coordenadaY-:coordenadaY)*(p.coordenadaY-:coordenadaY)) < :dMax")
})
public class PontoInteresse extends BaseEntity {

	/** Serial Verison Id  */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOME")
    private String nome;
    
    @Column(name = "COORDENADA_X")
    private Integer coordenadaX;
    
    @Column(name = "COORDENADA_Y")
    private Integer coordenadaY;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the coordenadaX
	 */
	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	/**
	 * @param coordenadaX the coordenadaX to set
	 */
	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	/**
	 * @return the coordenadaY
	 */
	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	/**
	 * @param coordenadaY the coordenadaY to set
	 */
	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
    
    
}
