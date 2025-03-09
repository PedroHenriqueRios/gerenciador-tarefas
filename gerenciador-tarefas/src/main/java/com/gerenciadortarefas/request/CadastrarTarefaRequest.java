package com.gerenciadortarefas.request;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTarefaRequest {

    private String titulo;
    private String descricao;
    private Long criadorId;
    private Integer quantidadeHorasEstimadas;
}
