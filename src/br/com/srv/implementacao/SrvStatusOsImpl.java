package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryOperacaoProduto;
import br.com.repository.interfaces.RepositoryStatusOs;
import br.com.srv.interfaces.SrvStatusOs;

@Service
public class SrvStatusOsImpl  implements SrvStatusOs{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryOperacaoProduto repositoryOperacaoProduto;
	
	@Resource
	private RepositoryStatusOs repositoryStatusOs;

	@Override
	public Long atualizaStatusOs(Long idProduto, Long idOperacaoAtual) throws Exception {
		return repositoryOperacaoProduto.proximaOperacao(idProduto, idOperacaoAtual);
	}

}
