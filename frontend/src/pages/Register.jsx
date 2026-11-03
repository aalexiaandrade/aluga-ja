import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
export default function Register(){
 const [f,setF]=useState({nome:'',email:'',senha:'',cpf:'',rg:'',endereco:'',profissao:'',tipo:'CLIENTE'});const [erro,setErro]=useState('')
 const {register}=useAuth();const nav=useNavigate()
 function change(e){setF({...f,[e.target.name]:e.target.value})}
 async function submit(e){e.preventDefault();setErro('');try{await register(f);nav('/')}catch(err){setErro(err.response?.data?.detail||'Erro ao criar cadastro.')}}
 return <div className="auth-page"><div className="auth-art"><div className="art-content"><div className="logo-light">AlugaJá</div><h1>Liberdade para ir mais longe.</h1><p>Cadastre-se e acompanhe seus pedidos de aluguel em um só lugar.</p></div></div>
 <div className="auth-form-wrap"><form className="auth-form register-form" onSubmit={submit}><span className="eyebrow">COMECE AGORA</span><h2>Criar sua conta</h2><p className="muted">Preencha os dados para se cadastrar.</p>{erro&&<div className="alert">{erro}</div>}
 <div className="form-grid"><div className="wide"><label>Nome completo</label><input name="nome" required value={f.nome} onChange={change}/></div><div className="wide"><label>E-mail</label><input name="email" type="email" required value={f.email} onChange={change}/></div><div><label>Senha</label><input name="senha" type="password" minLength="6" required value={f.senha} onChange={change}/></div><div><label>CPF</label><input name="cpf" value={f.cpf} onChange={change}/></div><div><label>RG</label><input name="rg" value={f.rg} onChange={change}/></div><div><label>Profissão</label><input name="profissao" value={f.profissao} onChange={change}/></div><div className="wide"><label>Endereço</label><input name="endereco" value={f.endereco} onChange={change}/></div></div>
 <button className="primary full">Criar conta</button><p className="auth-foot">Já tem uma conta? <Link to="/login">Fazer login</Link></p></form></div></div>
}
