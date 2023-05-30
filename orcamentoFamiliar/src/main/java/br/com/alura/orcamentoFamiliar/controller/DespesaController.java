package br.com.alura.orcamentoFamiliar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.orcamentoFamiliar.controller.vo.DespesaVO;
import br.com.alura.orcamentoFamiliar.modelo.Despesa;
import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private DespesaRepository despesaRepository;

	@GetMapping()
	public ResponseEntity<List<DespesaVO>> listarTodasDespesas() {
		List<Despesa> despesas = despesaRepository.findAll();

		if (despesas == null || despesas.isEmpty() ) {
			return ResponseEntity
					.notFound()
					.build();
		}

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

}
