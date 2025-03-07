package com.gerenciadortarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ObterTarefasPaginadoResponse {

    private int paginaAtual;
    private Long totalItens;
    private int totalPaginas;
    private List<ObterTarefaResponse> tarefas;
}
