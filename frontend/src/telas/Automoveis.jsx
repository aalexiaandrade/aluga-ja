import React, { useEffect, useState } from 'react';

export default function Automoveis() {
  const [carros, setCarros] = useState([]);
  const [marca, setMarca] = useState('');
  const [modelo, setModelo] = useState('');
  const [ano, setAno] = useState('');
  const [placa, setPlaca] = useState('');
  const [matricula, setMatricula] = useState('');

  const carregarCarros = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/automoveis');
      if (res.ok) {
        const data = await res.json();
        setCarros(data);
      }
    } catch (err) {
      console.error('Erro ao carregar automóveis:', err);
    }
  };

  useEffect(() => {
    carregarCarros();
  }, []);

  const handleCadastrar = async (e) => {
    e.preventDefault();
    const novoCarro = { marca, modelo, ano: parseInt(ano), placa, matricula };

    try {
      const res = await fetch('http://localhost:8080/api/automoveis', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(novoCarro)
      });

      if (res.ok) {
        alert('Automóvel cadastrado com sucesso!');
        setMarca(''); setModelo(''); setAno(''); setPlaca(''); setMatricula('');
        carregarCarros();
      }
    } catch (err) {
      alert('Erro ao conectar ao servidor.');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '900px', margin: '0 auto' }}>
      <h2 style={{ color: '#FF6600', borderBottom: '2px solid #FF6600', paddingBottom: '10px' }}>
        Gestão de Automóveis - Aluga Já
      </h2>

      {/* Formulário de Cadastro */}
      <form className="card" onSubmit={handleCadastrar} style={{ marginBottom: '30px' }}>
        <h3>Cadastrar Novo Automóvel</h3>
        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '10px' }}>
          <input type="text" placeholder="Marca (ex: Fiat)" value={marca} onChange={e => setMarca(e.target.value)} required style={{ padding: '8px' }} />
          <input type="text" placeholder="Modelo (ex: Uno)" value={modelo} onChange={e => setModelo(e.target.value)} required style={{ padding: '8px' }} />
          <input type="number" placeholder="Ano (ex: 2024)" value={ano} onChange={e => setAno(e.target.value)} required style={{ padding: '8px' }} />
          <input type="text" placeholder="Placa (ex: ABC-1234)" value={placa} onChange={e => setPlaca(e.target.value)} required style={{ padding: '8px' }} />
          <input type="text" placeholder="Matrícula" value={matricula} onChange={e => setMatricula(e.target.value)} required style={{ padding: '8px' }} />
        </div>
        <button type="submit" className="btn-primary" style={{ marginTop: '15px', width: '100%' }}>
          Cadastrar Automóvel
        </button>
      </form>

      {/* Listagem de Carros */}
      <h3>Veículos Cadastrados</h3>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '15px' }}>
        {carros.length === 0 ? (
          <p>Nenhum veículo cadastrado ainda.</p>
        ) : (
          carros.map((carro) => (
            <div key={carro.id} className="card">
              <h4 style={{ color: '#FF6600', margin: '0 0 10px 0' }}>{carro.marca} - {carro.modelo}</h4>
              <p><strong>Ano:</strong> {carro.ano}</p>
              <p><strong>Placa:</strong> {carro.placa}</p>
              <p><strong>Matrícula:</strong> {carro.matricula}</p>
            </div>
          ))
        )}
      </div>
    </div>
  );
}