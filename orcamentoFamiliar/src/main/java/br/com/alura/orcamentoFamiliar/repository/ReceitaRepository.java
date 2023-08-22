package br.com.alura.orcamentoFamiliar.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.orcamentoFamiliar.modelo.Receita;

public interface ReceitaRepository extends JpaRepository <Receita, Long> {
	
	public Optional<Receita> findByDescricaoAndDataBetween(String descricao, LocalDate startDate, LocalDate endDate);
	
	public List<Receita> findByDescricao(String descricao);
	
	public List<Receita> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
	
	@Query(value = "select SUM(valor) FROM receita WHERE data >= ?1 AND data <= ?2", nativeQuery = true)
	public Optional<BigDecimal> sumBetweenData(LocalDate dataInicial, LocalDate dataFinal);

}
