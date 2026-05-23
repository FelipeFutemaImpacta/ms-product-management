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
@Table(name = "PRODUTO_TAG")
public class ProdutoTagEntity {

	@EmbeddedId
	private ProdutoTagId produtoTagId = new ProdutoTagId();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("tagId")
	@JoinColumn(name = "tag_id")
	private TagEntity tag;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id")
	private ProdutoEntity produto;
	
	@Column(name = "data_criacao")
	private LocalDate data;

	public ProdutoTagEntity() {
		
	}
	
	public ProdutoTagEntity(TagEntity tag, ProdutoEntity produto, LocalDate data) {
		super();
		this.tag = tag;
		this.produto = produto;
		this.data = data;
	}

	public TagEntity getTag() {
		return tag;
	}

	public void setTag(TagEntity tag) {
		this.tag = tag;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
