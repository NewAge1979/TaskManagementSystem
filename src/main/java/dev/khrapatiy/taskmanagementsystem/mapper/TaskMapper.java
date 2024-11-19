package dev.khrapatiy.taskmanagementsystem.mapper;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.request.EditTaskDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.TaskResponse;
import dev.khrapatiy.taskmanagementsystem.entity.Task;
import dev.khrapatiy.taskmanagementsystem.utils.mapper.TaskMapperUtil;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TaskMapperUtil.class},
        imports = {TaskMapperUtil.class}
)
public interface TaskMapper {
    @Mapping(target = "status", constant = "NEW")
    @Mapping(target = "priority", constant = "LOW")
    @Mapping(target = "creator", expression = "java(TaskMapperUtil.getUser())")
    @Mapping(target = "executor", expression = "java(TaskMapperUtil.getUser())")
    Task toEntity(CreateTaskDto createTaskDto);

    TaskResponse toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(EditTaskDto editTaskDto, @MappingTarget Task task);
}