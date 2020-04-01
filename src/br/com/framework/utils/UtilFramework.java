package br.com.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Classe responsável por cadastrar qual usuário realizou determinada operação na base de dados.
 * @author David Salomão
 *@category Audit
 */

@Component
public class UtilFramework implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	/**
	 * Carrega qual usuário está fazendo a alteração na base de dados.
	 * Metodos estáticos e sincronizados: Garante que o metodo não será utilizado em dois lugares ao mesmo tempo.
	 * @return ThreadLocal<Long>
	 */
	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return threadLocal;
	}

}
