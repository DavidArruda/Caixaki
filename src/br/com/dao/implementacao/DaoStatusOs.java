package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.StatusO;
import br.com.repository.interfaces.RepositoryStatusOs;

@Repository
public class DaoStatusOs extends InplementacaoCrud<StatusO> implements RepositoryStatusOs {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(StatusO statusOs) throws Exception {
		super.save(statusOs);
	}
	

}
