package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.model.classes.OperacaoProduto;

@Transactional
@Repository
public interface RepositoryOperacaoProduto extends Serializable {
	
	/**
	 * M�todo para consultar a pr�xima operacao a ser apontanda. Retorna o id da pr�xima opera��o
	 * @param produtoId
	 * @param idOperacaoAtual
	 * @return Long
	 * @throws Exception
	 */
	OperacaoProduto proximaOperacao(Long produtoId, Long idOperacaoAtual, int nOperacaoAtual) throws Exception;
	
	/**
	 * M�todo que retorna o id(identificador) da primeira opera��o de um determinado produto.
	 * @param idProduto
	 * @return Long
	 * @throws Exception
	 */
	Long operacaoInicial(String idProduto) throws Exception;

}
