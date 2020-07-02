package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.DimencaoInspecaoOperacao;
import br.com.repository.interfaces.RepositoryDimencaoInspecaoOperacao;

@Controller
public class DimencaoInspecaoOperacaoController extends InplementacaoCrud<DimencaoInspecaoOperacao>
		implements InterfaceCrud<DimencaoInspecaoOperacao> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryDimencaoInspecaoOperacao repositoryDimencaoInspecaoOperacao;

	public void setRepositoryDimencaoInspecaoOperacao(
			RepositoryDimencaoInspecaoOperacao repositoryDimencaoInspecaoOperacao) {
		this.repositoryDimencaoInspecaoOperacao = repositoryDimencaoInspecaoOperacao;
	}
}
