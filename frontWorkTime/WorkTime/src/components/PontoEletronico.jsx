import { useState } from "react";
import "./PontoEletronico.css"; 
import axios from "axios";

export default function PontoEletronico() {
  const [cpf, setCpf] = useState("");
  const [funcionarioId, setFuncionarioId] = useState(null);
  const [clockedIn, setClockedIn] = useState(false);
  const [erro, setErro] = useState(""); 

  const maskCPF = (value) => {
    return value
      .replace(/\D/g, "")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d)/, "$1.$2")
      .replace(/(\d{3})(\d{1,2})/, "$1-$2")
      .replace(/(-\d{2})\d+?$/, "$1");
  };

  const handleCpfChange = (e) => {
    setCpf(maskCPF(e.target.value));
  };

  const buscarFuncionarioId = async () => {
    try {
      console.log("Enviando CPF para o backend:", cpf); // Log do CPF enviado
      const response = await axios.get("http://localhost:8080/funcionarios/cpf", {
        params: { cpf: cpf.replace(/\D/g, "") }, // Remove a formatação
      });
      console.log("ID do funcionário encontrado:", response.data); // Log do ID retornado
      setFuncionarioId(response.data);
      setErro("");
    } catch (error) {
      console.error("Erro ao buscar funcionário:", error);
      setErro("Funcionário não encontrado.");
      setFuncionarioId(null);
    }
  };  

  const registrarPonto = async () => {
    if (funcionarioId) {
      try {
        const response = await axios.post(`http://localhost:8080/pontos/${funcionarioId}`);
        console.log("Resposta do backend:", response.data); // Log da resposta do backend
        setClockedIn(true);
        setErro("");
      } catch (error) {
        console.error("Erro ao registrar ponto:", error);
        setErro("Erro ao registrar o ponto.");
      }
    } else {
      setErro("Funcionário não encontrado.");
    }
  };
  

  const handleClockIn = async () => {
    try {
      await buscarFuncionarioId(); // Espera a busca do funcionário
      if (funcionarioId) {
        await registrarPonto(); // Só chama registrarPonto após buscarFuncionarioId
      } else {
        setErro("Funcionário não encontrado.");
      }
    } catch (error) {
      console.error("Erro ao bater ponto:", error);
      setErro("Erro ao bater ponto.");
    }
  };
  

  const handleGoBack = () => {
    setClockedIn(false);
    setCpf("");
  };

  return (
    <div className="container">
      <div className="card">
        <div className="card-content">
          {!clockedIn ? (
            <>
              <div className="card-head">
                <div className="card-logo">
                  <img src="./public/logo.png" alt="Logo da empresa" className="logo" />
                </div>
                <div className="input-group">
                  <label htmlFor="cpf" className="label">CPF</label>
                  <input
                    id="cpf"
                    type="text"
                    placeholder="000.000.000-00"
                    value={cpf}
                    onChange={handleCpfChange}
                    className="input"
                    maxLength={14}
                  />
                </div>
              </div>

              <button
                onClick={handleClockIn}
                className="button"
                disabled={cpf.length !== 14}
              >
                BATER PONTO
              </button>

              {erro && <p style={{ color: "red" }}>{erro}</p>}
            </>
          ) : (
            <div className="success">
              <img src="/check.png" alt="check" className="check" />
              <h2 className="success-title">Ponto registrado com sucesso!</h2>
              <p className="success-message">Que seu dia seja produtivo, com foco e motivação para alcançar grandes resultados!</p>
            </div>
          )}
        </div>

        {clockedIn && (
          <div className="footer">
            <a href="#" onClick={handleGoBack} className="link">
              Retornar para pagina principal
            </a>
          </div>
        )}
      </div>
    </div>
  );
}
