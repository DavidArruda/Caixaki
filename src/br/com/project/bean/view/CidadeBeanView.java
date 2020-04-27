package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
	
	private List<Cidade> list = new ArrayList<>();
	
	@Autowired
	private CidadeController cidadeController;
	
	@Override
	public String salvar() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public String editar() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Cidade();
		
		return url;
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		System.out.println(objetoSelecionado.getCid_descricao());
	}
	
	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	public List<Cidade> getList() throws Exception {
		list = cidadeController.findList(Cidade.class);
		return list;
	}
}
