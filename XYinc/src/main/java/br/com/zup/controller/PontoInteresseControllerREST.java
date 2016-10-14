package br.com.zup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.model.PontoInteresse;
import br.com.zup.service.PontoInteresseService;
import br.com.zup.util.Constants;


@RestController
@RequestMapping(Constants.PATH_SERVICE_PONTO_INTERESSE)
public class PontoInteresseControllerREST extends GenericController<PontoInteresse> {

	@Autowired
	private PontoInteresseService pontoInteresseService;
	
	/**
	 * Servico para consulta ede POIs com base nas coordenadas de entrada x, y e a distancia maxima dMax
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @param dMax Distancia maxima dado pelo teorema de pitagoras considerando o plano cartesiano
	 * @return Lista de POIs por proximidade 
	 */
	@RequestMapping(value = "/coordenadas/{x}/{y}/{dMax}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PontoInteresse>> findByCoordenadas(@PathVariable("x") Integer x, 
			@PathVariable("y") Integer y, @PathVariable("dMax") Integer dMax) {
		
		if (x==null||y==null) {
			return new ResponseEntity<List<PontoInteresse>>(HttpStatus.BAD_REQUEST);
		}
		
		PontoInteresse pontoInteresse = new PontoInteresse();
		pontoInteresse.setCoordenadaX(x);
		pontoInteresse.setCoordenadaY(y);

		List<PontoInteresse> list = pontoInteresseService.findByPontoInteresse(pontoInteresse, dMax);
    	
        if(list==null || list.isEmpty()){
            return new ResponseEntity<List<PontoInteresse>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<PontoInteresse>>(list, HttpStatus.OK);
	}
	
	/**
	 * Metodo REST para inserir registro via GET
	 */
	@RequestMapping(value = "/add/{nome}/{x}/{y}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PontoInteresse> insert(@PathVariable("nome") String nome, 
    								   @PathVariable("x") Integer x, 
    								   @PathVariable("y") Integer y) {
        try {
        	
        	if (nome==null||x==null||y==null) {
                return new ResponseEntity<PontoInteresse>(HttpStatus.BAD_REQUEST); 
            } 
        	
        	PontoInteresse pontoInteresse = new PontoInteresse();
        	pontoInteresse.setNome(nome);
    		pontoInteresse.setCoordenadaX(x);
    		pontoInteresse.setCoordenadaY(y);
    		pontoInteresse = service.persist(pontoInteresse);
            return new ResponseEntity<PontoInteresse>(pontoInteresse, HttpStatus.CREATED);
            
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<PontoInteresse>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        
    }
 
}
