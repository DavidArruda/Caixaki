package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Mensagem;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
	
	private Mensagem objetoSelecionado = new Mensagem();
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Mensagem();
		objetoSelecionado.setUsr_origem(contextoBean.getEntidadeLogada());
		return "";
	}
	
	@RequestMapping("**/buscarUsuarioDestinoMsg")
	public void buscarUsuarioDestinoMsg(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codEntidade") Long codEntidade) throws Exception{
		
		Entidade entidade = entidadeController.findObjById(Entidade.class, codEntidade);
		
		if (entidade != null) {
			objetoSelecionado.setUsr_destino(entidade);
			httpServletResponse.getWriter().write(entidade.getJson().toString());
		}
	}

	@Override
	protected Class<?> getClassImplemt() {
		return null;
	}

	@Override
	protected InterfaceCrud<?> getControler() {
		return null;
	}
	
	public Mensagem getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	public void setObjetoSelecionado(Mensagem objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
