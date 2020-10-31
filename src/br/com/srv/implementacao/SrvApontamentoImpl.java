package br.com.srv.implementacao;

import java.math.BigInteger;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.geral.controller.OperacaoProdutoController;
import br.com.project.geral.controller.StatusOsController;
import br.com.project.model.classes.Apontamento;
import br.com.project.model.classes.StatusO;
import br.com.repository.interfaces.RepositoryApontamento;
import br.com.srv.interfaces.SrvApontamento;

@Service
public class SrvApontamentoImpl implements SrvApontamento {

	private static final long serialVersionUID = 1L;

	@Autowired
	RepositoryApontamento repositoryApontamento;

	@Autowired
	StatusOsController statusOsController;

	@Autowired
	OperacaoProdutoController operacaoProdutoController;

	@Override
	public Apontamento apontar(Apontamento apontamento, StatusO statusOs) throws Exception {

		var listaStatusOrdenada = statusOsController
				.consultarStatusPorOs(apontamento.getOrdemServico().getId().toString());
		Collections.sort(listaStatusOrdenada);

		//JUNTAR STATUS DA O.S
		if (listaStatusOrdenada.size() > 1 && statusOs.getOperacaoProduto().getnOperacao() < listaStatusOrdenada.get(0)
				.getOperacaoProduto().getnOperacao()) {
			statusOsController.deletarComCondicao(BigInteger.valueOf(statusOs.getId()));
			return repositoryApontamento.apontar(apontamento);

		} else if (apontamento.getQuantidade() < apontamento.getOrdemServico().getQuantidade()) {
			var auxStatus = statusOs;
			auxStatus.setId(null);
			apontarQtdRestante(apontamento);
			return apontarEatualizarStatus(apontamento, auxStatus); // APONTAMENTO COM A QTD MENOR

		} else {
			return apontarEatualizarStatus(apontamento, statusOs);
		}

	}

	/**
	 * Gera um novo apontamento. Gera um novo status e continua na mesma operação.
	 * 
	 * @param apontamento
	 * @param statusOs
	 * @throws Exception
	 * @return void
	 * @author David Arruda
	 */
	private void apontarQtdRestante(Apontamento apontamento) throws Exception {
		// RESTANTE DA QUANTIDADE. O.S CONTINUA NO MESMO STATUS
		var qtdRestaste = (apontamento.getOrdemServico().getQuantidade() - apontamento.getQuantidade());
		apontamento.setQuantidade(qtdRestaste);

		// APONTAMENTO COM A QTD RESTANTE
		repositoryApontamento.apontar(apontamento);
	}

	/**
	 * Gera um novo apontamento e atualiza o status da o.s com a próxima operação.
	 * 
	 * @param apontamento
	 * @param statusOs
	 * @throws Exception
	 * @return Apontamento
	 * @author David Arruda
	 */
	private Apontamento apontarEatualizarStatus(Apontamento apontamento, StatusO statusOs) throws Exception {
		// GERA NOVO APONTAMENTO
		apontamento = repositoryApontamento.apontar(apontamento);

		// CONSULTA A PRÓXIMA OPERACÃO
		statusOs.setOperacaoProduto(
				operacaoProdutoController.consultarProximaOp(statusOs.getOrdemServico().getProduto().getId(),
						statusOs.getOperacaoProduto().getId(), statusOs.getOperacaoProduto().getnOperacao()));

		// GERA UM NOVO STATUS PARA A O.S
		statusOsController.merge(statusOs);

		return apontamento;
	}

}
