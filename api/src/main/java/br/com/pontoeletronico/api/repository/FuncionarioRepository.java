package br.com.pontoeletronico.api.repository;

import br.com.pontoeletronico.api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByCpf(String cpf);
}
