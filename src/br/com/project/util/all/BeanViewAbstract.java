package br.com.project.util.all;

import org.springframework.stereotype.Component;


/**
 * Classe abstrata, caso esses metodos não forem reescritos quando invocados, será utizado a implementação padrão(Implementação desse classe).
 * @author deh0_
 *
 */
@Component
public class BeanViewAbstract implements ActionViewPadrao{ //ABSTRACT OBRIGA QUE AS OUTRAS CLASSES IMPLEMENTE

	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {
		
	}

	@Override
	public String salvar() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
		
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
	 * @throws Exception
	 */
	protected void sucesso() throws Exception{
		statusOperation(StatusPersistencia.SUCESSO);
	}
	
	protected void error() throws Exception{
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
