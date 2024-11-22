package br.com.pontoeletronico.api.controller;

import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.model.FuncionarioPonto;
import br.com.pontoeletronico.api.service.FuncionarioPontoService;
import br.com.pontoeletronico.api.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos")
public class FuncionarioPontoController {

    @Autowired
    private FuncionarioPontoService funcionarioPontoService;

    @Autowired
    private FuncionarioService funcionarioService;


    @PostMapping("/{funcionarioId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registrarPonto(@PathVariable Long funcionarioId) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + funcionarioId));

        funcionarioPontoService.registrarPonto(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Ponto registrado com sucesso.");
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<List<FuncionarioPonto>> listarPontosPorFuncionario(@PathVariable Long funcionarioId) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + funcionarioId));

        List<FuncionarioPonto> pontos = funcionarioPontoService.listarPontosPorFuncionario(funcionario);
        return ResponseEntity.ok(pontos);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioPonto>> listarTodosPontos() {
        return ResponseEntity.ok(funcionarioPontoService.listarPontos());
    }

}
