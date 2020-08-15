package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RepositoryOperacaoProduto extends Serializable {
	
	/**
	 * Método para consultar a próxima operacao a ser apontanda. Retorna o id da próxima operação
	 * @param produtoId
	 * @param idOperacaoAtual
	 * @return Long
	 * @throws Exception
	 */
	Long proximaOperacao(Long produtoId, Long idOperacaoAtual) throws Exception;

}
