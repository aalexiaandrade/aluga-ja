import React, { useState } from 'react';
import Login from './telas/Login';

export default function App() {
  const [usuarioLogado, setUsuarioLogado] = useState(null);

  if (!usuarioLogado) {
    return <Login onLoginSuccess={(user) => setUsuarioLogado(user)} />;
  }

  return (
    <div style={{ padding: '20px' }}>
      <header style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', borderBottom: '2px solid #FF6600', paddingBottom: '10px' }}>
        <h1 style={{ color: '#FF6600', margin: 0 }}>Aluga Já</h1>
        <p>Bem-vindo, <strong>{usuarioLogado.nome || usuarioLogado.username}</strong>!</p>
      </header>
      <main>
        <div style={{ marginTop: '20px' }}>
          <p>Painel principal carregado com sucesso! Aqui você poderá gerenciar clientes, carros e pedidos.</p>
        </div>
      </main>
    </div>
  );
}