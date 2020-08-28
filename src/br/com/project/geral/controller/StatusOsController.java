package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.StatusO;
import br.com.repository.interfaces.RepositoryStatusOs;
import br.com.srv.interfaces.SrvStatusOs;

@Controller
public class StatusOsController extends InplementacaoCrud<StatusO> implements InterfaceCrud<StatusO>{

	private static final long serialVersionUID = 1L;

	@Resource
	private SrvStatusOs srvStatusOs;
	
	@Resource
	private RepositoryStatusOs repositoryStatusOs; 
	
	public void setRepositoryStatusOs(RepositoryStatusOs repositoryStatusOs) {
		this.repositoryStatusOs = repositoryStatusOs;
	}
	
	public void setSrvStatusOs(SrvStatusOs srvStatusOs) {
		this.srvStatusOs = srvStatusOs;
	}
}
