package com.gerenciadortarefas.request;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Getter
@Setter
public class TarefaRequest {

    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private Long responsavelId;
    private Long criadorId;
    private Integer quantidadeHorasEstimadas;
}
