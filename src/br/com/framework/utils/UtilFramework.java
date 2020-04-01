package br.com.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Classe respons�vel por cadastrar qual usu�rio realizou determinada opera��o na base de dados.
 * @author David Salom�o
 *@category Audit
 */

@Component
public class UtilFramework implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	/**
	 * Carrega qual usu�rio est� fazendo a altera��o na base de dados.
	 * Metodos est�ticos e sincronizados: Garante que o metodo n�o ser� utilizado em dois lugares ao mesmo tempo.
	 * @return ThreadLocal<Long>
	 */
	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return threadLocal;
	}

}
