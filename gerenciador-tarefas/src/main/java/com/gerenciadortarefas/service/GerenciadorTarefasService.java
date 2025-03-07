package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Tarefa;
import com.gerenciadortarefas.repository.GerenciadorTarefasRepository;
import com.gerenciadortarefas.request.AtualizarTarefaRequest;
import com.gerenciadortarefas.request.CadastrarTarefaRequest;
import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GerenciadorTarefasService {

    @Autowired
    private GerenciadorTarefasRepository gerenciadorTarefasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CadastrarTarefaRequest request){

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
                .status(TarefaStatusEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuario(request.getCriadorId()).get())
                .build();

        return this.gerenciadorTarefasRepository.save(tarefa);

    }

    public Page<Tarefa> obtemTarefasPorTitulo(String titulo, Pageable pageable){

       return this.gerenciadorTarefasRepository.findByTituloContaining(titulo, pageable);

    }

    public Page<Tarefa> obtemTodasTarefas(Pageable pageable){

        return this.gerenciadorTarefasRepository.findAll(pageable);

    }

    public Tarefa atualizarTarefas(Long id,AtualizarTarefaRequest request){

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas());
        tarefa.setStatus(request.getStatus());
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setResponsavel(usuarioService.obterUsuario(request.getResponsavelId()).get());
        tarefa.setQuantidadeHorasRealizadas(tarefa.getQuantidadeHorasRealizadas());


        this.gerenciadorTarefasRepository.save(tarefa);

        return tarefa;

    }

    public void excluirTarefas(Long tarefaid){
        this.gerenciadorTarefasRepository.deleteById(tarefaid);

    }

}
