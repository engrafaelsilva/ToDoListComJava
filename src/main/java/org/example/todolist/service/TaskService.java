package org.example.todolist.service;

import org.example.todolist.model.Task;
import lombok.RequiredArgsConstructor;
import org.example.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public List<Task> listarTodas() {
        return repository.findAll();
    }

    public Task salvar(Task task) {
        task.setCriadaEm(LocalDateTime.now());
        return repository.save(task);
    }

    public Task atualizar(Long id, Task taskAtualizada) {
        return repository.findById(id)
                .map(task -> {
                    task.setTitulo(taskAtualizada.getTitulo());
                    task.setDescricao(taskAtualizada.getDescricao());
                    task.setConcluida(taskAtualizada.isConcluida());
                    return repository.save(task);
                }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
