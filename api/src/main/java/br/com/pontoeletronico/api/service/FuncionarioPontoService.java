package br.com.pontoeletronico.api.service;

import br.com.pontoeletronico.api.enums.TipoRegistro; // Nova importação
import br.com.pontoeletronico.api.model.Funcionario;
import br.com.pontoeletronico.api.model.FuncionarioPonto;
import br.com.pontoeletronico.api.repository.FuncionarioPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuncionarioPontoService {

    @Autowired
    private FuncionarioPontoRepository funcionarioPontoRepository;

    public void registrarPonto(Funcionario funcionario) {
        List<FuncionarioPonto> pontos = funcionarioPontoRepository.findByFuncionario(funcionario);

        TipoRegistro tipoRegistro;

        if (pontos.isEmpty()) {
            tipoRegistro = TipoRegistro.ENTRADA;
            System.out.println("Nenhum registro encontrado. Registrando ENTRADA.");
        } else {
            TipoRegistro ultimoRegistro = pontos.get(0).getTipoRegistro();
            System.out.println("Último registro encontrado: " + ultimoRegistro);

            if (ultimoRegistro == TipoRegistro.SAIDA) {
                tipoRegistro = TipoRegistro.ENTRADA;
                System.out.println("Registrando ENTRADA após SAÍDA.");
            } else {
                tipoRegistro = TipoRegistro.SAIDA;
                System.out.println("Registrando SAÍDA após ENTRADA.");
            }
        }

        FuncionarioPonto novoPonto = new FuncionarioPonto();
        novoPonto.setFuncionario(funcionario);
        novoPonto.setDataHora(LocalDateTime.now());
        novoPonto.setTipoRegistro(tipoRegistro);

        funcionarioPontoRepository.save(novoPonto);
        System.out.println("Novo registro salvo: " + novoPonto);
    }



    public List<FuncionarioPonto> listarPontosPorFuncionario(Funcionario funcionario) {
        return funcionarioPontoRepository.findByFuncionario(funcionario);
    }

    public List<FuncionarioPonto> listarPontos() {
        return funcionarioPontoRepository.findAll();
    }


}
