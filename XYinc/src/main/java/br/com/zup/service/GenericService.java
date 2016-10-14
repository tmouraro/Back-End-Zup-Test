package br.com.zup.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.zup.model.BaseEntity;
import br.com.zup.repository.GenericRepository;

@Service
public abstract class GenericService<T extends BaseEntity> implements Serializable {
	
	/** Serial version Id */
	private static final long serialVersionUID = 1L;
	
	public abstract GenericRepository<T> getRepository();

	
    public List<T> findAll() {
        return getRepository().findAll();
    }
    
    public T persist(T entity) {
        return (T)getRepository().persist(entity);
    }
 
    public void merge(T entity) {
    	getRepository().merge(entity);
    }
 
    public void remove(T entity){
    	getRepository().remove(entity);
    }
 
    public void removeById(Integer id){
    	getRepository().removeById(id);
    }
 
    public T findById(Integer id){
        return (T)getRepository().findById(id);
    }
    
    public T findById(Integer id, Class<T> klass){
        return (T)getRepository().findById(id, klass);
    }

}
