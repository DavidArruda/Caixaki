package br.com.framework.controller.crud;

import org.springframework.transaction.annotation.Transactional;

import br.com.framework.implementacao.crud.InplementacaoCrud;

/**
 * Controller genérico para controlar transações com o banco de dados.
 * @author deh0_
 *
 */
@Transactional
public class Controller extends InplementacaoCrud<Object> {

	private static final long serialVersionUID = 1L;

}
