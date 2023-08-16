package br.com.alura.orcamentoFamiliar.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.alura.orcamentoFamiliar.controller.form.AtualizacaoReceitaForm;
import br.com.alura.orcamentoFamiliar.controller.form.ReceitaForm;
import br.com.alura.orcamentoFamiliar.controller.vo.ReceitaVO;
import br.com.alura.orcamentoFamiliar.modelo.Receita;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaRepository receitaRepository;

	@GetMapping
	public ResponseEntity<List<ReceitaVO>> listarTodasReceitas(String descricao) {
		if (descricao == null) {
			List<Receita> receitas = receitaRepository.findAll();
			return ResponseEntity.ok().body(ReceitaVO.converterListaReceitaEntidadeParaVo(receitas));
		} else {
			List<Receita> receitas = receitaRepository.findByDescricao(descricao);
			return ResponseEntity.ok().body(ReceitaVO.converterListaReceitaEntidadeParaVo(receitas));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaVO> detalharReceita(@PathVariable Long id){
		Optional<Receita> receita = receitaRepository.findById(id);

		if (!receita.isPresent()) {
			return ResponseEntity
					.notFound()
					.build();
		}

		return ResponseEntity.ok().body(new ReceitaVO(receita.get()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarReceita(@Valid @RequestBody ReceitaForm form, 
			UriComponentsBuilder uriBuilder) {

		if (form.validarMesmaDescricaoDentroDoMesmoMes(receitaRepository)) {
			return ResponseEntity
					.badRequest()
					.body("Receita duplicada");
		}

		Receita receita = form.converterParaEntidadeReceita();
		Receita itemSalvo = receitaRepository.save(receita);

		URI uri = uriBuilder
				.path("/receitas/{id}")
				.buildAndExpand(itemSalvo.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(new ReceitaVO(itemSalvo));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaVO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoReceitaForm form, UriComponentsBuilder uriBuilder) {
		Optional<Receita> optional = receitaRepository.findById(id);
		if (optional.isPresent()) {
			Receita receita = form.atualizar(id, receitaRepository);
			return ResponseEntity.ok(new ReceitaVO(receita));

		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity remover(@PathVariable Long id) {
		receitaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}

