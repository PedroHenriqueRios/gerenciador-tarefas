package com.gerenciadortarefas.request;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.status.TarefaStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AtualizarTarefaRequest {


    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private Long responsavelId;
    private Integer quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizadas;


}
