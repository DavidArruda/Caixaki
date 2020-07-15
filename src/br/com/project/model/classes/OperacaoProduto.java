package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Table(name = "operacao_produto")
@Entity
@SequenceGenerator(name = "operacao_produto_seq", sequenceName = "operacao_produto_seq", initialValue = 1, allocationSize = 1)
public class OperacaoProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	public OperacaoProduto() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operacao_produto_seq")
	private Long id;

	@Column(nullable = false, length = 90)
	private String descricao;

	@IdentificaCampoPesquisa(campoConsulta = "descricao", descricaoCampo = "Nº Operação")
	@Column(name = "n_operacao", nullable = false)
	private Short nOperacao;

	@Column(length = 45, nullable = false)
	private String maquina;

	@Column(name = "tempo_estimado", nullable = true)
	@Temporal(TemporalType.TIME)
	private Date tempoEstimado;

	@IdentificaCampoPesquisa(campoConsulta = "produto.pn", descricaoCampo = "PN", principal = 1)
	@NotAudited
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	@ForeignKey(name = "produto_fk")
	private Produto produto = new Produto();

	@NotAudited
	@OneToMany(mappedBy = "operacaoProduto", cascade = CascadeType.ALL)
	private List<DimencaoInspecaoOperacao> dimensoes = new ArrayList<DimencaoInspecaoOperacao>();

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

	public Short getnOperacao() {
		return nOperacao;
	}

	public void setnOperacao(Short nOperacao) {
		this.nOperacao = nOperacao;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public Date getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(Date tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<DimencaoInspecaoOperacao> getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(List<DimencaoInspecaoOperacao> dimensoes) {
		this.dimensoes = dimensoes;
	}

	protected int getVersionNum() {
		return versionNum;
	}

	protected void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
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
		OperacaoProduto other = (OperacaoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
		map.put("descricao", descricao);
		return new JSONObject(map);
	}

	@Override
	public String toString() {
		return "OperacaoProduto [id=" + id + ", descricao=" + descricao + ", nOperacao=" + nOperacao + ", maquina="
				+ maquina + ", tempoEstimado=" + tempoEstimado + ", produto=" + produto + ", dimensoes=" + dimensoes
				+ "]";
	}

}
