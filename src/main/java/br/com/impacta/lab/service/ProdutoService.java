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
import br.com.impacta.lab.entity.CategoriaEntity;
import br.com.impacta.lab.entity.ProdutoEntity;
import br.com.impacta.lab.exception.NotFoundException;
import br.com.impacta.lab.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public List<ProdutoResponse> listarTodos() {
		
		//List<ProdutoEntity> produtos = produtoRepository.findAll();
		
		List<ProdutoEntity> produtos = produtoRepository.buscaPorDescricao("%RAM%");
		
		return produtos.stream()
			.map(p -> toResponse(p))
			.toList();
		
	}
	
	public ProdutoResponse buscarPorId(Long id) {
		//Optional<ProdutoEntity> produto = produtoRepository.findById(id);
		Optional<ProdutoEntity> produto = produtoRepository.findByIdFull(id);
		
		if (!produto.isPresent()) {
			throw new NotFoundException(id);
		}
		
		return toResponse(produto.get());
		
	}
	
	public ProdutoResponse criarProduto(ProdutoRequest request) {
		
		ProdutoEntity entity = toEntity(request);
		
		entity = produtoRepository.save(entity);
		
		return toResponse(entity);
		
	}
	
	public ProdutoResponse atualizarProduto(Long id, ProdutoUpdateRequest request) {
		Optional<ProdutoEntity> produtoOpt = produtoRepository.findById(id);
		
		if (!produtoOpt.isPresent()) {
			throw new NotFoundException(id);
		}
		
		var produto = produtoOpt.get();
		
		produto.setNome(request.nome());
		produto.setPreco(request.preco());
		produto.setDescricao(request.descricao());
		
		return toResponse(produtoRepository.save(produto));
	}
	
	public ProdutoResponse atualizaParcialProduto(Long id, ProdutoPatchRequest request) {
		Optional<ProdutoEntity> produtoOpt = produtoRepository.findById(id);
		
		if (!produtoOpt.isPresent()) {
			throw new NotFoundException(id);
		}
		
		var produto = produtoOpt.get();
		
		request.nome().ifPresent(nome -> produto.setNome(nome));
		
		if (request.preco().isPresent()) {
			produto.setPreco(request.preco().get());
		}
		
		if (request.descricao().isPresent()) {
			produto.setDescricao(request.descricao().get());
		}
		
		return toResponse(produtoRepository.save(produto));
	}
	
	public void deletaProduto(Long id) {
		Optional<ProdutoEntity> produtoOpt = produtoRepository.findById(id);
		
		if (!produtoOpt.isPresent()) {
			throw new NotFoundException(id);
		}
		
		var produto = produtoOpt.get();
		
		produtoRepository.delete(produto);
		//produtoRepository.deleteById(id);
	}
	
	public ProdutoEntity toEntity(ProdutoRequest request) {
		ProdutoEntity produto = new ProdutoEntity();
		produto.setNome(request.nome());
		produto.setPreco(request.preco());
		produto.setDescricao(request.descricao());
		
		return produto;
	}
	
	public ProdutoResponse toResponse(ProdutoEntity entity) {
		CategoriaEntity categoria = entity.getCategoria();
		CategoriaResponse categoriaResponse = new CategoriaResponse(categoria.getId(), categoria.getNome());
		
		List<TagResponse> tags = new ArrayList<>();
		
		for (var produtoTag : entity.getProdutoTags()) {
			TagResponse tagResponse = new TagResponse(produtoTag.getTag().getId(), 
					produtoTag.getTag().getNome(), 
					produtoTag.getData());
			
			tags.add(tagResponse);
		}
		
		
		return new ProdutoResponse(entity.getId(), entity.getNome(), 
				entity.getPreco(), entity.getDescricao(), categoriaResponse, tags);
	}
	
}
