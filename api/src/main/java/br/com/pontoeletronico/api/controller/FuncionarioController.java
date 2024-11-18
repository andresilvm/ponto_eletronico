package br.com.pontoeletronico.api.controller;

import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.cadastrarFuncionario(funcionario);
    }
}
