package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.OperacaoProduto;
import br.com.project.model.classes.OrdemServico;
import br.com.repository.interfaces.RepositoryOrdemServico;
import br.com.repository.interfaces.RepositoryStatusOs;
import br.com.srv.interfaces.SrvOrdemServico;

@Controller
public class OrdemServicoController extends InplementacaoCrud<OrdemServico> implements InterfaceCrud<OrdemServico> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryOrdemServico repositoryOrdemServico;
	
	@Resource
	RepositoryStatusOs repositoryStatusOs;
	
	@Resource
	OperacaoProdutoController operacaoProdutoController; 
	
	@Resource
	private SrvOrdemServico srvOrdemServico;

	public Long consultaIdOpInicial(String idProduto) throws Exception {
		return srvOrdemServico.statusInicial(idProduto);
	}
	
	/**
	 * Consulta a operação inicial
	 * @param idProduto
	 * @return OperacaoProduto
	 * @throws Exception
	 */
	public OperacaoProduto consultaOpInicial(String idProduto) throws Exception {
		return  operacaoProdutoController.findObjById(OperacaoProduto.class, Long.parseLong(idProduto));
	}

}
