package com.projeto.ssxund3r.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cupom_desconto")
@SequenceGenerator(name = "seq_cupom_desconto", sequenceName = "seq_cupom_desconto", allocationSize = 1, initialValue = 1)
public class CupDesc implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
	@Column(name = "id_cupom_desconto")
	private Long id;
	
	@Column(name="codigo_descricao", nullable = false)
	private String codDesc;
	
	@Column(name="valor_real_desconto")
	private BigDecimal valorRealDesc;
	
	@Column(name="valor_porcentagem_desconto")
	private BigDecimal valorPorcentDesc;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_validade_cupom", nullable = false)
	private Date dataValidadeCupom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodDesc() {
		return codDesc;
	}

	public void setCodDesc(String codDesc) {
		this.codDesc = codDesc;
	}

	public BigDecimal getValorRealDesc() {
		return valorRealDesc;
	}

	public void setValorRealDesc(BigDecimal valorRealDesc) {
		this.valorRealDesc = valorRealDesc;
	}

	public BigDecimal getValorPorcentDesc() {
		return valorPorcentDesc;
	}

	public void setValorPorcentDesc(BigDecimal valorPorcentDesc) {
		this.valorPorcentDesc = valorPorcentDesc;
	}

	public Date getDataValidadeCupom() {
		return dataValidadeCupom;
	}

	public void setDataValidadeCupom(Date dataValidadeCupom) {
		this.dataValidadeCupom = dataValidadeCupom;
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
		CupDesc other = (CupDesc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}