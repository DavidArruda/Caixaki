package teste.junit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Ignore;
import org.junit.Test;

import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.project.report.util.DataUtil;

public class TesFuncionario {
	
	EntidadeController entidadeController = new EntidadeController();
	
	
	@Test
	@Ignore
	public void testDate() {
		try {
			assertEquals("03042020", DataUtil.getDateAtualReportName());
			assertEquals("'2020-04-03'", DataUtil.formatDateSql(Calendar.getInstance().getTime()));
			assertEquals("2020-04-03", DataUtil.formatDateSqlSimple(Calendar.getInstance().getTime()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertFuncionario() {
		try {
			Entidade entidade = new Entidade();
			
			entidade.setEnt_nomefantasia("David");
			entidade.setEnt_login("admin");
			entidade.setEnt_senha("admin");
			
			entidadeController.merge(entidade);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
