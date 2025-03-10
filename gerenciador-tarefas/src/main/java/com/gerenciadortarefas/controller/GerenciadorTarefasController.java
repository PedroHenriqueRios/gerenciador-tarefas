package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.entity.Tarefa;
import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.request.AtualizarTarefaRequest;
import com.gerenciadortarefas.request.CadastrarTarefaRequest;
import com.gerenciadortarefas.response.AtualizarTarefaResponse;
import com.gerenciadortarefas.response.CadastrarTarefaResponse;
import com.gerenciadortarefas.response.ObterTarefaResponse;
import com.gerenciadortarefas.response.ObterTarefasPaginadoResponse;
import com.gerenciadortarefas.service.GerenciadorTarefasService;
import com.gerenciadortarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerenciador-tarefas")
public class GerenciadorTarefasController {

    @Autowired
    private GerenciadorTarefasService gerenciadorTarefasService;

    @PostMapping
    public ResponseEntity<CadastrarTarefaResponse> salvarTarefa(@RequestBody CadastrarTarefaRequest request){
        Tarefa tarefaSalva = gerenciadorTarefasService.salvarTarefa(request);

      CadastrarTarefaResponse response =  CadastrarTarefaResponse.builder()
                .id(tarefaSalva.getId())
                .titulo(tarefaSalva.getTitulo())
                .descricao(tarefaSalva.getDescricao())
                .criador(tarefaSalva.getCriador().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ObterTarefasPaginadoResponse> obterTarefas(
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

   Page<Tarefa> tarefasPaginada = null;

   if(titulo == null){
       tarefasPaginada = this.gerenciadorTarefasService.obtemTodasTarefas(PageRequest.of(page,size));
   }else {
       tarefasPaginada = this.gerenciadorTarefasService.obtemTarefasPorTitulo(titulo, PageRequest.of(page,size));
   }

   List<ObterTarefaResponse> tarefas = tarefasPaginada.getContent()
           .stream().map(tarefa -> {
      return ObterTarefaResponse
               .builder()
              .id(tarefa.getId())
               .titulo(tarefa.getTitulo())
               .descricao(tarefa.getDescricao())
               .responsavel(tarefa.getResponsavel() != null ?tarefa.getResponsavel().getUsername() : "NAO_ATRIBUIDA")
               .criador(tarefa.getCriador().getUsername())
               .status(tarefa.getStatus())
               .quantidadeHorasEstimadas(tarefa.getQuantidadeHorasEstimadas())
               .quantidadeHorasRealizadas(tarefa.getQuantidadeHorasRealizadas())
               .dataCadastro(tarefa.getDataCadastro())
               .dataAtualizacao(tarefa.getDataAtualizacao())
               .build();
   }).toList();

        ObterTarefasPaginadoResponse response = ObterTarefasPaginadoResponse.builder()
           .paginaAtual(tarefasPaginada.getNumber())
           .totalPaginas(tarefasPaginada.getTotalPages())
           .totalItens(tarefasPaginada.getTotalElements())
           .tarefas(tarefas)
           .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public void excluirTarefa(@PathVariable Long id){
gerenciadorTarefasService.excluirTarefas(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AtualizarTarefaResponse> atualizarTarefa(@PathVariable Long id, @RequestBody AtualizarTarefaRequest request){
        Tarefa tarefaAtualizada = gerenciadorTarefasService.atualizarTarefas(id,request);

        AtualizarTarefaResponse response =  AtualizarTarefaResponse.builder()
                .id(tarefaAtualizada.getId())
                .titulo(tarefaAtualizada.getTitulo())
                .descricao(tarefaAtualizada.getDescricao())
                .criador(tarefaAtualizada.getCriador().getUsername())
                .quantidadeHorasEstimadas(tarefaAtualizada.getQuantidadeHorasEstimadas())
                .status(tarefaAtualizada.getStatus().toString())
                .responsavel(tarefaAtualizada.getResponsavel().getUsername())
                .quantidadeHorasRealizadas(tarefaAtualizada.getQuantidadeHorasRealizadas() != null ? tarefaAtualizada.getQuantidadeHorasRealizadas() : null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
