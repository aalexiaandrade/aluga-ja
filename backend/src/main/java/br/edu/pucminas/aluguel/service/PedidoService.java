package br.edu.pucminas.aluguel.service;

import br.edu.pucminas.aluguel.dto.PedidoDtos.*;
import br.edu.pucminas.aluguel.model.*;
import br.edu.pucminas.aluguel.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoAluguelRepository pedidos; private final ClienteRepository clientes;
    private final UsuarioRepository usuarios; private final VeiculoRepository veiculos;
    public PedidoService(PedidoAluguelRepository p, ClienteRepository c, UsuarioRepository u, VeiculoRepository v) {
        pedidos=p; clientes=c; usuarios=u; veiculos=v;
    }
    @Transactional public PedidoResponse criar(String email, PedidoRequest req) {
        validarDatas(req.dataInicio(), req.dataFim());
        Cliente c=cliente(email);
        Veiculo v=veiculos.findById(req.veiculoId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,"Veículo não encontrado."));
        PedidoAluguel p=new PedidoAluguel(); p.setCliente(c); p.setVeiculo(v);
        aplicar(p,req); return converter(pedidos.save(p));
    }
    public List<PedidoResponse> meus(String email) {
        Cliente c=cliente(email);
        return pedidos.findByClienteIdOrderByCriadoEmDesc(c.getId()).stream().map(this::converter).toList();
    }
    public List<PedidoResponse> todos() {
        return pedidos.findAllByOrderByCriadoEmDesc().stream().map(this::converter).toList();
    }
    public PedidoResponse buscar(Long id, String email) {
        PedidoAluguel p=obter(id);
        Usuario u=usuarios.findByEmail(email).orElseThrow();
        if (u.getTipo()==TipoUsuario.CLIENTE && !p.getCliente().getUsuario().getId().equals(u.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Você não pode consultar este pedido.");
        return converter(p);
    }
    @Transactional public PedidoResponse atualizar(Long id,String email,PedidoRequest req) {
        PedidoAluguel p=obter(id); verificarDono(p,email); verificarPendente(p);
        validarDatas(req.dataInicio(),req.dataFim());
        Veiculo v=veiculos.findById(req.veiculoId()).orElseThrow();
        p.setVeiculo(v); aplicar(p,req); return converter(p);
    }
    @Transactional public PedidoResponse cancelar(Long id,String email) {
        PedidoAluguel p=obter(id); verificarDono(p,email); verificarPendente(p);
        p.setStatus(StatusPedido.CANCELADO); return converter(p);
    }
    @Transactional public PedidoResponse avaliar(Long id,String email,AvaliacaoRequest req) {
        PedidoAluguel p=obter(id);
        if (p.getStatus()!=StatusPedido.PENDENTE && p.getStatus()!=StatusPedido.EM_ANALISE)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Este pedido já foi finalizado.");
        Usuario agente=usuarios.findByEmail(email).orElseThrow();
        if (agente.getTipo()!=TipoUsuario.AGENTE) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        p.setAgenteAvaliador(agente); p.setParecerAgente(req.parecer());
        p.setStatus(req.aprovado()?StatusPedido.APROVADO:StatusPedido.REPROVADO);
        return converter(p);
    }
    private Cliente cliente(String email) {
        Usuario u=usuarios.findByEmail(email).orElseThrow();
        return clientes.findByUsuarioId(u.getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.FORBIDDEN,"Apenas clientes podem realizar esta ação."));
    }
    private PedidoAluguel obter(Long id) {
        return pedidos.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado."));
    }
    private void verificarDono(PedidoAluguel p,String email) {
        if (!p.getCliente().getUsuario().getEmail().equals(email))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Pedido não pertence ao usuário.");
    }
    private void verificarPendente(PedidoAluguel p) {
        if (p.getStatus()!=StatusPedido.PENDENTE)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Somente pedidos pendentes podem ser alterados ou cancelados.");
    }
    private void validarDatas(LocalDate inicio,LocalDate fim) {
        if (!fim.isAfter(inicio) || inicio.isBefore(LocalDate.now()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Informe datas futuras e uma data final posterior à inicial.");
    }
    private void aplicar(PedidoAluguel p,PedidoRequest r) {
        p.setDataInicio(r.dataInicio()); p.setDataFim(r.dataFim()); p.setValorTotal(r.valorTotal());
        p.setPossuiCredito(Boolean.TRUE.equals(r.possuiCredito()));
        p.setBancoAgente(r.bancoAgente()); p.setValorCredito(r.valorCredito());
        p.setStatus(StatusPedido.PENDENTE);
    }
    private PedidoResponse converter(PedidoAluguel p) {
        return new PedidoResponse(p.getId(),p.getCliente().getUsuario().getNome(),
            p.getVeiculo().getMarca()+" "+p.getVeiculo().getModelo(),p.getVeiculo().getPlaca(),
            p.getDataInicio(),p.getDataFim(),p.getValorTotal(),p.getPossuiCredito(),p.getBancoAgente(),
            p.getValorCredito(),p.getStatus().name(),p.getParecerAgente(),
            p.getAgenteAvaliador()==null?null:p.getAgenteAvaliador().getNome(),p.getCriadoEm().toString());
    }
}
