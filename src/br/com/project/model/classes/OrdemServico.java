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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "ordem_servico")
@SequenceGenerator(name = "ordem_servico_seq", sequenceName = "ordem_servico_seq", initialValue = 1, allocationSize = 1)
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordem_servico_seq")
	private Long id;

	@Column(name = "data_emissao")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "data_entrega")
	@Temporal(TemporalType.DATE)
	private Date dataEntrega;

	@Column(name = "quantidade")
	private Integer quantidade;

	@IdentificaCampoPesquisa(campoConsulta = "produto.pn", descricaoCampo = "PN Produto")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id", nullable = false)
	@ForeignKey(name = "produto_fk")
	private Produto produto = new Produto();

	// bi-directional many-to-one association to StatusO
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<StatusO> statusOs = new ArrayList<>();

	// bi-directional many-to-one association to Apontamento
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
	private List<Apontamento> apontamentos = new ArrayList<>();
	
	@Transient
	private List<OperacaoProduto> operacoesReport = new ArrayList<>();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Apontamento> getApontamentos() {
		return this.apontamentos;
	}

	public void setApontamentos(List<Apontamento> apontamentos) {
		this.apontamentos = apontamentos;
	}

	public Apontamento addApontamento(Apontamento apontamento) {
		getApontamentos().add(apontamento);
		apontamento.setOrdemServico(this);

		return apontamento;
	}

	public Apontamento removeApontamento(Apontamento apontamento) {
		getApontamentos().remove(apontamento);
		apontamento.setOrdemServico(null);

		return apontamento;
	}

	public List<StatusO> getStatusOs() {
		return this.statusOs;
	}

	public void setStatusOs(List<StatusO> statusOs) {
		this.statusOs = statusOs;
	}

	public StatusO addStatusO(StatusO statusO) {
		getStatusOs().add(statusO);
		statusO.setOrdemServico(this);

		return statusO;
	}

	public StatusO removeStatusO(StatusO statusO) {
		getStatusOs().remove(statusO);
		statusO.setOrdemServico(null);

		return statusO;
	}
	
	public List<OperacaoProduto> getOperacoesReport() {
		return operacoesReport;
	}
	
	public void setOperacoesReport(List<OperacaoProduto> operacoesReport) {
		this.operacoesReport = operacoesReport;
	}

	protected int getVersionNum() {
		return versionNum;
	}

	protected void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("produtoOs", produto);
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrdemServico [id=" + id + ", dataEmissao=" + dataEmissao + ", dataEntrega=" + dataEntrega
				+ ", quantidade=" + quantidade + ", produto=" + produto + "]";
	}

}
