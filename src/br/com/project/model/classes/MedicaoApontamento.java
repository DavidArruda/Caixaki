package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "medicao_apontamento")
@SequenceGenerator(name = "medicao_apontamento_seq", sequenceName = "medicao_apontamento_seq", initialValue = 1, allocationSize = 1)
public class MedicaoApontamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "id", principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicao_apontamento_seq")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_medicao", nullable = false)
	private Date dataMedicao;
	
	@Temporal(TemporalType.TIME)
	@Column(nullable = false, name = "medicao_apontamento")
	private Date horarioMedicao;

	@IdentificaCampoPesquisa(descricaoCampo = "Apontamento", campoConsulta = "apontamento.id")
	@Basic
	@ManyToOne
	@JoinColumn(name = "apontamento_id", nullable = false)
	@ForeignKey(name = "apontamento_fk")
	private Apontamento apontamento = new Apontamento();

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataMedicao() {
		return dataMedicao;
	}

	public void setDataMedicao(Date dataMedicao) {
		this.dataMedicao = dataMedicao;
	}

	public Date getHorarioMedicao() {
		return horarioMedicao;
	}

	public void setHorarioMedicao(Date horarioMedicao) {
		this.horarioMedicao = horarioMedicao;
	}

	public Apontamento getApontamento() {
		return apontamento;
	}

	public void setApontamento(Apontamento apontamento) {
		this.apontamento = apontamento;
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
		MedicaoApontamento other = (MedicaoApontamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
