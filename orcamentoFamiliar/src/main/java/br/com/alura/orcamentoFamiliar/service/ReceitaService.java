package br.com.alura.orcamentoFamiliar.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.alura.orcamentoFamiliar.modelo.Receita;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository repository;
	
	@Transactional
	public List<Receita> findByData(Integer ano, Integer mes) {

		LocalDate dataInicial = LocalDate.of(ano, mes, 1);
		LocalDate dataFinal = dataInicial.with(TemporalAdjusters.lastDayOfMonth());

		List<Receita> receitas = repository.findByDataBetween(dataInicial, dataFinal);
		return receitas;
	}

}
