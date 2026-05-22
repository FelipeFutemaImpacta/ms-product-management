package br.com.impacta.lab.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto_tags")
public class ProdutoTag {

	@EmbeddedId
	private ProdutoTagId id = new ProdutoTagId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("tagId")
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@Column(name = "data_associacao", nullable = false)
	private LocalDate dataAssociacao;

	public ProdutoTag() {
	}

	public ProdutoTag(Produto produto, Tag tag, LocalDate dataAssociacao) {
		this.id = new ProdutoTagId(produto.getId(), tag.getId());
		this.produto = produto;
		this.tag = tag;
		this.dataAssociacao = dataAssociacao;
	}

	public ProdutoTagId getId() {
		return id;
	}

	public void setId(ProdutoTagId id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public LocalDate getDataAssociacao() {
		return dataAssociacao;
	}

	public void setDataAssociacao(LocalDate dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}

}
