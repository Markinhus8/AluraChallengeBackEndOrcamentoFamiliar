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
import br.com.alura.orcamentoFamiliar.controller.form.AtualizacaoDespesaForm;
import br.com.alura.orcamentoFamiliar.controller.form.DespesaForm;
import br.com.alura.orcamentoFamiliar.controller.vo.DespesaVO;
import br.com.alura.orcamentoFamiliar.modelo.Despesa;
import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;
import br.com.alura.orcamentoFamiliar.service.DespesaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private DespesaService service;

	
	@GetMapping
	public ResponseEntity<List<DespesaVO>> listarTodasDespesas(String descricao) {
		if (descricao == null) {
			List<Despesa> despesas = despesaRepository.findAll();
			return ResponseEntity.ok().body(DespesaVO.converterListaDespesaEntidadeParaVo(despesas));
		} else {
			List<Despesa> despesas = despesaRepository.findByDescricao(descricao);
			return ResponseEntity.ok().body(DespesaVO.converterListaDespesaEntidadeParaVo(despesas));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaVO> detalharDespesa(@PathVariable Long id){
		Optional<Despesa> despesa = despesaRepository.findById(id);

		if (!despesa.isPresent()) {
			return ResponseEntity
					.notFound()
					.build();
		}

		return ResponseEntity.ok().body(new DespesaVO(despesa.get()));
	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<DespesaVO>> buscarPeloMesAno(@PathVariable Integer ano, @PathVariable Integer mes) {
		List<Despesa> despesas = service.buscarPeloMesAno(ano, mes);
		if (despesas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(DespesaVO.converterListaDespesaEntidadeParaVo(despesas));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarDespesa(@Valid @RequestBody DespesaForm form, 
			UriComponentsBuilder uriBuilder) {

		if (form.validarMesmaDescricaoDentroDoMesmoMes(despesaRepository)) {
			return ResponseEntity
					.badRequest()
					.body("Despesa duplicada");
		}

		Despesa despesa = form.converterParaEntidadeDespesa();
		Despesa itemSalvo = despesaRepository.save(despesa);

		URI uri = uriBuilder
				.path("/despesas/{id}")
				.buildAndExpand(itemSalvo.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(new DespesaVO(itemSalvo));
	}
	
	//As mesmas regras de negócio do cadastro de uma despesa foram realizadas também na atualização dela.
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoDespesaForm form, UriComponentsBuilder uriBuilder) {
			Optional<Despesa> optional = despesaRepository.findById(id);
			if (optional.isPresent()) {
				if (form.validarMesmaDescricaoDentroDoMesmoMes(despesaRepository)) {
					return ResponseEntity
							.badRequest()
							.body("Despesa duplicada");
				}else{
					Despesa despesa = form.atualizar(id, despesaRepository);
					return ResponseEntity.ok(new DespesaVO(despesa));
					
				}

			}

			return ResponseEntity.notFound().build();
		}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		despesaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
