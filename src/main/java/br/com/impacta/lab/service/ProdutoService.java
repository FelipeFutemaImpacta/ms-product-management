package br.com.impacta.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.lab.dto.ProdutoPatchRequest;
import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.dto.ProdutoUpdateRequest;
import br.com.impacta.lab.entity.ProdutoEntity;
import br.com.impacta.lab.exception.NotFoundException;
import br.com.impacta.lab.repository.ProdutoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public List<ProdutoResponse> listarTodos() {
		
		List<ProdutoEntity> produtos = produtoRepository.listarTodos();
		
		return produtos.stream()
			.map(p -> toResponse(p))
			.toList();
		
	}
	
	public ProdutoResponse buscarPorId(Long id) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);
		
		if (produto == null) {
			throw new NotFoundException(id);
		}
		
		return toResponse(produto);
		
	}
	
	public ProdutoResponse criarProduto(ProdutoRequest request) {
		
		ProdutoEntity entity = toEntity(request);
		
		entity = produtoRepository.criarProduto(entity);
		
		return toResponse(entity);
		
	}
	
	public ProdutoResponse atualizarProduto(Long id, ProdutoUpdateRequest request) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);
		
		if (produto == null) {
			throw new NotFoundException(id);
		}
		
		produto.setNome(request.nome());
		produto.setPreco(request.preco());
		produto.setDescricao(request.descricao());
		
		return toResponse(produtoRepository.atualizar(produto));
	}
	
	public ProdutoResponse atualizaParcialProduto(Long id, ProdutoPatchRequest request) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);
		
		if (produto == null) {
			throw new NotFoundException(id);
		}
		
		request.nome().ifPresent(nome -> produto.setNome(nome));
		
		if (request.preco().isPresent()) {
			produto.setPreco(request.preco().get());
		}
		
		if (request.descricao().isPresent()) {
			produto.setDescricao(request.descricao().get());
		}
		
		return toResponse(produtoRepository.atualizar(produto));
	}
	
	public void deletaProduto(Long id) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);
		
		if (produto == null) {
			throw new NotFoundException(id);
		}
		
		produtoRepository.deletar(produto);
	}
	
	public ProdutoEntity toEntity(ProdutoRequest request) {
		ProdutoEntity produto = new ProdutoEntity();
		produto.setNome(request.nome());
		produto.setPreco(request.preco());
		produto.setDescricao(request.descricao());
		
		return produto;
	}
	
	public ProdutoResponse toResponse(ProdutoEntity entity) {
		return new ProdutoResponse(entity.getId(), entity.getNome(), 
				entity.getPreco(), entity.getDescricao());
	}
	
}
