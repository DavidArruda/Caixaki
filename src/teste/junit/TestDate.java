package teste.junit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DataUtil;

public class TestDate {

	@Test
	public void testDate() {
		try {
			assertEquals("03042020", DataUtil.getDateAtualReportName());
			assertEquals("'2020-04-03'", DataUtil.formatDateSql(Calendar.getInstance().getTime()));
			assertEquals("2020-04-03", DataUtil.formatDateSqlSimple(Calendar.getInstance().getTime()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
