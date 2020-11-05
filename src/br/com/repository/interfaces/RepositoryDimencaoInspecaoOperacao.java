package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.model.classes.DimencaoInspecaoOperacao;

@Transactional
@Repository
public interface RepositoryDimencaoInspecaoOperacao extends Serializable {
	
	List<DimencaoInspecaoOperacao> findByOperacaoId (Long idOperacao) throws Exception;

}
