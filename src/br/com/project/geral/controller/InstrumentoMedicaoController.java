package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.InstrumentoMedicao;
import br.com.repository.interfaces.RepositoryInstrumentoMedicao;

@Controller
public class InstrumentoMedicaoController extends InplementacaoCrud<InstrumentoMedicao>
		implements InterfaceCrud<InstrumentoMedicao> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryInstrumentoMedicao repositoryInstrumentoMedicao;

}
