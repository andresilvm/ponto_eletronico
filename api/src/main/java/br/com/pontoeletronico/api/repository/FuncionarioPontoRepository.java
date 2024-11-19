package br.com.pontoeletronico.api.repository;

import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.model.FuncionarioPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioPontoRepository extends JpaRepository<FuncionarioPonto, Long> {
    List<FuncionarioPonto> findByFuncionario(Funcionario funcionario);
}
