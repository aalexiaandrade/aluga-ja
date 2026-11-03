import React, { useState } from 'react';

export default function Login({ onLoginSuccess }) {
  const [username, setUsername] = useState('');
  const [senha, setSenha] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, senha })
      });

      if (res.ok) {
        const user = await res.json();
        onLoginSuccess(user);
      } else {
        alert('Login falhou! Verifique suas credenciais.');
      }
    } catch (error) {
      console.error('Erro ao conectar com o back-end:', error);
      alert('Não foi possível conectar ao servidor Spring Boot.');
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', marginTop: '100px' }}>
      <form className="card" onSubmit={handleLogin} style={{ width: '300px' }}>
        <h2 style={{ color: '#FF6600', textAlign: 'center' }}>Aluga Já</h2>
        <div style={{ marginBottom: '15px' }}>
          <label>Usuário:</label>
          <input 
            type="text" 
            style={{ width: '100%', padding: '8px', marginTop: '5px', boxSizing: 'border-box' }}
            value={username} 
            onChange={(e) => setUsername(e.target.value)} 
            required 
          />
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label>Senha:</label>
          <input 
            type="password" 
            style={{ width: '100%', padding: '8px', marginTop: '5px', boxSizing: 'border-box' }}
            value={senha} 
            onChange={(e) => setSenha(e.target.value)} 
            required 
          />
        </div>
        <button type="submit" className="btn-primary" style={{ width: '100%' }}>Entrar</button>
      </form>
    </div>
  );
}