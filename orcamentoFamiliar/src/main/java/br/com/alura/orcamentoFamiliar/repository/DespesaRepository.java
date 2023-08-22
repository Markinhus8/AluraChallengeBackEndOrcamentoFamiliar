package br.com.alura.orcamentoFamiliar.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.orcamentoFamiliar.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	public Optional<Despesa> findByDescricaoAndDataBetween(String descricao, LocalDate startDate, LocalDate endDate);
	
	public List<Despesa> findByDescricao(String descricao);
	
	public List<Despesa> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
	
	@Query(value = "select SUM(valor) FROM despesa WHERE data >= ?1 AND data <= ?2", nativeQuery = true)
	public Optional<BigDecimal> sumBetweenData(LocalDate dataInicial, LocalDate dataFinal);
	
	@Query(value = "select categoria, SUM(valor) from despesa WHERE data >= ?1 AND data <= ?2 GROUP BY categoria", nativeQuery = true)
	public Collection<?> exibirTotalPorCategoria(LocalDate dataInicial, LocalDate dataFinal);

}
