package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.report.util.DataUtil;

@Audited
@Entity
@Table(name = "instrumento_medicao")
@SequenceGenerator(name = "instrumento_medicao_seq", sequenceName = "instrumento_medicao_seq", initialValue = 1, allocationSize = 1)
public class InstrumentoMedicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instrumento_medicao_seq")
	private Long id;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "descricao")
	@Column(length = 60, nullable = false)
	private String descricao;

	@IdentificaCampoPesquisa(descricaoCampo = "Codigo Empresa", campoConsulta = "idEmpresa", principal = 1)
	@Column(name = "id_empresa", length = 6, nullable = false, unique = true)
	private String idEmpresa;

	@Column(name = "data_calibracao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCalibracao;

	@Column(name = "validade_calibracao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date validadeCalibracao;

	@Column(name = "autorizacao_uso", length = 3, nullable = true)
	private String autorizacaoUso;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Date getDataCalibracao() {
		return dataCalibracao;
	}

	public void setDataCalibracao(Date dataCalibracao) {
		this.dataCalibracao = dataCalibracao;
	}

	public Date getValidadeCalibracao() {
		return validadeCalibracao;
	}

	public void setValidadeCalibracao(Date validadeCalibracao) {
		this.validadeCalibracao = validadeCalibracao;
	}

	protected int getVersionNum() {
		return versionNum;
	}

	protected void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public String getAutorizacaoUso() {

		var calibracao = getValidadeCalibracao().toString();
		var dataAtual = DataUtil.getDateNow().toString();

		if (getValidadeCalibracao().after(DataUtil.getDataAtual()) || calibracao.equalsIgnoreCase(dataAtual)) {
			setautorizacao_uso("Sim");

		} else {
			setautorizacao_uso("Não");
		}

		return autorizacaoUso;
	}

	public void setautorizacao_uso(String autorizacaoUso) {
		this.autorizacaoUso = autorizacaoUso;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
		map.put("descricao", descricao);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstrumentoMedicao other = (InstrumentoMedicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InstrumentoMedicao [id=" + id + ", descricao=" + descricao + ", idEmpresa=" + idEmpresa
				+ ", dataCalibracao=" + dataCalibracao + ", validadeCalibracao=" + validadeCalibracao
				+ ", autorizacaoUso=" + autorizacaoUso + "]";
	}

}
