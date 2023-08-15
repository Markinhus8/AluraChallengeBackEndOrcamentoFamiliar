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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.orcamentoFamiliar.controller.form.DespesaForm;
import br.com.alura.orcamentoFamiliar.controller.vo.DespesaVO;
import br.com.alura.orcamentoFamiliar.modelo.Despesa;
import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private DespesaRepository despesaRepository;

	
	@GetMapping()
	public ResponseEntity<List<DespesaVO>> listarTodasDespesas() {
		List<Despesa> despesas = despesaRepository.findAll();

		return ResponseEntity.ok().body(DespesaVO.converterListaDespesaEntidadeParaVo(despesas));
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
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		despesaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
