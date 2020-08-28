package br.com.srv.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public interface SrvStatusOs extends Serializable{
	
	Long atualizaStatusOs(Long idProduto, Long idOperacaoAtual) throws Exception;

}
