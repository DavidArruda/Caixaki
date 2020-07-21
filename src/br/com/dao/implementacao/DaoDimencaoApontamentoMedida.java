package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.DimencaoApontamentoMedida;
import br.com.repository.interfaces.RepositoryDimencaoApontamentoMedida;

@Repository
public class DaoDimencaoApontamentoMedida extends InplementacaoCrud<DimencaoApontamentoMedida> implements RepositoryDimencaoApontamentoMedida {

	private static final long serialVersionUID = 1L;

}
