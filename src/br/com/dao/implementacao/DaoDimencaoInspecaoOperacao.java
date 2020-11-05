package br.com.dao.implementacao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.DimencaoInspecaoOperacao;
import br.com.repository.interfaces.RepositoryDimencaoInspecaoOperacao;

@Repository
public class DaoDimencaoInspecaoOperacao extends InplementacaoCrud<DimencaoInspecaoOperacao> implements RepositoryDimencaoInspecaoOperacao{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<DimencaoInspecaoOperacao> findByOperacaoId(Long idOperacao) throws Exception{
		return super.getSession().createQuery("select dim from DimencaoInspecaoOperacao dim where dim.operacaoProduto = :idOperacao")
			.setParameter("idOperacao", idOperacao).list();
	}

}
