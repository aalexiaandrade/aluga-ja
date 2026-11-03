package br.edu.pucminas.aluguel.service;

import br.edu.pucminas.aluguel.dto.AuthDtos.*;
import br.edu.pucminas.aluguel.model.*;
import br.edu.pucminas.aluguel.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ClienteService {
    private final UsuarioRepository usuarios; private final ClienteRepository clientes;
    private final RendimentoRepository rendimentos;
    public ClienteService(UsuarioRepository u, ClienteRepository c, RendimentoRepository r) {
        usuarios=u; clientes=c; rendimentos=r;
    }
    private Cliente obter(String email) {
        Usuario u = usuarios.findByEmail(email).orElseThrow();
        return clientes.findByUsuarioId(u.getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil de cliente não encontrado."));
    }
    public ClienteResponse meuPerfil(String email) { return converter(obter(email)); }
    @Transactional public ClienteResponse atualizar(String email, ClienteUpdateRequest req) {
        Cliente c=obter(email);
        if (req.nome()!=null) c.getUsuario().setNome(req.nome());
        if (req.rg()!=null) c.setRg(req.rg());
        if (req.cpf()!=null) c.setCpf(req.cpf());
        if (req.endereco()!=null) c.setEndereco(req.endereco());
        if (req.profissao()!=null) c.setProfissao(req.profissao());
        return converter(c);
    }
    public List<ClienteResponse> listar() { return clientes.findAll().stream().map(this::converter).toList(); }
    @Transactional public List<RendimentoRequest> adicionarRendimento(String email, RendimentoRequest req) {
        Cliente c=obter(email);
        if (rendimentos.countByClienteId(c.getId()) >= 3)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O limite é de três rendimentos.");
        Rendimento r=new Rendimento(); r.setCliente(c); r.setEmpregador(req.empregador()); r.setValor(req.valor());
        rendimentos.save(r);
        return rendimentos.findByClienteId(c.getId()).stream()
            .map(x -> new RendimentoRequest(x.getEmpregador(), x.getValor())).toList();
    }
    public List<RendimentoRequest> listarRendimentos(String email) {
        Cliente c=obter(email);
        return rendimentos.findByClienteId(c.getId()).stream()
            .map(x -> new RendimentoRequest(x.getEmpregador(), x.getValor())).toList();
    }
    private ClienteResponse converter(Cliente c) {
        Usuario u=c.getUsuario();
        return new ClienteResponse(c.getId(),u.getNome(),u.getEmail(),c.getRg(),c.getCpf(),c.getEndereco(),c.getProfissao());
    }
}
