import React, { useState } from 'react';

export default function Login({ onLoginSuccess }) {
  const [modo, setModo] = useState('login'); // 'login' ou 'cadastro'

  // Estados para Login
  const [usernameLogin, setUsernameLogin] = useState('');
  const [senhaLogin, setSenhaLogin] = useState('');

  // Estados para Cadastro de Cliente
  const [nome, setNome] = useState('');
  const [usernameCad, setUsernameCad] = useState('');
  const [senhaCad, setSenhaCad] = useState('');
  const [rg, setRg] = useState('');
  const [cpf, setCpf] = useState('');
  const [endereco, setEndereco] = useState('');
  const [profissao, setProfissao] = useState('');
  const [entidadeEmpregadora, setEntidadeEmpregadora] = useState('');
  const [rendimentos, setRendimentos] = useState(['']);

  const handleRendimentoChange = (index, value) => {
    const novosRendimentos = [...rendimentos];
    novosRendimentos[index] = value;
    setRendimentos(novosRendimentos);
  };

  const addRendimento = () => {
    if (rendimentos.length < 3) {
      setRendimentos([...rendimentos, '']);
    } else {
      alert('Máximo de 3 rendimentos permitidos.');
    }
  };

  const removeRendimento = (index) => {
    const novosRendimentos = rendimentos.filter((_, i) => i !== index);
    setRendimentos(novosRendimentos.length > 0 ? novosRendimentos : ['']);
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: usernameLogin, senha: senhaLogin })
      });

      if (res.ok) {
        const user = await res.json();
        onLoginSuccess(user);
      } else {
        alert('Usuário ou senha incorretos.');
      }
    } catch (err) {
      console.error(err);
      alert('Erro ao conectar com o servidor Spring Boot.');
    }
  };

  const handleCadastroSubmit = async (e) => {
    e.preventDefault();
    const rendimentosNumericos = rendimentos
      .map(r => parseFloat(r))
      .filter(r => !isNaN(r));

    const payload = {
      nome,
      username: usernameCad,
      senha: senhaCad,
      rg,
      cpf,
      endereco,
      profissao,
      entidadeEmpregadora,
      rendimentos: rendimentosNumericos
    };

    try {
      const res = await fetch('http://localhost:8080/api/clientes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (res.ok) {
        alert('Conta criada com sucesso! Faça login para continuar.');
        setModo('login');
        setUsernameLogin(usernameCad);
        setSenhaLogin('');
      } else {
        alert('Erro ao realizar cadastro.');
      }
    } catch (err) {
      console.error(err);
      alert('Erro ao conectar ao servidor.');
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '50px', marginBottom: '50px' }}>
      <div className="card" style={{ width: '420px' }}>
        <h2 style={{ color: '#FF6600', textAlign: 'center', margin: '0 0 15px 0' }}>Aluga Já 🚗</h2>

        {/* Abas para alternar entre Login e Cadastro */}
        <div style={{ display: 'flex', marginBottom: '20px', borderBottom: '1px solid #ccc' }}>
          <button
            type="button"
            onClick={() => setModo('login')}
            style={{
              flex: 1,
              padding: '10px',
              border: 'none',
              background: modo === 'login' ? '#FF6600' : 'transparent',
              color: modo === 'login' ? '#fff' : '#333',
              fontWeight: 'bold',
              cursor: 'pointer',
              borderRadius: '4px 0 0 0'
            }}
          >
            Entrar
          </button>
          <button
            type="button"
            onClick={() => setModo('cadastro')}
            style={{
              flex: 1,
              padding: '10px',
              border: 'none',
              background: modo === 'cadastro' ? '#FF6600' : 'transparent',
              color: modo === 'cadastro' ? '#fff' : '#333',
              fontWeight: 'bold',
              cursor: 'pointer',
              borderRadius: '0 4px 0 0'
            }}
          >
            Cadastrar
          </button>
        </div>

        {/* Formulário de Login */}
        {modo === 'login' ? (
          <form onSubmit={handleLoginSubmit}>
            <div style={{ marginBottom: '15px' }}>
              <label style={{ fontWeight: 'bold' }}>Usuário:</label>
              <input
                type="text"
                style={{ width: '100%', padding: '8px', marginTop: '5px', boxSizing: 'border-box' }}
                value={usernameLogin}
                onChange={e => setUsernameLogin(e.target.value)}
                required
              />
            </div>
            <div style={{ marginBottom: '15px' }}>
              <label style={{ fontWeight: 'bold' }}>Senha:</label>
              <input
                type="password"
                style={{ width: '100%', padding: '8px', marginTop: '5px', boxSizing: 'border-box' }}
                value={senhaLogin}
                onChange={e => setSenhaLogin(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="btn-primary" style={{ width: '100%' }}>
              Entrar no Sistema
            </button>
          </form>
        ) : (
          /* Formulário de Cadastro de Cliente */
          <form onSubmit={handleCadastroSubmit}>
            <div style={{ marginBottom: '10px' }}>
              <label>Nome Completo:</label>
              <input type="text" value={nome} onChange={e => setNome(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
            </div>
            <div style={{ display: 'flex', gap: '10px', marginBottom: '10px' }}>
              <div style={{ flex: 1 }}>
                <label>Usuário:</label>
                <input type="text" value={usernameCad} onChange={e => setUsernameCad(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
              </div>
              <div style={{ flex: 1 }}>
                <label>Senha:</label>
                <input type="password" value={senhaCad} onChange={e => setSenhaCad(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
              </div>
            </div>
            <div style={{ display: 'flex', gap: '10px', marginBottom: '10px' }}>
              <div style={{ flex: 1 }}>
                <label>RG:</label>
                <input type="text" value={rg} onChange={e => setRg(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
              </div>
              <div style={{ flex: 1 }}>
                <label>CPF:</label>
                <input type="text" value={cpf} onChange={e => setCpf(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
              </div>
            </div>
            <div style={{ marginBottom: '10px' }}>
              <label>Endereço:</label>
              <input type="text" value={endereco} onChange={e => setEndereco(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
            </div>
            <div style={{ display: 'flex', gap: '10px', marginBottom: '10px' }}>
              <div style={{ flex: 1 }}>
                <label>Profissão:</label>
                <input type="text" value={profissao} onChange={e => setProfissao(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
              </div>
              <div style={{ flex: 1 }}>
                <label>Empregador:</label>
                <input type="text" value={entidadeEmpregadora} onChange={e => setEntidadeEmpregadora(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
              </div>
            </div>

            <div style={{ marginBottom: '15px' }}>
              <label style={{ display: 'block', marginBottom: '5px' }}>Rendimentos (Máx 3):</label>
              {rendimentos.map((rend, idx) => (
                <div key={idx} style={{ display: 'flex', gap: '5px', marginBottom: '5px' }}>
                  <input
                    type="number"
                    placeholder={`Rendimento ${idx + 1}`}
                    value={rend}
                    onChange={e => handleRendimentoChange(idx, e.target.value)}
                    style={{ flex: 1, padding: '8px', boxSizing: 'border-box' }}
                  />
                  {rendimentos.length > 1 && (
                    <button 
                      type="button" 
                      onClick={() => removeRendimento(idx)}
                      style={{ background: '#dc3545', color: '#fff', border: 'none', padding: '0 10px', borderRadius: '4px', cursor: 'pointer' }}
                    >
                      X
                    </button>
                  )}
                </div>
              ))}
              {rendimentos.length < 3 && (
                <button type="button" onClick={addRendimento} style={{ marginTop: '5px', padding: '5px 10px', cursor: 'pointer', background: '#eee', border: '1px solid #ccc', borderRadius: '4px' }}>
                  + Adicionar Rendimento
                </button>
              )}
            </div>

            <button type="submit" className="btn-primary" style={{ width: '100%' }}>
              Criar Minha Conta
            </button>
          </form>
        )}
      </div>
    </div>
  );
}