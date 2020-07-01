package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface que define as operações de CRUD
 * @author David Arruda
 *
 */
@Transactional
@Repository
public interface RepositoryOrdemServico extends Serializable{

}
