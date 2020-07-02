package br.com.dao.implementacao;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.InstrumentoMedicao;
import br.com.repository.interfaces.RepositoryInstrumentoMedicao;

@Controller
public class DaoInstrumentoMedicao extends InplementacaoCrud<InstrumentoMedicao>
		implements RepositoryInstrumentoMedicao {

	private static final long serialVersionUID = 1L;

}
