package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.MedicaoApontamento;
import br.com.repository.interfaces.RepositoryMedicaoApontamento;

@Controller
public class MedicaoApontamentoController extends InplementacaoCrud<MedicaoApontamento>
		implements InterfaceCrud<MedicaoApontamento> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryMedicaoApontamento repositoryMedicaoApontamento;

	public void setRepositoryMedicaoApontamento(RepositoryMedicaoApontamento repositoryMedicaoApontamento) {
		this.repositoryMedicaoApontamento = repositoryMedicaoApontamento;
	}
}
