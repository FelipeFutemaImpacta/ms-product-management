package br.com.impacta.lab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.impacta.lab.dto.ProdutoPatchRequest;
import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.dto.ProdutoUpdateRequest;
import br.com.impacta.lab.entity.ProdutoEntity;
import br.com.impacta.lab.exception.ProdutoNotFoundException;
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

		if (produto == null) {
			throw new ProdutoNotFoundException(id);
		}

		return toResponse(produto);
	}

	public ProdutoResponse criarProduto(ProdutoRequest request) {

		ProdutoEntity entity = toEntity(request);

		entity = produtoRepository.criarProduto(entity);

		return toResponse(entity);

	}

	public ProdutoResponse atualizar(Long id, ProdutoUpdateRequest request) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);

		if (produto == null) {
			throw new ProdutoNotFoundException(id);
		}

		produto.setNome(request.nome());
		produto.setDescricao(request.descricao());
		produto.setPreco(request.preco());

		produto = produtoRepository.editar(produto);

		return toResponse(produto);
	}

	public ProdutoResponse patch(Long id, ProdutoPatchRequest request) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);

		if (produto == null) {
			throw new ProdutoNotFoundException(id);
		}

		request.nome().ifPresent(produto::setNome);
		request.descricao().ifPresent(produto::setDescricao);
		request.preco().ifPresent(produto::setPreco);

		produto = produtoRepository.editar(produto);

		return toResponse(produto);
	}

	public void deletar(Long id) {
		ProdutoEntity produto = produtoRepository.buscarPorId(id);

		if (produto == null) {
			throw new ProdutoNotFoundException(id);
		}

		produtoRepository.delete(produto);
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
