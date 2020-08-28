package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import br.com.project.model.classes.Apontamento;

@Service
public interface SrvApontamento extends Serializable{
	
	/**
	 * Realiza o apontamento de uma determinada ordem de serviço
	 * @param apontamento
	 * @return Apontamento
	 * @throws Exception
	 */
	Apontamento apontar(Apontamento apontamento) throws Exception;
	
	/**
	 * Método para atualizar status da os.
	 * @param idProduto
	 * @param idOperacaoAtual
	 * @return
	 * @throws Exception
	 */
	Long atualizaStatusOs(Long idProduto, Long idOperacaoAtual) throws Exception;
	
	

}
