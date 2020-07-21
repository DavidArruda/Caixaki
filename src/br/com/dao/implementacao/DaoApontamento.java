package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.Apontamento;
import br.com.repository.interfaces.RepositoryApontamento;

@Repository
public class DaoApontamento extends InplementacaoCrud<Apontamento> implements RepositoryApontamento {

	private static final long serialVersionUID = 1L;

}
