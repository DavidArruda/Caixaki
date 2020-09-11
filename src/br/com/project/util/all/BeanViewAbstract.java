package br.com.project.util.all;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * Classe abstrata, caso esses metodos n�o forem reescritos quando invocados,
 * ser� utizado a implementa��o padr�o(Implementa��o desse classe).
 * 
 * @author deh0_
 *
 */
@Component
public class BeanViewAbstract implements ActionViewPadrao { // ABSTRACT OBRIGA QUE AS OUTRAS CLASSES IMPLEMENTE

	private static final long serialVersionUID = 1L;
	
	/**
	 *  ir� fazer o RELOAD forcado da tela JSF e isso ir� limpar os dados em cache evitando a duplica��o,
	 * esse m�todo devera ser chamado de dentro do m�todo de sucesso,
	 * assim a corre��o fica correta para todo o sistema. 
	 * @throws Exception
	 */
	protected void refresh() throws Exception {
		  /*Pega o contexto do JSF*/
		  ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		  
		  /*Pegas informa��es da solicita��o e HTTP*/
		  StringBuffer requestURL = ((HttpServletRequest) ec.getRequest()).getRequestURL();
		  
		  /*Pega a URL completa da solitica��o*/
		  String queryString = ((HttpServletRequest) ec.getRequest()).getQueryString();
		  
		  /*Permite mostrar as menssagens ap�s redirecionamento*/
		  ec.getFlash().setKeepMessages(true);
		 
		  /*Faz o refresh da p�gina JSF*/
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
		//SALVAMENTO SEM RETORNO PARA OUTRA P�GINA (ACTION LISTENER)
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
	 * Mensagem de sucesso, (protected) S� quem reescrever esse metodo ter� acesso
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
