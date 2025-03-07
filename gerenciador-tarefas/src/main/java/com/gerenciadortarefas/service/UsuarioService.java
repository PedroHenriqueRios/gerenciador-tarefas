package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.repository.IRoleRepository;
import com.gerenciadortarefas.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){

        usuario.setRoles(usuario.getRoles()
                .stream()
                .map(role -> iRoleRepository.findByNome(role.getNome()))
                .toList());

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iUsuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario){

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iUsuarioRepository.save(usuario);
    }

    public Optional<Usuario> obterUsuario(Long usuarioid){
        return this.iUsuarioRepository.findById(usuarioid);
    }

    public void excluirUsuario(Usuario usuario){
        this.iUsuarioRepository.deleteById(usuario.getId());
    }

    public List<Usuario> obtemUsuarios(){
        return this.iUsuarioRepository.findAll();
    }

}
