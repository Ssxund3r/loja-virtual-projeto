package com.projeto.ssxund3r.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	@Column(name = "id_produto")
	private Long id;

	@Column(name = "tipo_unidade", nullable = false)
	private String tipoUnidade;

	@Column(name = "nome_produto", nullable = false)
	private String nome;

	@Column(name = "descricao_produto", columnDefinition = "text", length = 2000, nullable = false)
	private String descricao;

	@Column(name = "status_ativo", nullable = false)
	private Boolean ativo = Boolean.TRUE;

	/* Nota ITEM PRODUTO - ASSOCIAR */
	
	
	
	@Column(name = "largura_produto",nullable = false)
	private Double largura;

	@Column(name = "altura_produto", nullable = false)
	private Double altura;

	@Column(name = "profundidade_produto", nullable = false)
	private Double profundidade;

	@Column(name = "valor_venda", nullable = false)
	private BigDecimal valorVenda = BigDecimal.ZERO;

	@Column(name = "qtd_estoque", nullable = false)
	private Integer qtdEstoque = 0;

	@Column(name = "qtd_alerta_estoque")
	private Integer qtdAltertaEstoque = 0;

	@Column(name = "link_youtube")
	private String linkYoutube;

	@Column(name = "alerta_qtd_estoque")
	private Boolean alertaQtdeEstoque = Boolean.FALSE;

	@Column(name = "qtd_clique_produto")
	private Integer qtdeClique = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Integer getQtdAltertaEstoque() {
		return qtdAltertaEstoque;
	}

	public void setQtdAltertaEstoque(Integer qtdAltertaEstoque) {
		this.qtdAltertaEstoque = qtdAltertaEstoque;
	}

	public String getLinkYoutube() {
		return linkYoutube;
	}

	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}

	public Boolean getAlertaQtdeEstoque() {
		return alertaQtdeEstoque;
	}

	public void setAlertaQtdeEstoque(Boolean alertaQtdeEstoque) {
		this.alertaQtdeEstoque = alertaQtdeEstoque;
	}

	public Integer getQtdeClique() {
		return qtdeClique;
	}

	public void setQtdeClique(Integer qtdeClique) {
		this.qtdeClique = qtdeClique;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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