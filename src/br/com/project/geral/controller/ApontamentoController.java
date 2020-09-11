package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Apontamento;
import br.com.project.model.classes.StatusO;
import br.com.repository.interfaces.RepositoryApontamento;
import br.com.srv.interfaces.SrvApontamento;
import br.com.srv.interfaces.SrvStatusOs;

@Controller
public class ApontamentoController extends InplementacaoCrud<Apontamento> implements InterfaceCrud<Apontamento> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryApontamento repositoryApontamento;
	
	@Resource
	private SrvStatusOs srvStatusOs;
	
	@Resource
	private SrvApontamento srvApontamento;
	
	public Apontamento apontarOs(Apontamento apontamento, StatusO statusOs) throws Exception {
		return srvApontamento.apontar(apontamento, statusOs);
	}
	
	public void setRepositoryApontamento(RepositoryApontamento repositoryApontamento) {
		this.repositoryApontamento = repositoryApontamento;
	}
	
}