package com.gerenciadortarefas.repository;

import com.gerenciadortarefas.entity.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GerenciadorTarefasRepository extends JpaRepository<Tarefa, Long> {

    Page<Tarefa> findByTituloContaining(String titulo, Pageable pageable);

    Page<Tarefa> findAll(Pageable pageable);
}
