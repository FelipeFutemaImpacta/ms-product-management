package br.com.impacta.lab.repository;

import br.com.impacta.lab.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    public List<Produto> findAll() {
        return produtos;
    }

    public Optional<Produto> findById(Long id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
    }

    public Produto save(Produto produto) {
        produto.setId(sequencia.getAndIncrement());
        produtos.add(produto);
        return produto;
    }
}
