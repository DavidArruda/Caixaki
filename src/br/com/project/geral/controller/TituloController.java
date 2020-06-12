package br.com.project.geral.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.text.StrBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.implementacao.crud.InplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Titulo;

@Controller
public class TituloController extends InplementacaoCrud<Titulo> implements InterfaceCrud<Titulo>{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	//@ResponseBody RESPOSDE A REQUISIÇÃO AJAX QUE SERÁ FEITA.
	@RequestMapping("**/gerarGraficoInicial")
	public @ResponseBody String gerarGraficoInicial(@RequestParam(value = "dias") int dias) {
		
		//MAP É UMA LISTA QUE IDENTIFICA OS VALORES APARTIR DA CHAVE.
		//NESTE CASO CHAVE SÃO AS COLUNAS DA CONSULTA SQL.
		
		List<Map<String, Object>> tituloUltimosDias = getTituloUltimosDias(dias);
		
		int totalLinhas = tituloUltimosDias.size() + 1;
		
		String[] dados = new String[totalLinhas];
		
		int cont = 0;
		
		boolean semDados = false;
		
		if (totalLinhas <= 1) {
			semDados = true;
		}
		
		if (semDados) {
			dados[cont++] = "[\"" + "tipo" + "\"," + "\"" + "Quantidade" + "\"," + "\"" + "Média" + "\"]";

		} else {
			dados[cont] = "[\"" + "tipo" + "\"," + "\"" + "Quantidade" + "\"," + "\"" + "Média" + "\"]";
			cont ++;
			
			for (Map<String, Object> objeto : tituloUltimosDias) {
				dados[cont] = "[\"" + objeto.get("situacao") + "\","
							  + "\""
							  + objeto.get("quantidade") + "\"," 
							  + "\"" 
							  + objeto.get("media_valor") + "\"]";
				cont++;
			}
		}
		
		return Arrays.toString(dados);
	}

	/**
	 * Método para consultar dados que irão preencher o grafico de títulos.
	 * @param dias
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> getTituloUltimosDias(int dias) {
		StringBuilder sql = new StringBuilder();
		
	sql.append("select count(1) as quantidade, ");
		sql.append("tit_origem as situacao, ");
		sql.append("trunc(avg(coalesce(tit_valor, 0.00)), 2) as media_valor ");
		sql.append("from titulo ");
		sql.append("where cast(tit_datahora as date) >= (current_date - "+dias+") and cast(tit_datahora as date) <= (current_date) ");
		sql.append("group by tit_origem ");
        sql.append("");
		sql.append("union ");
        sql.append("");
		sql.append("select count(1) as quantidade, ");
		sql.append("case when tit_baixado ");
		sql.append(" then ");
		sql.append(" 'BAIXADO' ");
		sql.append(" else ");
		sql.append(" 'EM ABERTO' ");
		sql.append("end as situacao, ");
		sql.append("");
		sql.append("trunc(avg(coalesce(tit_valor, 0.00)), 2) as media_valor ");
		sql.append("from titulo ");
		sql.append("where cast(tit_datahora as date) >= (current_date - "+dias+") and cast(tit_datahora as date) <= (current_date) ");
		sql.append("group by tit_baixado ");
        sql.append("");
		sql.append("union ");	
		sql.append("");
		sql.append("select count(1) as quantidade, ");
		sql.append("tit_tipo as situacao, ");
		sql.append("trunc(avg(coalesce(tit_valor, 0.00)), 2) as media_valor ");
		sql.append("from titulo ");
		sql.append("where cast(tit_datahora as date) >= (current_date - "+dias+") and cast(tit_datahora as date) <= (current_date) ");
		sql.append("group by tit_tipo order by quantidade, media_valor ");

		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}
}
