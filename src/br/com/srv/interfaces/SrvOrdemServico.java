package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public interface SrvOrdemServico extends Serializable {
	
	Long statusInicial(String idProduto) throws Exception;

}
