package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Cidade objetoSelecionado = new Cidade();

	private final String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";

	private final String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";
	
	private List<Cidade> list = new ArrayList<>();
	
	@Autowired
	private CidadeController cidadeController;

	@Override
	public String salvar() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clear();
		objetoSelecionado = new Cidade();
	}

	@Override
	public String editar() throws Exception {
		list.clear();
		return url;
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Cidade) cidadeController.getSession()
				.get(getClassImplemt(), objetoSelecionado.getCid_codigo());
		cidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		novo();
		sucesso();
	}

	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Cidade();

		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		list.clear();
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Cidade();
		sucesso();
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public List<Cidade> getList() throws Exception {
		list = cidadeController.findList(getClassImplemt());
		return list;
	}

	@Override
	protected Class<Cidade> getClassImplemt() {
		return Cidade.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return cidadeController;
	}
}
