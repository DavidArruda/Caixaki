package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryOperacaoProduto;
import br.com.repository.interfaces.RepositoryOrdemServico;
import br.com.srv.interfaces.SrvOrdemServico;

@Service
public class SrvOrdemServicoImpl implements SrvOrdemServico {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryOrdemServico repositoryOrdemServico;
	
	@Resource
	private RepositoryOperacaoProduto repositoryOperacaoProduto;

	@Override
	public Long statusInicial(String idProduto) throws Exception {
		return repositoryOperacaoProduto.operacaoInicial(idProduto);
	}
}
