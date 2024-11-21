package dev.khrapatiy.taskmanagementsystem.repository;

import dev.khrapatiy.taskmanagementsystem.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCreatorId(Long userId, Pageable pageable);
    Page<Task> findByExecutorId(Long userId, Pageable pageable);
}