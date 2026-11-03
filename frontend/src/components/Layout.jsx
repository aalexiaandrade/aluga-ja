import { NavLink, Outlet, useNavigate } from 'react-router-dom'
import { CarFront, ClipboardList, Users, LogOut, PlusCircle } from 'lucide-react'
import { useAuth } from '../context/AuthContext'
export default function Layout() {
  const { user, logout } = useAuth()
  const navigate = useNavigate()
  function sair() { logout(); navigate('/login') }
  return <div className="app-shell">
    <aside className="sidebar">
      <div className="brand"><span className="brand-icon"><CarFront /></span><div><b>AlugaJá</b><small>GESTÃO DE ALUGUÉIS</small></div></div>
      <div className="nav-label">MENU PRINCIPAL</div>
      {user?.tipo === 'CLIENTE' ? <>
        <NavLink to="/"><ClipboardList size={18}/> Meus pedidos</NavLink>
        <NavLink to="/novo-pedido"><PlusCircle size={18}/> Novo aluguel</NavLink>
        <NavLink to="/perfil"><Users size={18}/> Meu perfil</NavLink>
      </> : <>
        <NavLink to="/"><ClipboardList size={18}/> Análise de pedidos</NavLink>
        <NavLink to="/clientes"><Users size={18}/> Clientes</NavLink>
      </>}
      <div className="sidebar-bottom"><div className="user-chip"><div className="avatar">{user?.nome?.[0]}</div><div><b>{user?.nome}</b><small>{user?.tipo === 'AGENTE' ? 'Agente' : 'Cliente'}</small></div></div>
        <button className="logout" onClick={sair}><LogOut size={17}/> Sair</button></div>
    </aside>
    <main className="main"><header className="topbar"><span>Portal de gestão</span><span className="online-dot">● Sistema online</span></header><div className="content"><Outlet /></div></main>
  </div>
}
