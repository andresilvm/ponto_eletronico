package br.com.pontoeletronico.api.service;

import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public void deletarFuncionarioPorId(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Long getIdByCpf(String cpf) {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
        return funcionario != null ? funcionario.getId() : null;
    }
}
