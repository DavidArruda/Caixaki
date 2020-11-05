package br.com.project.model.classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "dimencao_inspecao_operacao")
public class DimencaoInspecaoOperacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nº cota", campoConsulta = "nCota")
	@Column(name = "n_cota", length = 4, nullable = false)
	private String nCota;

	@Column(precision = 9, scale = 3, nullable = false)
	private BigDecimal valor = new BigDecimal("0");

	@IdentificaCampoPesquisa(descricaoCampo = "Tolerância", campoConsulta = "tolerancia")
	@Column(length = 12, nullable = false)
	private String tolerancia;

	@IdentificaCampoPesquisa(descricaoCampo = "Operação", campoConsulta = "operacaoProduto.nOperacao")
	@NotAudited
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "operacao_produto_id", nullable = false)
	@ForeignKey(name = "operacao_produto_fk")
	private OperacaoProduto operacaoProduto = new OperacaoProduto();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getnCota() {
		return nCota;
	}

	public void setnCota(String nCota) {
		this.nCota = nCota;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}

	public OperacaoProduto getOperacaoProduto() {
		return operacaoProduto;
	}

	public void setOperacaoProduto(OperacaoProduto operacaoProduto) {
		this.operacaoProduto = operacaoProduto;
	}

	protected int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
		map.put("nCota", nCota);
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
		DimencaoInspecaoOperacao other = (DimencaoInspecaoOperacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}