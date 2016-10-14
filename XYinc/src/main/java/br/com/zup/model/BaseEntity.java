package br.com.zup.model;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */

public class BaseEntity implements Serializable {
    
	/** Serial Verison Id  */
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador da entidade 
     */
    private Integer id;

    public Integer getId() {
        return id;
    }	

    public void setId(Integer id) {
        this.id = id;
    }
    
}
