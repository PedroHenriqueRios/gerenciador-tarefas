package com.gerenciadortarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CadastraCadastrarTarefaResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private String criador;
}
