package br.com.impacta.lab.repository;

import br.com.impacta.lab.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    private static long sequencia = 1;

    private final List<Produto> produtos = new ArrayList<>();

    public List<Produto> findAll() {
        return produtos;
    }

    public Optional<Produto> findById(Long id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
    }

    public Produto save(Produto produto) {
        produto.setId(sequencia++);
        produtos.add(produto);
        return produto;
    }
}
