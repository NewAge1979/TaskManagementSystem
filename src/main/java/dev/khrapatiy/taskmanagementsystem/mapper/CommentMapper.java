package dev.khrapatiy.taskmanagementsystem.mapper;

import dev.khrapatiy.taskmanagementsystem.dto.request.CreateCommentDto;
import dev.khrapatiy.taskmanagementsystem.dto.response.CommentResponse;
import dev.khrapatiy.taskmanagementsystem.entity.Comment;
import dev.khrapatiy.taskmanagementsystem.utils.mapper.CommentMapperUtil;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {CommentMapperUtil.class}
)
public interface CommentMapper {
    @Mapping(target = "creator", expression = "java(CommentMapperUtil.getUserComment())")
    Comment toEntity(CreateCommentDto createCommentDto);

    CommentResponse toDto(Comment comment);
}