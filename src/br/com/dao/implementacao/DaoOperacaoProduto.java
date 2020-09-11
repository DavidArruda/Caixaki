package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.project.model.classes.OperacaoProduto;
import br.com.repository.interfaces.RepositoryOperacaoProduto;

@Repository
public class DaoOperacaoProduto extends InplementacaoCrud<OperacaoProduto> implements RepositoryOperacaoProduto {

	private static final long serialVersionUID = 1L;

	@Override
	public OperacaoProduto proximaOperacao(Long produtoId, Long idOperacaoAtual, int nOperacaoAtual) throws Exception {
		var sql = "select op from OperacaoProduto op where op.produto = " +produtoId+ " and op.id > " +idOperacaoAtual+ "and op.nOperacao > " + nOperacaoAtual;
		return (OperacaoProduto) super.getSession().createQuery(sql).setMaxResults(1).uniqueResult();
		
	}

	@Override
	public Long operacaoInicial(String idProduto) throws Exception {
		return Long.parseLong(super.getSession().createQuery("select min(op.id) from OperacaoProduto op where op.produto = " + idProduto)
				.uniqueResult().toString());
	}
	
}
