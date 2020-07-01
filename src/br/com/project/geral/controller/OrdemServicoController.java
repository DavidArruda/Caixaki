package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.OrdemServico;
import br.com.repository.interfaces.RepositoryOrdemServico;

@Controller
public class OrdemServicoController extends InplementacaoCrud<OrdemServico> implements InterfaceCrud<OrdemServico> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryOrdemServico repositoryOrdemServico;

	public void setRepositoryOrdemServico(RepositoryOrdemServico repositoryOrdemServico) {
		this.repositoryOrdemServico = repositoryOrdemServico;
	}

}
