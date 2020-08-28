package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.model.classes.StatusO;

@Transactional
@Repository
public interface RepositoryStatusOs extends Serializable {
	
	void save(StatusO statusOs) throws Exception;

}
