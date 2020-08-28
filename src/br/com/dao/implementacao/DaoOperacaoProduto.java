package br.com.dao.implementacao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.OperacaoProduto;
import br.com.repository.interfaces.RepositoryOperacaoProduto;

@Repository
public class DaoOperacaoProduto extends InplementacaoCrud<OperacaoProduto> implements RepositoryOperacaoProduto {

	private static final long serialVersionUID = 1L;

	@Override
	public Long proximaOperacao(Long produtoId, Long idOperacaoAtual) throws Exception {
		return (Long) super.getSession().createCriteria(OperacaoProduto.class)
		.add(Restrictions.eq(produtoId.toString(), idOperacaoAtual.toString()))
		.setMaxResults(1).uniqueResult();
		
	}

	@Override
	public Long operacaoInicial(String idProduto) throws Exception {
		return Long.parseLong(super.getSession().createQuery("select min(op.id) from OperacaoProduto op where op.produto = " + idProduto)
				.uniqueResult().toString());
	}
	
	

}
