package br.com.project.exeption;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import br.com.framework.hibernate.session.HibernateUtil;

public class CustomExeptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapperd;

	final FacesContext facesContext = FacesContext.getCurrentInstance();

	Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();

	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

	public CustomExeptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapperd = exceptionHandler;
	}

	/**
	 * sobrescreve o método ExceptionHandler que retorna a pilha de exeções.
	 * 
	 * @return ExceptionHandler
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return wrapperd;
	}

	/**
	 * sobrescreve o método handle que é responsável por manipular as exceções.
	 * 
	 * @return void
	 */
	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext contex = (ExceptionQueuedEventContext) event.getSource();
			
			//RECUPERAR A EXCEÇÃO DO CONTEXTO
			Throwable exeption = contex.getException();
			
			//trabalhando a exeção
			try {
				requestMap.put("exceptionMessage", exeption.getMessage());
				
				if (exeption != null && exeption.getMessage() != null
						&& exeption.getMessage().indexOf("ConstraintViolationException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg",
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro não pode ser removido por estar associado",""));
					
				} else if (exeption != null && exeption.getMessage() != null
						&& exeption.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro foi atualizado ou excluido por outro usuário. Consulte novamente",""));
					
				}else {
					
					//AVISA O USUÁRIO DO ERRO OCORRIDO
					FacesContext.getCurrentInstance().addMessage("msg",
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "O sistema se recuperou de um erro inesperado.",""));
					
					//INFORMAÇÃO PARA QUE O USUÁRIO CONTINUE USANDO O SISTEMA
					FacesContext.getCurrentInstance().addMessage("msg",
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Você pode continuar usando o sistema normamente!",""));
					
					FacesContext.getCurrentInstance().
					addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "O erro foi causado por: \n"+exeption.getMessage(),""));
					
					//SETA A NAVEGAÇÃO PARA UMA PÁGINA PADRÃO - REDIRECIONA PARA UMA PAGINA DE ERRO.
					// ESSE ALERT APENAS É EXIBIDO SE A PAGINA NÃO FOR REDIRECIONADA
					RequestContext.getCurrentInstance().execute("alert('O sistema se recuperou de um erro inesperado.')");
					
					RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,"Erro","O sistema se recuperou de um erro inesperado."));
					
					//REDIRECIONAMENTO PARA A PÁGINA DE ERRO
					navigationHandler.handleNavigation(facesContext, null, "/error/error.jsf?faces-redirect=true&expired=true");
				}
				
				//RENDERIZA A PAGINA DE ERRO E EXIBE AS MENSAGENS
				facesContext.renderResponse();
				
			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				
				//IMPRIME ERRO NO CONSOLE
				exeption.printStackTrace();
				
				iterator.remove();
			}
		}
		
		getWrapped().handle();
	}

}
