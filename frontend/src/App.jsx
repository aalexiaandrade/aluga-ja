import React, { useState } from 'react';
import Login from './telas/Login';
import Automoveis from './telas/Automoveis';

export default function App() {
  const [usuarioLogado, setUsuarioLogado] = useState(null);

  // Se não estiver logado, obriga a exibir a tela de Login / Cadastro
  if (!usuarioLogado) {
    return <Login onLoginSuccess={(user) => setUsuarioLogado(user)} />;
  }

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <header style={{ 
        display: 'flex', 
        justifyContent: 'space-between', 
        alignItems: 'center', 
        borderBottom: '3px solid #FF6600', 
        paddingBottom: '10px',
        marginBottom: '20px'
      }}>
        <h1 style={{ color: '#FF6600', margin: 0 }}>Aluga Já</h1>
        <div>
          <span style={{ marginRight: '15px' }}>
            Olá, <strong>{usuarioLogado.nome || usuarioLogado.username}</strong>!
          </span>
          <button 
            onClick={() => setUsuarioLogado(null)} 
            style={{ padding: '6px 12px', background: '#dc3545', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
          >
            Sair
          </button>
        </div>
      </header>

      <main>
        <Automoveis />
      </main>
    </div>
  );
}