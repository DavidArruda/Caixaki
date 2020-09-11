package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.project.geral.controller.OperacaoProdutoController;
import br.com.project.geral.controller.StatusOsController;
import br.com.srv.interfaces.SrvStatusOs;

@Service
public class SrvStatusOsImpl  implements SrvStatusOs{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private OperacaoProdutoController operacaoProdutoController;
	
	@Resource
	private StatusOsController statusOsController;
	
}
