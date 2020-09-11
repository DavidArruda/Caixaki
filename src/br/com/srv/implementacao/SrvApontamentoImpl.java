package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.hibernate.session.HibernateUtil;
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
	
	private Apontamento auxApontamento = new Apontamento();
	
	@Override
	public Apontamento apontar(Apontamento apontamento, StatusO statusOs) throws Exception {
		try {
			if (apontamento.getQuantidade().equals(apontamento.getOrdemServico().getQuantidade())) {
				auxApontamento = apontarEatualizarStatus(apontamento, statusOs);

			} else if (apontamento.getQuantidade() < apontamento.getOrdemServico().getQuantidade()) {
				auxApontamento = apontarEatualizarStatus(apontamento, statusOs); // APONTAMENTO COM A QTD MENOR
				apontarQtdRestante(apontamento, statusOs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.getConnection().rollback();
		}
		return auxApontamento;
	}

	/**
	 * Gera um novo apontamento. Gera um novo status e continua na mesma operação.
	 * @param apontamento
	 * @param statusOs
	 * @throws Exception
	 * @return  void
	 * @author David Arruda
	 */
	private Apontamento apontarQtdRestante(Apontamento apontamento, StatusO statusOs) throws Exception {
		// RESTANTE DA QUANTIDADE. O.S CONTINUA NO MESMO STATUS
		var qtdRestaste = (apontamento.getOrdemServico().getQuantidade() - apontamento.getQuantidade());
		apontamento.setQuantidade(qtdRestaste);

		// APONTAMENTO COM A QTD RESTANTE
		apontamento = repositoryApontamento.apontar(apontamento);

		// GERA UM NOVO STATUS PARA A O.S
		statusOs.setId(null);
		apontamento.getOrdemServico().getStatusOs().add(statusOsController.merge(statusOs));
		
		return apontamento;
	}

	/**
	 * Gera um novo apontamento e atualiza o status da o.s com a próxima operação.
	 * @param apontamento
	 * @param statusOs
	 * @throws Exception
	 * @return Apontamento
	 * @author David Arruda
	 */
	private Apontamento apontarEatualizarStatus(Apontamento apontamento, StatusO statusOs) throws Exception {
		//GERA NOVO APONTAMENTO
		apontamento = repositoryApontamento.apontar(apontamento);
		
		//CONSULTA A PRÓXIMA OPERACÃO
		statusOs.setOperacaoProduto(operacaoProdutoController.consultarProximaOp(
				statusOs.getOrdemServico().getProduto().getId(),
				statusOs.getOperacaoProduto().getId(),
				statusOs.getOperacaoProduto().getnOperacao()));
		
		//ATUALIZA O STATUS DA O.S
		apontamento.getOrdemServico().getStatusOs().add(statusOsController.merge(statusOs));
		
		return apontamento;
	}
	
}
