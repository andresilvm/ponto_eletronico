package br.com.pontoeletronico.api.controller;

import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.cadastrarFuncionario(funcionario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioService.listarTodosFuncionarios();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFuncionarioPorId(@PathVariable Long id) {
        funcionarioService.deletarFuncionarioPorId(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/cpf")
    public ResponseEntity<Long> getFuncionarioIdByCpf(@RequestParam String cpf) {
        System.out.println("Recebendo CPF: " + cpf);
        Long funcionarioId = funcionarioService.getIdByCpf(cpf);
        if (funcionarioId != null) {
            return ResponseEntity.ok(funcionarioId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
