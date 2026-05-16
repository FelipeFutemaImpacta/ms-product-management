package br.com.impacta.lab.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.impacta.lab.entity.ProdutoEntity;

@Repository
public class ProdutoRepository {

	private List<ProdutoEntity> produtos = new ArrayList<>();
	
	private Long sequencia = 1l; 
	
	public List<ProdutoEntity> listarTodos() {
		return produtos;
	}
	
	public ProdutoEntity buscarPorId(Long id) {
		for(var produto : produtos) {
			if (produto.getId() == id) {
				return produto;
			}
		}
		return null;
	}
	
	public ProdutoEntity criarProduto(ProdutoEntity produto) {
		produto.setId(sequencia);
		produtos.add(produto);
		
		sequencia = sequencia + 1;
		
		return produto;
	}
	
	public ProdutoEntity atualizar(ProdutoEntity produto) {
		
		for (int i = 0; i < produtos.size(); i++) {
			if (produto.getId() == produtos.get(i).getId()) {
				produtos.set(i, produto);
			}
		}
		return produto;
	}
	
	public void deletar(ProdutoEntity produto) {
		produtos.remove(produto);
	}
	
}
