package br.com.alura.orcamentoFamiliar.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.orcamentoFamiliar.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	public Optional<Despesa> findByDescricaoAndDataBetween(String descricao, LocalDate startDate, LocalDate endDate);
	public List<Despesa> findByDescricao(String descricao);
	public List<Despesa> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);

}
