package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Entidade objetoSelecionado;

	@Autowired
	private ContextoBean cotextoBean;

	@Autowired
	private EntidadeController entidadeController;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<>();

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	protected Class<?> getClassImplemt() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		// TODO Auto-generated method stub
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return " AND entity.tipoEntidade = 'FUNCIONARIO' ";
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

}
