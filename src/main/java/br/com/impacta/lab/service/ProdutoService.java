package br.com.impacta.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.entity.ProdutoEntity;
import br.com.impacta.lab.repository.ProdutoRepository;

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
		
		return produto == null ? null : toResponse(produto);
		
	}
	
	public ProdutoResponse criarProduto(ProdutoRequest request) {
		
		ProdutoEntity entity = toEntity(request);
		
		entity = produtoRepository.criarProduto(entity);
		
		return toResponse(entity);
		
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
