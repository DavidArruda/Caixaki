package teste.junit;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.stereotype.Component;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.OperacaoProduto;

@Component
public class TesFuncionario {
	
	@Test
	public void testeOperacao(){
		HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(OperacaoProduto.class)
		.add(Restrictions.eq("1".toString(), "1".toString()))
		.setMaxResults(1).uniqueResult();
		
	}
	

}