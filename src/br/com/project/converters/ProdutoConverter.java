
package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Produto;

@FacesConverter(forClass = Produto.class, value = "produtoConverter")
public class ProdutoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			return (Produto) HibernateUtil.getCurrentSession().get(Produto.class, new Long(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null) {
			Produto c = (Produto) objeto;
			return c.getId() != null && c.getId() > 0 ? c.getId().toString() : null;
		}
		return null;
	}

}
