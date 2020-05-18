package br.com.project.bean.geral;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Component;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.CondicaoPesquisa;
import br.com.project.report.util.BeanReportView;
import br.com.project.util.all.UtilitariaRegex;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	private static final long serialVersionUID = 1L;

	protected abstract Class<?> getClassImplemt();

	protected abstract InterfaceCrud<?> getControler();

	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;
	
	public List<SelectItem> listaCampoPesquisa;
	
	public List<SelectItem> listaCondicaoPesquisa;
	
	public CondicaoPesquisa condicaoPesquisaSelecionado;
	
	public String valorPesquisa;
	
	public void setValorPesquisa(String valorPesquisa) {
		this.valorPesquisa = valorPesquisa;
	}
	
	public String getValorPesquisa() {
		return valorPesquisa != null ? new UtilitariaRegex().retiraAcentos(valorPesquisa) : "";
	}
	
	public void setCondicaoPesquisaSelecionado(CondicaoPesquisa condicaoPesquisaSelecionado) {
		this.condicaoPesquisaSelecionado = condicaoPesquisaSelecionado;
	}
	
	public CondicaoPesquisa getCondicaoPesquisaSelecionado() {
		return condicaoPesquisaSelecionado;
	}
	
	public List<SelectItem> getListaCondicaoPesquisa() {
		listaCondicaoPesquisa = new ArrayList<>();
		
		//RETORNA TODAS AS OPÇÕES DO ENUM
		for (CondicaoPesquisa condicaoPesquisa : CondicaoPesquisa.values()) {
			listaCondicaoPesquisa.add(new SelectItem(condicaoPesquisa, condicaoPesquisa.toString()));
		}
		
		return listaCondicaoPesquisa;
	}
	

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {
		if (objetoCampoConsultaSelecionado != null) { //VERIFICA SE O OBJETO É DIFERENTE DE NULL
			
			//PEGA TODOS OS ATRIBUTOS DA ENTIDADE
			for (Field field : getClassImplemt().getDeclaredFields()) {
				
				//VERIFICA SE A FIELD CONTEM ESTA ANOTAÇÃO
				if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
					if (objetoCampoConsultaSelecionado.getCampoBanco().equalsIgnoreCase(field.getName())) {
						String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
						objetoCampoConsultaSelecionado.setDescricao(descricaoCampo);
						objetoCampoConsultaSelecionado.setTipoClass(field.getType().getCanonicalName());
						objetoCampoConsultaSelecionado.setPrincipal(field.getAnnotation(IdentificaCampoPesquisa.class).principal());
						break;
					}
				}
			}
		}
		
		this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
	}

	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}
	
	public List<SelectItem> getListaCampoPesquisa() {
		listaCampoPesquisa = new ArrayList<>();
		List<ObjetoCampoConsulta> listTemp = new ArrayList<>();
		
		//PEGA TODOS OS ATRIBUTOS DA ENTIDADE
		for(Field field: getClassImplemt().getDeclaredFields()) {
			
			//VERIFICA SE A FIELD CONTEM ESTA ANOTAÇÃO
			if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
				String descricao = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
				String descricaoCampoPesquisa = field.getAnnotation(IdentificaCampoPesquisa.class).campoConsulta();
				int isPrincipal = field.getAnnotation(IdentificaCampoPesquisa.class).principal();
				
				ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
				objetoCampoConsulta.setDescricao(descricao);
				objetoCampoConsulta.setCampoBanco(descricaoCampoPesquisa);
				objetoCampoConsulta.setTipoClass(field.getType().getCanonicalName());
				objetoCampoConsulta.setPrincipal(isPrincipal);
				
				listTemp.add(objetoCampoConsulta);
			}
		}
		
		orderReverse(listTemp);
		
		//TRANSFORMA EM UMA LISTA DE SELECTITENS PARA RETORNAR PARA O COMBO BOX
		for (ObjetoCampoConsulta objetoCampoConsulta : listTemp) {
			listaCampoPesquisa.add(new SelectItem(objetoCampoConsulta));
		}
		
		return listaCampoPesquisa;
	}

	/**
	 * Ordena a lista de ObjetoCampoPesquisa pelo field principal.
	 * @param listTemp
	 * @return void
	 * @author David Arruda
	 */
	private void orderReverse(List<ObjetoCampoConsulta> listTemp) {
		Collections.sort(listTemp, new Comparator<ObjetoCampoConsulta>() {

			@Override
			public int compare(ObjetoCampoConsulta o1, ObjetoCampoConsulta o2) {
				return o1.getPrincipal().compareTo(o2.getPrincipal());
			}
		});
		
	}

}
