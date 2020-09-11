package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.OperacaoProduto;
import br.com.repository.interfaces.RepositoryOperacaoProduto;

@Controller
public class OperacaoProdutoController extends InplementacaoCrud<OperacaoProduto> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryOperacaoProduto repositoryOperacaoProduto;
	
	public OperacaoProduto consultarProximaOp(Long produtoId, Long idOperacaoAtual, int nOperacaoAtual) throws Exception {
		return repositoryOperacaoProduto.proximaOperacao(produtoId, idOperacaoAtual, nOperacaoAtual);
	}
	
	public void setRepositoryOperacaoProduto(RepositoryOperacaoProduto repositoryOperacaoProduto) {
		this.repositoryOperacaoProduto = repositoryOperacaoProduto;
	}

}
