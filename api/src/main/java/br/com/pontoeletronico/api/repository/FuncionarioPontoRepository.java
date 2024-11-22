package br.com.pontoeletronico.api.repository;

import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.model.FuncionarioPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioPontoRepository extends JpaRepository<FuncionarioPonto, Long> {
    @Query("SELECT fp FROM FuncionarioPonto fp WHERE fp.funcionario = :funcionario ORDER BY fp.dataHora DESC")
    List<FuncionarioPonto> findByFuncionario(@Param("funcionario") Funcionario funcionario);

}
