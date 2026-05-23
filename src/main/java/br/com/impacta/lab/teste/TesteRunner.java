package br.com.impacta.lab.teste;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.impacta.lab.entity.CategoriaEntity;
import br.com.impacta.lab.entity.ProdutoEntity;
import br.com.impacta.lab.entity.ProdutoTagEntity;
import br.com.impacta.lab.entity.PromocaoEntity;
import br.com.impacta.lab.entity.TagEntity;
import br.com.impacta.lab.repository.CategoriaRepository;
import br.com.impacta.lab.repository.ProdutoRepository;
import br.com.impacta.lab.repository.ProdutoTagRepository;
import br.com.impacta.lab.repository.PromocaoRepository;
import br.com.impacta.lab.repository.TagRepository;

@Component
public class TesteRunner implements CommandLineRunner{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private ProdutoTagRepository produtoTagRepository;
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		CategoriaEntity categoriaEletronicos = new CategoriaEntity();
//		categoriaEletronicos.setNome("Eletronicos");
//		
//		categoriaRepository.save(categoriaEletronicos);
		
		Optional<CategoriaEntity> categoriaEletronicos = categoriaRepository.findById(1l);
		
		if (!categoriaEletronicos.isPresent()) {
			return;
		}
		
		var categoriaUm = categoriaEletronicos.get();
		
		var notebook = produtoRepository.findById(1l).get();
		
		//System.out.println(notebook.getPromocoes());
		
//		ProdutoEntity notebook = new ProdutoEntity();
//		notebook.setNome("Notebook");
//		notebook.setDescricao("Notebook LG");
//		notebook.setPreco(5000d);
//		notebook.setCategoria(categoriaUm);
//		
//		produtoRepository.save(notebook);
		
		//TagEntity lancamento = new TagEntity();
		//lancamento.setNome("Lancamento");
		
		//lancamento = tagRepository.save(lancamento);
		
		//produtoTagRepository.save(new ProdutoTagEntity(lancamento, notebook, LocalDate.now()));
		
		
//		PromocaoEntity blackFriday = new PromocaoEntity();
//		blackFriday.setNome("Black friday");
//		
//		blackFriday = promocaoRepository.save(blackFriday);
		
		Optional<PromocaoEntity> promoOpt = promocaoRepository.findById(1l);
		
		var blackFriday = promoOpt.get();
		
		List<PromocaoEntity> promocoes = new ArrayList<>();
		promocoes.add(blackFriday);
		
		notebook.setPromocoes(promocoes);
		produtoRepository.save(notebook);
		
		
	}

}
