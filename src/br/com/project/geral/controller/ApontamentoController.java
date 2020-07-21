package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Apontamento;
import br.com.repository.interfaces.RepositoryApontamento;

@Controller
public class ApontamentoController extends InplementacaoCrud<Apontamento> implements InterfaceCrud<Apontamento> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryApontamento repositoryApontamento;
	
	public void setRepositoryApontamento(RepositoryApontamento repositoryApontamento) {
		this.repositoryApontamento = repositoryApontamento;
	}
}
