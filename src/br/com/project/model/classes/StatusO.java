package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

/**
 * The persistent class for the status_os database table.
 * 
 */
@Audited
@Entity
@Table(name = "status_os")
@SequenceGenerator(name = "status_os_sequence", sequenceName = "status_os_sequence", allocationSize = 1, initialValue = 1)
@NamedQuery(name = "StatusO.findAll", query = "SELECT s FROM StatusO s")
public class StatusO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_os_sequence")
	private Long id;

	// bi-directional many-to-one association to Operacao
	@ManyToOne(cascade = CascadeType.ALL)
	private OperacaoProduto operacaoProduto;

	// bi-directional many-to-one association to OrdemServico
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordem_servico_id")
	@IdentificaCampoPesquisa(descricaoCampo = "Nº O.S", campoConsulta = "ordemServico.id" , principal = 1)
	private OrdemServico ordemServico = new OrdemServico();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public StatusO() {
		// CONSTRUTOR VAZIO
	}
	
	public StatusO(OperacaoProduto operacaoProduto, OrdemServico ordemServico) {
		super();
		this.operacaoProduto = operacaoProduto;
		this.ordemServico = ordemServico;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OperacaoProduto getOperacaoProduto() {
		return this.operacaoProduto;
	}

	public void setOperacaoProduto(OperacaoProduto operacao) {
		this.operacaoProduto = operacao;
	}

	public OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
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
		StatusO other = (StatusO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}