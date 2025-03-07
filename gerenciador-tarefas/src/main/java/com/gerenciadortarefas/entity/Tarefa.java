package com.gerenciadortarefas.entity;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "tarefas")
@Data
@Getter
@Setter
@Builder
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo, String descricao, TarefaStatusEnum status, Usuario responsavel, Usuario criador, LocalTime dataCadastro, LocalTime dataAtualizacao, Integer quantidadeHorasEstimadas, Integer quantidadeHorasRealizadas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.responsavel = responsavel;
        this.criador = criador;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.quantidadeHorasEstimadas = quantidadeHorasEstimadas;
        this.quantidadeHorasRealizadas = quantidadeHorasRealizadas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TarefaStatusEnum status;

    @Column
    private Usuario responsavel;

    @Column(nullable = false)
    private Usuario criador;

    @Column
    @CreationTimestamp
    private LocalTime dataCadastro;

    @Column
    @UpdateTimestamp
    private LocalTime dataAtualizacao;

    @Column(nullable = false)
    private Integer quantidadeHorasEstimadas;

    @Column
    private Integer quantidadeHorasRealizadas;


}
