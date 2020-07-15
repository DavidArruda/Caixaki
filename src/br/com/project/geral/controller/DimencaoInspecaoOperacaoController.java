package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.DimencaoInspecaoOperacao;
import br.com.repository.interfaces.RepositoryDimencaoInspecaoOperacao;

@Controller
public class DimencaoInspecaoOperacaoController extends InplementacaoCrud<DimencaoInspecaoOperacao>
		implements InterfaceCrud<DimencaoInspecaoOperacao> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryDimencaoInspecaoOperacao repositoryDimencaoInspecaoOperacao;

	public void setRepositoryDimencaoInspecaoOperacao(
			RepositoryDimencaoInspecaoOperacao repositoryDimencaoInspecaoOperacao) {
		this.repositoryDimencaoInspecaoOperacao = repositoryDimencaoInspecaoOperacao;
	}
	
	/**
	 * Consulta de DimencaoInspecaoOperacao por operação+
	 * @param idOperacao
	 * @return List
	 * @throws Exception
	 */
	public List<DimencaoInspecaoOperacao> findByOperacao(String idOperacao) throws Exception {
		List<DimencaoInspecaoOperacao> dimensoes = new ArrayList<>();
		dimensoes = super.findListByQueryDynamic("from DimencaoInspecaoOperacao as dimencao left join dimencao.operacaoProduto as op with op.id = " + idOperacao);
			
		return dimensoes;
	}

	public void saveSQLNative(DimencaoInspecaoOperacao dimencaoInspecaoOperacao) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("n_cota", dimencaoInspecaoOperacao.getnCota());
		map.put("tolerancia", dimencaoInspecaoOperacao.getTolerancia());
		map.put("valor", dimencaoInspecaoOperacao.getValor());
		map.put("versionnum", 0);
		map.put("operacao_produto_id", dimencaoInspecaoOperacao.getOperacaoProduto().getId());

		super.getSimpleJdbcInsert().usingGeneratedKeyColumns("id").withTableName("dimencao_inspecao_operacao")
				.execute(map);
	}
}
