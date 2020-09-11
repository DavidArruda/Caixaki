package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import br.com.project.model.classes.Apontamento;
import br.com.project.model.classes.StatusO;

@Service
public interface SrvApontamento extends Serializable{
	
	/**
	 * Realiza o apontamento de uma determinada ordem de serviço
	 * @param apontamento
	 * @return Apontamento
	 * @throws Exception
	 */
	Apontamento apontar(Apontamento apontamento, StatusO statusO) throws Exception;
	
}
