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
@Table(name = "dimencao_apontamento_medida")
public class DimencaoApontamentoMedida implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nº cota", campoConsulta = "nCota")
	@Column(name = "n_cota", length = 4, nullable = false)
	private String nCota;

	@Column(precision = 9, scale = 3, nullable = false)
	private BigDecimal valor;

	@IdentificaCampoPesquisa(descricaoCampo = "Medicao", campoConsulta = "medicaoApontamento.id")
	@NotAudited
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "medicao_apontamento_id", nullable = false)
	@ForeignKey(name = "medicao_apontamento_fk")
	private MedicaoApontamento medicaoApontamento = new MedicaoApontamento();

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

	public MedicaoApontamento getMedicaoApontamento() {
		return medicaoApontamento;
	}

	public void setMedicaoApontamento(MedicaoApontamento medicaoApontamento) {
		this.medicaoApontamento = medicaoApontamento;
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
		DimencaoApontamentoMedida other = (DimencaoApontamentoMedida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}