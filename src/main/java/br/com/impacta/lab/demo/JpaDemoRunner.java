package br.com.impacta.lab.demo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.impacta.lab.entity.Categoria;
import br.com.impacta.lab.entity.Produto;
import br.com.impacta.lab.entity.ProdutoTag;
import br.com.impacta.lab.entity.Promocao;
import br.com.impacta.lab.entity.Tag;
import br.com.impacta.lab.repository.CategoriaRepository;
import br.com.impacta.lab.repository.ProdutoRepository;
import br.com.impacta.lab.repository.ProdutoTagRepository;
import br.com.impacta.lab.repository.PromocaoRepository;
import br.com.impacta.lab.repository.TagRepository;

@Component
public class JpaDemoRunner implements CommandLineRunner {

	private final CategoriaRepository categoriaRepository;
	private final TagRepository tagRepository;
	private final ProdutoRepository produtoRepository;
	private final ProdutoTagRepository produtoTagRepository;
	private final PromocaoRepository promocaoRepository;

	public JpaDemoRunner(CategoriaRepository categoriaRepository,
			TagRepository tagRepository,
			ProdutoRepository produtoRepository,
			ProdutoTagRepository produtoTagRepository,
			PromocaoRepository promocaoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.tagRepository = tagRepository;
		this.produtoRepository = produtoRepository;
		this.produtoTagRepository = produtoTagRepository;
		this.promocaoRepository = promocaoRepository;
	}

	@Override
	@Transactional
	public void run(String... args) {
		if (categoriaRepository.count() > 0) {
			System.out.println(">>> dados já populados, pulando.");
			return;
		}

		System.out.println(">>> populando dados de exemplo");

		Categoria eletronicos = new Categoria();
		eletronicos.setNome("Eletronicos");
		eletronicos = categoriaRepository.save(eletronicos);

		Produto notebook = new Produto();
		notebook.setNome("Notebook");
		notebook.setPreco(3500.0);
		notebook.setDescricao("Notebook gamer");
		notebook.setCategoria(eletronicos);
		notebook = produtoRepository.save(notebook);

		Tag promocaoTag = new Tag();
		promocaoTag.setNome("promocao");
		promocaoTag = tagRepository.save(promocaoTag);

		Tag lancamentoTag = new Tag();
		lancamentoTag.setNome("lancamento");
		lancamentoTag = tagRepository.save(lancamentoTag);

		ProdutoTag pt1 = produtoTagRepository.save(new ProdutoTag(notebook, promocaoTag, LocalDate.now()));
		ProdutoTag pt2 = produtoTagRepository.save(new ProdutoTag(notebook, lancamentoTag, LocalDate.now()));
		notebook.getProdutoTags().add(pt1);
		notebook.getProdutoTags().add(pt2);

		Promocao blackFriday = new Promocao();
		blackFriday.setNome("Black Friday");
		blackFriday = promocaoRepository.save(blackFriday);

		Promocao cyberMonday = new Promocao();
		cyberMonday.setNome("Cyber Monday");
		cyberMonday = promocaoRepository.save(cyberMonday);

		notebook.getPromocoes().add(blackFriday);
		notebook.getPromocoes().add(cyberMonday);
		notebook = produtoRepository.save(notebook);

		System.out.println(">>> findAll Categorias: " + categoriaRepository.findAll().size());
		System.out.println(">>> findAll Tags: " + tagRepository.findAll().size());
		System.out.println(">>> findAll Produtos: " + produtoRepository.findAll().size());
		System.out.println(">>> findAll ProdutoTags: " + produtoTagRepository.findAll().size());
		System.out.println(">>> findAll Promocoes: " + promocaoRepository.findAll().size());

		
	}

}
