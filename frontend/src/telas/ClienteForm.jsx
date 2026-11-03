import React, { useState } from 'react';

export default function ClienteForm({ clienteAtual, onSaveSuccess }) {
  const [nome, setNome] = useState(clienteAtual?.nome || '');
  const [username, setUsername] = useState(clienteAtual?.username || '');
  const [senha, setSenha] = useState(clienteAtual?.senha || '');
  const [rg, setRg] = useState(clienteAtual?.rg || '');
  const [cpf, setCpf] = useState(clienteAtual?.cpf || '');
  const [endereco, setEndereco] = useState(clienteAtual?.endereco || '');
  const [profissao, setProfissao] = useState(clienteAtual?.profissao || '');
  const [entidadeEmpregadora, setEntidadeEmpregadora] = useState(clienteAtual?.entidadeEmpregadora || '');
  const [rendimentos, setRendimentos] = useState(clienteAtual?.rendimentos || [0]);

  const handleRendimentoChange = (index, value) => {
    const novosRendimentos = [...rendimentos];
    novosRendimentos[index] = parseFloat(value) || 0;
    setRendimentos(novosRendimentos);
  };

  const addRendimento = () => {
    if (rendimentos.length < 3) {
      setRendimentos([...rendimentos, 0]);
    } else {
      alert('Máximo de 3 rendimentos permitidos.');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = { nome, username, senha, rg, cpf, endereco, profissao, entidadeEmpregadora, rendimentos };
    const method = clienteAtual?.id ? 'PUT' : 'POST';
    const url = clienteAtual?.id 
      ? `http://localhost:8080/api/clientes/${clienteAtual.id}` 
      : 'http://localhost:8080/api/clientes';

    try {
      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (res.ok) {
        alert('Dados do cliente guardados com sucesso!');
        if (onSaveSuccess) onSaveSuccess();
      } else {
        alert('Erro ao guardar os dados.');
      }
    } catch (err) {
      console.error(err);
      alert('Erro ao conectar ao servidor.');
    }
  };

  return (
    <div className="card" style={{ maxWidth: '600px', margin: '20px auto' }}>
      <h2 style={{ color: '#FF6600', borderBottom: '2px solid #FF6600', paddingBottom: '10px' }}>
        {clienteAtual?.id ? 'Editar Dados do Cliente' : 'Cadastro de Cliente'}
      </h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px' }}>
          <label>Nome Completo:</label>
          <input type="text" value={nome} onChange={e => setNome(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
        </div>
        <div style={{ display: 'flex', gap: '10px', marginBottom: '10px' }}>
          <div style={{ flex: 1 }}>
            <label>Usuário:</label>
            <input type="text" value={username} onChange={e => setUsername(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
          </div>
          <div style={{ flex: 1 }}>
            <label>Senha:</label>
            <input type="password" value={senha} onChange={e => setSenha(e.target.value)} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
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
          <label>Rendimentos (Máximo 3):</label>
          {rendimentos.map((rend, idx) => (
            <div key={idx} style={{ marginTop: '5px' }}>
              <input 
                type="number" 
                placeholder={`Rendimento ${idx + 1}`}
                value={rend} 
                onChange={e => handleRendimentoChange(idx, e.target.value)} 
                style={{ width: '80%', padding: '8px' }}
              />
            </div>
          ))}
          {rendimentos.length < 3 && (
            <button type="button" onClick={addRendimento} style={{ marginTop: '5px', padding: '5px 10px', cursor: 'pointer' }}>
              + Adicionar Rendimento
            </button>
          )}
        </div>

        <button type="submit" className="btn-primary" style={{ width: '100%' }}>Guardar Cadastro</button>
      </form>
    </div>
  );
}