package br.com.project.util.all;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * Classe abstrata, caso esses metodos não forem reescritos quando invocados,
 * será utizado a implementação padrão(Implementação desse classe).
 * 
 * @author deh0_
 *
 */
@Component
public class BeanViewAbstract implements ActionViewPadrao { // ABSTRACT OBRIGA QUE AS OUTRAS CLASSES IMPLEMENTE

	private static final long serialVersionUID = 1L;
	
	/**
	 *  irá fazer o RELOAD forcado da tela JSF e isso irá limpar os dados em cache evitando a duplicação,
	 * esse método devera ser chamado de dentro do método de sucesso,
	 * assim a correção fica correta para todo o sistema. 
	 * @throws Exception
	 */
	protected void refresh() throws Exception {
		  /*Pega o contexto do JSF*/
		  ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		  
		  /*Pegas informações da solicitação e HTTP*/
		  StringBuffer requestURL = ((HttpServletRequest) ec.getRequest()).getRequestURL();
		  
		  /*Pega a URL completa da soliticação*/
		  String queryString = ((HttpServletRequest) ec.getRequest()).getQueryString();
		  
		  /*Permite mostrar as menssagens após redirecionamento*/
		  ec.getFlash().setKeepMessages(true);
		 
		  /*Faz o refresh da página JSF*/
		  ec.redirect((queryString == null) ? requestURL.toString() :
			  requestURL.append('?').append(queryString).toString());
  	}

	@Override
	public void limparLista() throws Exception {
		//LIMPA LISTA
	}

	@Override
	public String salvar() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
		//SALVAMENTO SEM RETORNO PARA OUTRA PÁGINA (ACTION LISTENER)
	}

	@Override
	public void saveEdit() throws Exception {

	}

	@Override
	public void excluir() throws Exception {

	}

	@Override
	public String ativar() throws Exception {
		return null;
	}

	@Override
	public String novo() throws Exception {
		return null;
	}

	@Override
	public String editar() throws Exception {
		return null;
	}

	@Override
	public void setarVariaveisNulas() throws Exception {

	}

	@Override
	public void consultarEntidade() throws Exception {

	}

	@Override
	public void statusOperation(StatusPersistencia a) throws Exception {
		Messages.responseOperation(a);
	}

	/**
	 * Mensagem de sucesso, (protected) Só quem reescrever esse metodo terá acesso
	 * 
	 * @throws Exception
	 */
	protected void sucesso() throws Exception {
		statusOperation(StatusPersistencia.SUCESSO);
		refresh();
	}

	protected void error() throws Exception {
		statusOperation(StatusPersistencia.ERRO);
	}

	@Override
	public String redirecionarNewEntidade() throws Exception {
		return null;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		return null;
	}

	@Override
	public void addMessage(String msg) {
		Messages.msg(msg);
	}

}
