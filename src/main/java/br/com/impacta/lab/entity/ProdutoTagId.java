package br.com.impacta.lab.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProdutoTagId implements Serializable {

	private Long produtoId;

	private Long tagId;

	public ProdutoTagId() {
	}

	public ProdutoTagId(Long produtoId, Long tagId) {
		this.produtoId = produtoId;
		this.tagId = tagId;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProdutoTagId that = (ProdutoTagId) o;
		return Objects.equals(produtoId, that.produtoId) && Objects.equals(tagId, that.tagId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(produtoId, tagId);
	}

}
