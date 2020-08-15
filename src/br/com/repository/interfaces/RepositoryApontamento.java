package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.model.classes.Apontamento;

@Repository
@Transactional
public interface RepositoryApontamento extends Serializable{

	/** 
	 * Método para apontar
	 * @param apontamento
	 * @return Apontamento
	 * @throws Exception 
	 */
	Apontamento apontar(Apontamento apontamento) throws Exception;
	
}
