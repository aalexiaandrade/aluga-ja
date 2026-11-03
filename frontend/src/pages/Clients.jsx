import { useEffect,useState } from 'react'
import api from '../services/api'
export default function Clients(){
 const [items,setItems]=useState([]);const [erro,setErro]=useState('')
 useEffect(()=>{api.get('/clientes').then(r=>setItems(r.data)).catch(()=>setErro('Não foi possível carregar os clientes.'))},[])
 return <><div className="page-heading"><div><span className="eyebrow">GESTÃO</span><h1>Clientes cadastrados</h1><p className="muted">Consulte os dados dos contratantes.</p></div></div><section className="panel"><h2>Base de clientes</h2>{erro&&<div className="alert">{erro}</div>}<div className="table-wrap"><table><thead><tr><th>Nome</th><th>E-mail</th><th>CPF</th><th>Profissão</th><th>Endereço</th></tr></thead><tbody>{items.map(c=><tr key={c.id}><td><b>{c.nome}</b></td><td>{c.email}</td><td>{c.cpf||'—'}</td><td>{c.profissao||'—'}</td><td>{c.endereco||'—'}</td></tr>)}</tbody></table></div></section></>
}
