package br.com.project.report.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String UNDERLINE = "";
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_PDF = "pdf";
	private String SEPARETOR = File.separator;
	private static final int RELATORIO_PDF = 1;
	private static final int RELATORIO_EXCEL = 2;
	private static final int RELATORIO_HTML = 3;
	private static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	private static final String PONTO = ".";
	private StreamedContent arquivoRetorno = null;
	private String caminhoArquivoRelatorio = null;
	private JRExporter tipoArquivoExportado;
	private String extensaoArquivoExportado = "";
	private File arquivoGerado = null;
	
	/**
	 * Método para gerar relatórios a partir de uma JRBeanCollectionDataSource
	 * @param listDataBeanCollectionReport
	 * @param parametrosRelatorio
	 * @param nomeRelatorio
	 * @param nomeRelatorioSaida
	 * @return StreamedContent
	 * @throws Exception
	 */
	public StreamedContent gerarRelatorio(List<?> listDataBeanCollectionReport, HashMap parametrosRelatorio, String nomeRelatorio,
			String nomeRelatorioSaida) throws Exception {
		
		//CRIAÇÃO DA LISTA DE COLLECTION_BEANS_DATA_SOURCE QUE CONTEM OS DADOS DO RELATÓRIO
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanCollectionReport);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
		
		return null;
	}
	
}
