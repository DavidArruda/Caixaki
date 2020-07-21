package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.DimencaoApontamentoMedida;
import br.com.repository.interfaces.RepositoryDimencaoApontamentoMedida;

@Controller
public class DimencaoApontamentoMedidaController extends InplementacaoCrud<DimencaoApontamentoMedida>
		implements InterfaceCrud<DimencaoApontamentoMedida> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryDimencaoApontamentoMedida repositoryDimencaoApontamentoMedida;

	public void setRepositoryDimencaoApontamentoMedida(
			RepositoryDimencaoApontamentoMedida repositoryDimencaoApontamentoMedida) {
		this.repositoryDimencaoApontamentoMedida = repositoryDimencaoApontamentoMedida;
	}

}
