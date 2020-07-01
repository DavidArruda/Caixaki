package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.OrdemServico;
import br.com.repository.interfaces.RepositoryOrdemServico;

/**
 * Classe responsável por implementar a interface repository
 * @author David Arruda
 *
 */
@Repository
public class DaoOrdemServico extends InplementacaoCrud<OrdemServico> implements RepositoryOrdemServico {

	private static final long serialVersionUID = 1L;

}
