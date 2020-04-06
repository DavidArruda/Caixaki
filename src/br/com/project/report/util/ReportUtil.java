package br.com.project.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

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
	private String caminhoSubreport_dir = "";
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
			String nomeRelatorioSaida, int tipoRelatorio) throws Exception {
		
		//CRIAÇÃO DA LISTA DE COLLECTION_BEANS_DATA_SOURCE QUE CONTEM OS DADOS DO RELATÓRIO
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanCollectionReport);
		
		//FORNECE O CAMINHO ATÉ AS PASTAS QUE CONTEM OS RELATÓRIOS .JASPER
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
		ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
		
		String caminhoRelatorio = scontext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARETOR + nomeRelatorio + PONTO + "jasper");
	
		if(caminhoRelatorio == null 
				|| (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
				|| !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARETOR = "";
		}
		
		//CAMINHO PARA IMAGENS DO RELATÓRIO
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//CAMINHO COMPLETO ATÉ O RELATÓRIO COMPILADO
		String caminhoArquivoJasper = caminhoRelatorio + SEPARETOR + nomeRelatorio + PONTO + "jasper";
		
		//FAZ O CARREGAMENTO DO RELATÓRIO
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
		
		//SETA PARAMETROS SUBREPORTT_DIR COMO CAMINHO FISICO PARA SUB-REPORTS
		caminhoSubreport_dir = caminhoRelatorio + SEPARETOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubreport_dir);
		
		//CARREGA O ARQUIVO COMPILADO PARA A MEMÓRIA
		JasperPrint impressoraJasper = JasperFillManager
				.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		switch (tipoRelatorio) {
		case RELATORIO_PDF:
			tipoArquivoExportado = new JRPdfExporter();
			extensaoArquivoExportado = EXTENSION_PDF;
			break;
			
		case RELATORIO_HTML:
			tipoArquivoExportado = new JRHtmlExporter();
			extensaoArquivoExportado = EXTENSION_HTML;
			break;
			
		case RELATORIO_EXCEL:
			tipoArquivoExportado = new JRXlsExporter();
			extensaoArquivoExportado = EXTENSION_XLS;
			break;
			
		case RELATORIO_PLANILHA_OPEN_OFFICE:
			tipoArquivoExportado = new JROdtExporter();
			extensaoArquivoExportado = EXTENSION_ODS;
			break;

		default:
			tipoArquivoExportado = new JRPdfExporter();
			extensaoArquivoExportado = EXTENSION_PDF;
			break;
		}
		
		//NOME DO RELATÓRIO DE SAIDA
		nomeRelatorioSaida += UNDERLINE + DataUtil.getDateAtualReportName();
		
		//CAMINHO QUE O RELATÓRIO FOI EXPORTADO
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARETOR + nomeRelatorioSaida + PONTO + extensaoArquivoExportado;
		
		//CRIA NOVO FILE EXPORTADO
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		//PREPARAR PARA IMPRESSÃO
		tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		//NOME DO ARQUIVO FISICO A SER EMPORTADO/IMPRESSO
		tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		//EXECUTA A EXPORTAÇÃO
		tipoArquivoExportado.exportReport();
		
		//REMOVE DO SERVIDOR APOS SE EXPORTADO/DOWNLOAD
		arquivoGerado.deleteOnExit();
		
		//CRIA O INPUTSTREAM PARA SER USADO PELO PRIMEFACES
		InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
		
		//FAZ O RETORNO PARA A APLICAÇÃO
		arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado, nomeRelatorioSaida + PONTO + extensaoArquivoExportado);
		
		return arquivoRetorno;
	}
	
}
