package br.com.impacta.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.impacta.lab.validation.BusinessValdation;
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
	
	@Autowired
	private List<BusinessValdation> validations;

	
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
	
	@Operation(summary = "Operacao de criar um novo produto")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ProdutoResponse.class)) }),
			  @ApiResponse(responseCode = "400", description = "Dados invalidos", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping
	public ResponseEntity<ProdutoResponse> criarProduto(@Valid @RequestBody ProdutoRequest request) {
		
		for (var validation :  validations) {
			validation.validar(request);
		}
		
		ProdutoResponse produto = produtoService.criarProduto(request);
		
		return ResponseEntity.created(null).body(produto);
	}
	
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ProdutoResponse.class)) }),
			  @ApiResponse(responseCode = "400", description = "Dados invalidos", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoUpdateRequest request) {
		
		ProdutoResponse produto = produtoService.atualizarProduto(id, request);
		
		return ResponseEntity.ok(produto);
	}
	
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Produto atualizado parcialmente com sucesso", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ProdutoResponse.class)) }),
			  @ApiResponse(responseCode = "400", description = "Dados invalidos", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PatchMapping("/{id}")
	public ResponseEntity<ProdutoResponse> atualizaParcialProduto(@PathVariable Long id, @Valid @RequestBody ProdutoPatchRequest request) {
		
		ProdutoResponse produto = produtoService.atualizaParcialProduto(id, request);
		
		return ResponseEntity.ok(produto);
	}
	
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "202", description = "Produto deletado com sucesso", 
			    content = { @Content(mediaType = "application/json") }),
			  @ApiResponse(responseCode = "404", description = "Dados invalidos", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ErrorResponse.class)) }) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaProduto(@PathVariable Long id) {
		produtoService.deletaProduto(id);
		return ResponseEntity.accepted().build();
	}
	
	
}
