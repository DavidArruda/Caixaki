package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.project.bean.geral.ObjetoCampoConsulta;

/**
 * Converter para classe ObjetoCampoPesquisa. Responsável por efetuar a conversão nos combos de consulta.
 * @author David Arruda
 *
 */

@FacesConverter(forClass = ObjetoCampoConsulta.class)
public class ObjetoCampoConsultaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Converter a String vindo da tela jsf para um objeto.
	 * @author David Arruda
	 * @return Object
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) { //Valida se não é nulo.
			String[] vals = value.split("\\*");
			ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
			objetoCampoConsulta.setCampoBanco(vals[0]);
			objetoCampoConsulta.setTipoClass(vals[1]);
			
			return objetoCampoConsulta;
		}
		return null;
	}

	
	/**
	 * Converte para um objeto para ser utilizado na tela JSF.
	 * @author David Arruda
	 * @return Object
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			ObjetoCampoConsulta c = (ObjetoCampoConsulta) value;
			
			return c.getCampoBanco() + "*" + c.getTipoClass();
		}
		return null;
	}

}
