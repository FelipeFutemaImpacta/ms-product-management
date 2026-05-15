package br.com.impacta.lab.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.lab.dto.ErrorResponse;
import br.com.impacta.lab.dto.ProdutoPatchRequest;
import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.dto.ProdutoResponse;
import br.com.impacta.lab.dto.ProdutoUpdateRequest;
import br.com.impacta.lab.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {

	private ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@Operation(summary = "Lista todos os produtos")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso",
					content = @Content(schema = @Schema(implementation = ProdutoResponse.class)))
	})
	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
		List<ProdutoResponse> produtos = produtoService.listarTodos();

		return ResponseEntity.ok().header("teste", "123").body(produtos);

	}

	@Operation(summary = "Busca um produto pelo id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Produto encontrado",
					content = @Content(schema = @Schema(implementation = ProdutoResponse.class))),
			@ApiResponse(responseCode = "404", description = "Produto nao encontrado",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(produtoService.buscarPorId(id));
	}

	@Operation(summary = "Cria um novo produto")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
					content = @Content(schema = @Schema(implementation = ProdutoResponse.class))),
			@ApiResponse(responseCode = "400", description = "Dados invalidos",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping
	public ResponseEntity<ProdutoResponse> criarProduto(@Valid @RequestBody ProdutoRequest request) {

		ProdutoResponse produto = produtoService.criarProduto(request);

		return ResponseEntity.created(null).body(produto);
	}

	@Operation(summary = "Atualiza um produto existente")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
					content = @Content(schema = @Schema(implementation = ProdutoResponse.class))),
			@ApiResponse(responseCode = "400", description = "Dados invalidos",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "Produto nao encontrado",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id,
			@Valid @RequestBody ProdutoUpdateRequest request) {
		return ResponseEntity.ok(produtoService.atualizar(id, request));
	}

	@Operation(summary = "Atualiza parcialmente um produto")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
					content = @Content(schema = @Schema(implementation = ProdutoResponse.class))),
			@ApiResponse(responseCode = "404", description = "Produto nao encontrado",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PatchMapping("/{id}")
	public ResponseEntity<ProdutoResponse> patch(@PathVariable Long id,
			@RequestBody ProdutoPatchRequest request) {
		return ResponseEntity.ok(produtoService.patch(id, request));
	}

	@Operation(summary = "Remove um produto")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Produto nao encontrado",
					content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		produtoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
