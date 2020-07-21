package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryApontamento;
import br.com.srv.interfaces.SrvApontamento;

@Service
public class SrvApontamentoImpl implements SrvApontamento {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	RepositoryApontamento repositoryApontamento;

}
