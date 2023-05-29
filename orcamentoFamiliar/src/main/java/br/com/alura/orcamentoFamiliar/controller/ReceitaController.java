package br.com.alura.orcamentoFamiliar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.orcamentoFamiliar.controller.dto.ReceitaVO;
import br.com.alura.orcamentoFamiliar.modelo.Receita;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;


@RestController
public class ReceitaController {
	
	@Autowired
	private ReceitaRepository receitaRepository;

	@RequestMapping("/receitas")
	public List<ReceitaVO> listarTodasReceitas() {
		//testee
		List<Receita> receitas = receitaRepository.findAll();
		return ReceitaVO.converter(receitas);
	}

}
