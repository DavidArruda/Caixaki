package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.RepositotyCidade;

@Repository
public class DaoCidade extends InplementacaoCrud<Cidade> implements RepositotyCidade {

	private static final long serialVersionUID = 1L;

}
