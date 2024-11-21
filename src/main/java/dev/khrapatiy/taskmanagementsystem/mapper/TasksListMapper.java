package dev.khrapatiy.taskmanagementsystem.mapper;

import dev.khrapatiy.taskmanagementsystem.dto.response.TasksListResponse;
import dev.khrapatiy.taskmanagementsystem.entity.Task;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TasksListMapper {
    Task toEntity(TasksListResponse tasksListResponse);

    @AfterMapping
    default void linkComments(@MappingTarget Task task) {
        task.getComments().forEach(comment -> comment.setTask(task));
    }

    TasksListResponse toDto(Task task);

    default Page<TasksListResponse> toDto2(Page<Task> tasks) {
        return tasks.map(this::toDto);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TasksListResponse tasksListResponse, @MappingTarget Task task);
}