package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.geral.controller.EstadoController;
import br.com.project.util.all.BeanViewAbstract;

@Controller
@Scope(value = "session")
@ManagedBean(name = "estadoBeanView")
public class EstadoBeanView extends BeanViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EstadoController estadoController;
	
	public List<SelectItem> getEstados() throws Exception{
		return estadoController.getListEstado();
	}

}
