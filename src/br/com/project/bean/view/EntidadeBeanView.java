package br.com.project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	public void updateSenha() throws Exception {
		//PEGA O USUÁRIO LOGADO
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		
		if (!entidadeAtualizaSenhaBean.getSenhaAtual()
				.equals(entidadeLogada.getEnt_senha())) {
			addMessage("A senha atual não é valida.");
			return; //RETURN VAZIO SOMENTE QUANDO O METODO É VOID.
		
		}else
			if(entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
				addMessage("A senha atual não pode ser igual a nova senha.");
				return;
				
			}else
				if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(entidadeAtualizaSenhaBean.getConfirmaSenha())) {
					addMessage("A nova senha e confirmação não conferem.");
					
				}else {
					entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean.getNovaSenha());
					entidadeController.saveOrUpdate(entidadeLogada);
					entidadeLogada = entidadeController.findObjById(getClassImplemt(), entidadeLogada.getEnt_codigo());
					
					if (entidadeLogada.getEnt_senha().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
						sucesso();
						
					}else {
						addMessage("A nova senha não foi atualizada");
						error();
					}
				}
	}
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	public void setEntidadeAtualizaSenhaBean(EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}

	/**
	 * Retorna o nome do usuário logado.
	 * @return String
	 */
	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception{
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	protected Class<Entidade> getClassImplemt() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getControler() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
