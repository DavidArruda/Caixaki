package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "produto")
@SequenceGenerator(name = "produto_sequence", sequenceName = "produto_sequence", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sequence")
	private Long id;

	@IdentificaCampoPesquisa(descricaoCampo = "PN", campoConsulta = "pn", principal = 1)
	@Column(name = "pn", length = 13, nullable = false, unique = true)
	private String pn;

	@IdentificaCampoPesquisa(descricaoCampo = "Cliente", campoConsulta = "cliente")
	@Column(name = "cliente", length = 90, nullable = false)
	private String cliente;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "descricao")
	@Column(name = "descricao", length = 120, nullable = false)
	private String descricao;

	@NotAudited
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OperacaoProduto> operacoes = new ArrayList<OperacaoProduto>();

	@NotAudited
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<OrdemServico> ordensServico = new ArrayList<OrdemServico>();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<OperacaoProduto> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<OperacaoProduto> operacoes) {
		this.operacoes = operacoes;
	}

	protected int getVersionNum() {
		return versionNum;
	}

	protected void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("id", id);
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}