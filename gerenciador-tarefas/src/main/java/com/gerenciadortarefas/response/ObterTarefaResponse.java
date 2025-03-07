package com.gerenciadortarefas.response;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.status.TarefaStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ObterTarefaResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private String responsavel;
    private String criador;
    private LocalTime dataCadastro;
    private LocalTime dataAtualizacao;
    private Integer quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizadas;

}
