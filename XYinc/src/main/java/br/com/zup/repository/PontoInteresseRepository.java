package br.com.zup.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.model.PontoInteresse;

/**
 *
 * @author Tulio
 */
@Repository
public class PontoInteresseRepository extends GenericRepository<PontoInteresse> {
    
	/** Serial version Id */
	private static final long serialVersionUID = 1L; 

	@Transactional(readOnly = true)
	public List<PontoInteresse> findByPontoInteresse(PontoInteresse pontoInteresse, Integer dMax) {

		TypedQuery<PontoInteresse> query = entityManager.createNamedQuery("PontoInteresse.findByCoordenadas", PontoInteresse.class);
		query.setParameter("coordenadaX", pontoInteresse.getCoordenadaX());
		query.setParameter("coordenadaY", pontoInteresse.getCoordenadaY());
		query.setParameter("dMax", Double.valueOf(dMax));

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
