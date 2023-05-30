package br.com.alura.orcamentoFamiliar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.orcamentoFamiliar.controller.vo.ReceitaVO;
import br.com.alura.orcamentoFamiliar.modelo.Receita;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaRepository receitaRepository;

	@GetMapping()
	public ResponseEntity<List<ReceitaVO>> listarTodasReceitas() {
		List<Receita> receitas = receitaRepository.findAll();
		return ResponseEntity.ok().body(ReceitaVO.converterListaReceitaEntidadeParaVo(receitas));
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

}

