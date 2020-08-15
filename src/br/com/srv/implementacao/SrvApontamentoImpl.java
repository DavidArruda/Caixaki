package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.model.classes.Apontamento;
import br.com.repository.interfaces.RepositoryApontamento;
import br.com.repository.interfaces.RepositoryOperacaoProduto;
import br.com.srv.interfaces.SrvApontamento;

@Service
public class SrvApontamentoImpl implements SrvApontamento {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	RepositoryApontamento repositoryApontamento;
	
	//@Autowired
	//RepositoryOperacaoProduto repositoryOperacaoProduto;

	/*
	@Override
	public Apontamento apontar(Apontamento apontamento) throws Exception {
		return repositoryApontamento.apontar(apontamento);
	}

	@Override
	public Long atualizaStatusOs(Long idProduto, Long idOperacaoAtual) throws Exception {
		return repositoryOperacaoProduto.proximaOperacao(idProduto, idOperacaoAtual);
	}
	*/
	
}
