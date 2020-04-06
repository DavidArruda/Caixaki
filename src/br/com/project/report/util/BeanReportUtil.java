package br.com.project.report.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.project.util.all.BeanViewAbstract;

@Component
public abstract class BeanReportUtil extends BeanViewAbstract {

	private static final long serialVersionUID = 1L;

	protected StreamedContent arquivoReport;
	protected int tipoRelatorio;
	protected List<?> listDataBeanCollectionReport;
	protected HashMap<Object, Object> parametroRelatorio;
	protected String nomeRelatorioJasper = "default";
	protected String nomeRelatorioSaida = "default";

	@Autowired
	private ReportUtil reportUtil;

	public BeanReportUtil() {
		parametroRelatorio = new HashMap<Object, Object>();
		listDataBeanCollectionReport = new ArrayList<>();
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}

	/**
	 * Resposável por retornar o arquivo para a web
	 * 
	 * @return StreamedContent
	 */
	public StreamedContent getArquivoReport() throws Exception {
		return getReportUtil().gerarRelatorio(getListDataBeanCollectionReport(), getParametroRelatorio(),
				getNomeRelatorioJasper(), getNomeRelatorioSaida(), getTipoRelatorio());
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public List<?> getListDataBeanCollectionReport() {
		return listDataBeanCollectionReport;
	}

	public void setListDataBeanCollectionReport(List<?> listDataBeanCollectionReport) {
		this.listDataBeanCollectionReport = listDataBeanCollectionReport;
	}

	public HashMap<Object, Object> getParametroRelatorio() {
		return parametroRelatorio;
	}

	public void setParametroRelatorio(HashMap<Object, Object> parametroRelatorio) {
		this.parametroRelatorio = parametroRelatorio;
	}

	public String getNomeRelatorioJasper() {
		return nomeRelatorioSaida;
	}

	public void setNomeRelatorioJasper(String nomeRelatorioJasper) {
		if (nomeRelatorioJasper == null || nomeRelatorioJasper.isEmpty()) {
			nomeRelatorioJasper = "default";
		}

		this.nomeRelatorioSaida = nomeRelatorioJasper;
	}

	public String getNomeRelatorioSaida() {
		return nomeRelatorioSaida;
	}

	public void setNomeRelatorioSaida(String nomeRelatorioSaida) {
		if (nomeRelatorioSaida == null || nomeRelatorioSaida.isEmpty()) {
			nomeRelatorioSaida = "default";
		}

		this.nomeRelatorioSaida = nomeRelatorioSaida;
	}

}
