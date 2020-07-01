package br.com.project.geral.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends InplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SrvEntidade srvEntidade;

	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findUniqueByProperty(Entidade.class, userLogado, "ent_login", " and entity.ent_inativo is false");
	}

	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);

	}

	public void updateUltimoAcessoUser(String name) {
		srvEntidade.updateUltimoAcessoUser(name);

	}

	/**
	 * Método para verificar se o cpf já está cadastrado na base de dados.
	 * @param cpf
	 * @return boolean
	 * @throws Exception
	 * @author David Arruda
	 */
	public boolean existeCpf(String cpf) throws Exception {
		return super.findListByQueryDynamic("from Entidade where cpf = '" + cpf + "'").size() > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> pequisarPorNome(String nome) throws Exception {
		return (List<Entidade>) getSession().createQuery("from Entidade where ent_nomefantasia like '%" + nome + "%'").list();
	}

}
