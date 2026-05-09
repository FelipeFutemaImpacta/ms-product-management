package br.com.impacta.lab.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.service.ProdutoService;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {

	private ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
		List<ProdutoResponse> produtos = produtoService.listarTodos();
		
		return ResponseEntity.ok().header("teste", "123").body(produtos);
		
	}
	
	@GetMapping("/{xpto}")
	public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable("xpto") Long id) {
		ProdutoResponse produto = produtoService.buscarPorId(id);
		
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody ProdutoRequest request) {
		
		ProdutoResponse produto = produtoService.criarProduto(request);
		
		return ResponseEntity.created(null).body(produto);
	}
	
}
