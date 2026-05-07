package br.com.impacta.lab.service;

import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.model.Produto;
import br.com.impacta.lab.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<ProdutoResponse> buscarPorId(Long id) {
        return produtoRepository.findById(id).map(this::toResponse);
    }

    public ProdutoResponse criar(ProdutoRequest request) {
        Produto salvo = produtoRepository.save(toModel(request));
        return toResponse(salvo);
    }

    private Produto toModel(ProdutoRequest request) {
        return new Produto(null, request.nome(), request.descricao(), request.preco());
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco());
    }
}
