package br.com.zup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.model.PontoInteresse;
import br.com.zup.repository.GenericRepository;
import br.com.zup.repository.PontoInteresseRepository;

/**
 * Servico para Ponto de Interesse
 * @author Tulio
 */
@Service
public class PontoInteresseService extends GenericService<PontoInteresse> {
	
	/** Serial version Id */
	private static final long serialVersionUID = 1L;
    
    @Autowired
    private PontoInteresseRepository pontoInteresseRepository;
    
    public List<PontoInteresse> list(){
		return pontoInteresseRepository.findAll();
    }

	@Override
	public GenericRepository<PontoInteresse> getRepository() {
		return pontoInteresseRepository;
	}
	
	public List<PontoInteresse> findByPontoInteresse(PontoInteresse pontoInteresse, Integer dMax) {
		return pontoInteresseRepository.findByPontoInteresse(pontoInteresse, dMax);
	}


}
