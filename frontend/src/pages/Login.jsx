import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { CarFront } from 'lucide-react'
import { useAuth } from '../context/AuthContext'
export default function Login() {
  const [email,setEmail]=useState(''); const [senha,setSenha]=useState(''); const [erro,setErro]=useState('')
  const {login}=useAuth(); const nav=useNavigate()
  async function submit(e){e.preventDefault();setErro('');try{await login(email,senha);nav('/')}catch(err){setErro(err.response?.data?.detail || 'Não foi possível entrar. Confira seus dados.')}} 
  return <div className="auth-page"><div className="auth-art"><div className="art-content"><div className="logo-light"><CarFront/> AlugaJá</div><h1>Seu próximo destino começa aqui.</h1><p>Uma experiência simples e inteligente para gerenciar aluguéis de veículos.</p><div className="art-car">🚘</div></div></div>
  <div className="auth-form-wrap"><form className="auth-form" onSubmit={submit}><span className="eyebrow">BEM-VINDO DE VOLTA</span><h2>Acesse sua conta</h2><p className="muted">Entre com seus dados para continuar.</p>
  {erro&&<div className="alert">{erro}</div>}<label>E-mail</label><input type="email" required value={email} onChange={e=>setEmail(e.target.value)} placeholder="seu@email.com"/>
  <label>Senha</label><input type="password" required value={senha} onChange={e=>setSenha(e.target.value)} placeholder="Sua senha"/>
  <button className="primary full">Entrar na plataforma</button><p className="auth-foot">Ainda não tem conta? <Link to="/cadastro">Criar cadastro</Link></p></form></div></div>
}
