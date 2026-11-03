import { Routes,Route,Navigate } from 'react-router-dom'
import { AuthProvider,useAuth } from './context/AuthContext'
import ProtectedRoute from './components/ProtectedRoute'
import Layout from './components/Layout'
import Login from './pages/Login'
import Register from './pages/Register'
import Dashboard from './pages/Dashboard'
import NewOrder from './pages/NewOrder'
import OrderDetails from './pages/OrderDetails'
import Profile from './pages/Profile'
import Clients from './pages/Clients'
function PrivateApp(){
 const {user}=useAuth()
 return <Routes>
  <Route path="/login" element={user?<Navigate to="/" replace/>:<Login/>}/>
  <Route path="/cadastro" element={user?<Navigate to="/" replace/>:<Register/>}/>
  <Route element={<ProtectedRoute><Layout/></ProtectedRoute>}>
   <Route path="/" element={<Dashboard/>}/>
   <Route path="/pedido/:id" element={<OrderDetails/>}/>
   <Route path="/novo-pedido" element={<ProtectedRoute role="CLIENTE"><NewOrder/></ProtectedRoute>}/>
   <Route path="/perfil" element={<ProtectedRoute role="CLIENTE"><Profile/></ProtectedRoute>}/>
   <Route path="/clientes" element={<ProtectedRoute role="AGENTE"><Clients/></ProtectedRoute>}/>
  </Route>
  <Route path="*" element={<Navigate to={user?'/':'/login'} replace/>}/>
 </Routes>
}
export default function App(){return <AuthProvider><PrivateApp/></AuthProvider>}
