import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Plus, CarFront, Clock3, CheckCircle2, XCircle, ClipboardList } from 'lucide-react'
import api from '../services/api'
import { useAuth } from '../context/AuthContext'
const money = v => Number(v||0).toLocaleString('pt-BR',{style:'currency',currency:'BRL'})
const statusClass = s => ({PENDENTE:'pending',EM_ANALISE:'review',APROVADO:'approved',REPROVADO:'rejected',CANCELADO:'cancelled'}[s]||'pending')
export default function Dashboard(){
 const {user}=useAuth();const [items,setItems]=useState([]);const [erro,setErro]=useState('');const [loading,setLoading]=useState(true)
 async function load(){try{const {data}=await api.get(user.tipo==='AGENTE'?'/pedidos':'/pedidos/meus');setItems(data)}catch(e){setErro('Não foi possível carregar os pedidos.')}finally{setLoading(false)}}
 useEffect(()=>{load()},[])
 const counts={total:items.length,pendentes:items.filter(x=>x.status==='PENDENTE'||x.status==='EM_ANALISE').length,aprovados:items.filter(x=>x.status==='APROVADO').length}
 return <><div className="page-heading"><div><span className="eyebrow">VISÃO GERAL</span><h1>{user.tipo==='AGENTE'?'Painel de análise':'Olá, '+user.nome.split(' ')[0]+' 👋'}</h1><p className="muted">{user.tipo==='AGENTE'?'Acompanhe e avalie as solicitações de aluguel.':'Acompanhe seus aluguéis e encontre seu próximo carro.'}</p></div>{user.tipo==='CLIENTE'&&<Link className="primary button-link" to="/novo-pedido"><Plus size={18}/> Novo pedido</Link>}</div>
 <div className="stats"><div className="stat-card"><div className="stat-icon purple"><ClipboardList/></div><span>Total de pedidos</span><strong>{counts.total}</strong></div><div className="stat-card"><div className="stat-icon orange"><Clock3/></div><span>Em análise</span><strong>{counts.pendentes}</strong></div><div className="stat-card"><div className="stat-icon green"><CheckCircle2/></div><span>Aprovados</span><strong>{counts.aprovados}</strong></div></div>
 <section className="panel"><div className="panel-heading"><div><h2>{user.tipo==='AGENTE'?'Solicitações recebidas':'Seus pedidos'}</h2><p className="muted">Acompanhe os detalhes e o andamento das solicitações.</p></div></div>
 {erro&&<div className="alert">{erro}</div>}{loading?<p className="empty">Carregando...</p>:items.length===0?<div className="empty"><CarFront size={38}/><b>Nenhum pedido por enquanto</b><span>Quando houver uma solicitação, ela aparecerá aqui.</span></div>:
 <div className="table-wrap"><table><thead><tr><th>Pedido</th>{user.tipo==='AGENTE'&&<th>Cliente</th>}<th>Veículo</th><th>Período</th><th>Valor</th><th>Status</th><th></th></tr></thead><tbody>{items.map(p=><tr key={p.id}><td className="id-cell">#{String(p.id).padStart(4,'0')}</td>{user.tipo==='AGENTE'&&<td>{p.cliente}</td>}<td><b>{p.veiculo}</b><small>{p.placa}</small></td><td>{new Date(p.dataInicio+'T00:00:00').toLocaleDateString('pt-BR')} – {new Date(p.dataFim+'T00:00:00').toLocaleDateString('pt-BR')}</td><td>{money(p.valorTotal)}</td><td><span className={'status '+statusClass(p.status)}>{p.status.replace('_',' ')}</span></td><td>{user.tipo==='AGENTE'?<Link className="text-link" to={`/pedido/${p.id}`}>Analisar</Link>:<Link className="text-link" to={`/pedido/${p.id}`}>Detalhes</Link>}</td></tr>)}</tbody></table></div>}</section></>
}
