package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.DimencaoInspecaoOperacao;
import br.com.repository.interfaces.RepositoryDimencaoInspecaoOperacao;

@Repository
public class DaoDimencaoInspecaoOperacao extends InplementacaoCrud<DimencaoInspecaoOperacao> implements RepositoryDimencaoInspecaoOperacao{

	private static final long serialVersionUID = 1L;

}
