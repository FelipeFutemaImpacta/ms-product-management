package br.com.impacta.lab.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProdutoTagId {

	private Long produtoId;
	
	private Long tagId;

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	
	
	
}
