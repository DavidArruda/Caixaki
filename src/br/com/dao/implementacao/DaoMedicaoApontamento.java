package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.MedicaoApontamento;
import br.com.repository.interfaces.RepositoryMedicaoApontamento;

@Repository
public class DaoMedicaoApontamento extends InplementacaoCrud<MedicaoApontamento> implements RepositoryMedicaoApontamento {
	private static final long serialVersionUID = 1L;

}
