package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

/**
 * Interface que define como o usu�rio se autenticar� no sistema.
 * @author David Arruda
 *
 */
@Repository
public interface RepositoryLogin extends Serializable {
	
	boolean autentico(String login, String senha) throws Exception;

}
