package br.com.alura.orcamentoFamiliar.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.orcamentoFamiliar.modelo.Receita;

public interface ReceitaRepository extends JpaRepository <Receita, Long> {
	
	public Optional<Receita> findByDescricaoAndDataBetween(String descricao, LocalDate startDate, LocalDate endDate);
	public List<Receita> findByDescricao(String descricao);

}
