package br.com.impacta.lab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.impacta.lab.dto.CategoriaResponse;
import br.com.impacta.lab.dto.ProdutoPatchRequest;
import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.dto.ProdutoUpdateRequest;
import br.com.impacta.lab.dto.TagResponse;
import br.com.impacta.lab.entity.Produto;
import br.com.impacta.lab.entity.ProdutoTag;
import br.com.impacta.lab.exception.NotFoundException;
import br.com.impacta.lab.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<ProdutoResponse> listarTodos() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoResponse> respostas = new ArrayList<>();
		for (Produto produto : produtos) {
			respostas.add(toResponse(produto));
		}
		return respostas;
	}

	public ProdutoResponse buscarPorId(Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isEmpty()) {
			throw new NotFoundException(id);
		}
		Produto produto = optional.get();
		return toResponse(produto);
	}

	public ProdutoResponse criarProduto(ProdutoRequest request) {
		Produto produto = toModel(request);
		produto = produtoRepository.save(produto);
		return toResponse(produto);
	}

	public ProdutoResponse atualizarProduto(Long id, ProdutoUpdateRequest request) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isEmpty()) {
			throw new NotFoundException(id);
		}
		Produto produto = optional.get();

		produto.setNome(request.nome());
		produto.setPreco(request.preco());
		produto.setDescricao(request.descricao());

		return toResponse(produtoRepository.save(produto));
	}

	public ProdutoResponse atualizaParcialProduto(Long id, ProdutoPatchRequest request) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isEmpty()) {
			throw new NotFoundException(id);
		}
		Produto produto = optional.get();

		if (request.nome().isPresent()) {
			produto.setNome(request.nome().get());
		}
		if (request.preco().isPresent()) {
			produto.setPreco(request.preco().get());
		}
		if (request.descricao().isPresent()) {
			produto.setDescricao(request.descricao().get());
		}

		return toResponse(produtoRepository.save(produto));
	}

	public void deletaProduto(Long id) {
		buscarPorId(id);
		produtoRepository.deleteById(id);
	}

	private Produto toModel(ProdutoRequest request) {
		Produto produto = new Produto();
		produto.setId(null);
		produto.setNome(request.nome());
		produto.setPreco(request.preco());
		produto.setDescricao(request.descricao());
		return produto;
	}

	private ProdutoResponse toResponse(Produto produto) {
		CategoriaResponse categoria = null;
		if (produto.getCategoria() != null) {
			categoria = new CategoriaResponse(
					produto.getCategoria().getId(),
					produto.getCategoria().getNome());
		}

		List<TagResponse> tags = new ArrayList<>();
		for (ProdutoTag produtoTag : produto.getProdutoTags()) {
			tags.add(new TagResponse(
					produtoTag.getTag().getId(),
					produtoTag.getTag().getNome(),
					produtoTag.getDataAssociacao()));
		}

		return new ProdutoResponse(produto.getId(), produto.getNome(),
				produto.getPreco(), produto.getDescricao(), categoria, tags);
	}

}
