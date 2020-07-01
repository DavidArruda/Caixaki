package br.com.project.geral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Produto;
import br.com.repository.interfaces.RepositoryProduto;

@Controller
public class ProdutoController extends InplementacaoCrud<Produto> implements InterfaceCrud<Produto> {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryProduto repositoryProduto;

	public void setRepositoryProduto(RepositoryProduto repositoryProduto) {
		this.repositoryProduto = repositoryProduto;
	}

	/**
	 * Método para consultar produto que contenha pn igual ao digitado pelo usuário
	 * no auto complete do primefaces
	 * 
	 * @param pn
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Produto> pesquisarProduto(String pn) throws Exception {
		return (List<Produto>) getSession().createQuery("from Produto where pn like '%" + pn + "%'").list();
	}

}
