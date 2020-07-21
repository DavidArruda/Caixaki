package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "apontamento")
@SequenceGenerator(name = "apontamento_seq", sequenceName = "apontamento_seq", initialValue = 1, allocationSize = 1)
public class Apontamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id", principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apontamento_seq")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;

	@Column(nullable = false)
	private Integer quantidade;

	@IdentificaCampoPesquisa(descricaoCampo = "Ordem de servico", campoConsulta = "ordemServico.id")
	@Basic
	@ManyToOne
	@JoinColumn(name = "ordem_servico_id", nullable = false)
	@ForeignKey(name = "ordem_servico_fk")
	private OrdemServico ordemServico = new OrdemServico();

	@IdentificaCampoPesquisa(descricaoCampo = "Ordem de servico", campoConsulta = "ordemServico.id")
	@Basic
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	@ForeignKey(name = "usuario_fk")
	private Entidade entidade = new Entidade();
	
	@OneToMany(mappedBy = "apontamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<MedicaoApontamento> medicoesApontamento = new ArrayList<>();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	
	public List<MedicaoApontamento> getMedicoesApontamento() {
		return medicoesApontamento;
	}
	
	public void setMedicoesApontamento(List<MedicaoApontamento> medicoesApontamento) {
		this.medicoesApontamento = medicoesApontamento;
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
		Apontamento other = (Apontamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Apontamento [id=" + id + ", inicio=" + inicio + ", termino=" + termino + ", quantidade=" + quantidade
				+ ", ordemServico=" + ordemServico + ", entidade=" + entidade + ", versionNum=" + versionNum + "]";
	}

}
