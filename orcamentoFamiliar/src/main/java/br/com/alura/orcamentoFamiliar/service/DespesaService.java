package br.com.alura.orcamentoFamiliar.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.alura.orcamentoFamiliar.modelo.Despesa;
import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository repository;
	
	@Transactional
	public List<Despesa> buscarPeloMesAno(Integer ano, Integer mes) {

		LocalDate dataInicial = LocalDate.of(ano, mes, 1);
		LocalDate dataFinal = dataInicial.with(TemporalAdjusters.lastDayOfMonth());

		List<Despesa> despesas = repository.findByDataBetween(dataInicial, dataFinal);
		return despesas;
	}

}
