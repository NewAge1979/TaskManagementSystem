package dev.khrapatiy.taskmanagementsystem.dto.response;

import lombok.Builder;

@Builder
public record ApiErrorDetails(String pointer, String reason) {
}