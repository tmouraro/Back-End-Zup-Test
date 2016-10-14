package br.com.zup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.model.BaseEntity;
import br.com.zup.service.GenericService;

@RestController
public abstract class GenericController<T extends BaseEntity> {
	
	@Autowired
	protected GenericService<T> service;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<T>> findAll() {
		
		List<T> list = service.findAll();
    	
        if(list==null || list.isEmpty()){
            return new ResponseEntity<List<T>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
	}
	

	/**
	 * Metodo REST para inserir registro 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody T entity, UriComponentsBuilder ucBuilder) {
 
        try {
        	if (entity==null) {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
            } 
        	service.persist(entity);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
            
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        
    }
	
}
