package br.com.project.geral.controller;

import java.math.BigInteger;
import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<StatusO> consultarStatusPorOs(String idOs) throws Exception{
		return super.getSession().createQuery("select status from StatusO status where status.ordemServico = " + idOs).list();
	}
	
	public void deletarComCondicao(BigInteger idOs) throws Exception{
		String hqlDelete = "delete StatusO status where status.id = :idStatus";
		super.getSession().createQuery(hqlDelete).setBigInteger("idStatus", idOs).executeUpdate();
	}
	
	public void setRepositoryStatusOs(RepositoryStatusOs repositoryStatusOs) {
		this.repositoryStatusOs = repositoryStatusOs;
	}
	
	public void setSrvStatusOs(SrvStatusOs srvStatusOs) {
		this.srvStatusOs = srvStatusOs;
	}
}
