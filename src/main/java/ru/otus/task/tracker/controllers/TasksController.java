package ru.otus.task.tracker.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.task.tracker.dtos.TaskDto;
import ru.otus.task.tracker.dtos.TasksPageDto;
import ru.otus.task.tracker.dtos.requests.CreateTaskDtoRq;
import ru.otus.task.tracker.dtos.requests.ChangeTaskStatusDtoRq;
import ru.otus.task.tracker.entities.Task;
import ru.otus.task.tracker.exceptions_handling.ErrorDto;
import ru.otus.task.tracker.exceptions_handling.ResourceNotFoundException;
import ru.otus.task.tracker.services.TasksService;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@Tag(name = "Задачи", description = "Методы работы с задачами")
public class TasksController {
    private final TasksService tasksService;

    private static final Function<Task, TaskDto> ENTITY_TO_DTO = t -> new TaskDto(t.getId(), t.getTitle(), t.getDescription(), t.getAssignee(), t.getStatus(), t.getPriority(), t.getDueDate());

    @GetMapping("/list")
    @Operation(
            summary = "Запрос списка задач пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TasksPageDto.class))
                    )
            }
    )
    public TasksPageDto getAllTasksByAssignee(
            @Parameter(description = "Идентификатор исполнителя", required = true, schema = @Schema(type = "string", maxLength = 36, example = "1"))
            @RequestParam(value = "assignee", required=true) String assignee,

            @Parameter(description = "Номер страницы", required = false, schema = @Schema(type = "integer", defaultValue = "0", example = "5"))
            @RequestParam(value = "page", required=false, defaultValue = "0") Integer page,

            @Parameter(description = "Количество задач на странице", required = false, schema = @Schema(type = "integer", defaultValue = "20", maximum = "100", example = "10"))
            @RequestParam(value = "size", required=false, defaultValue = "20") Integer size
    ) {
        return new TasksPageDto(
                tasksService
                        .getAllTasksByAssignee(assignee, page, size)
                        .stream()
                        .map(ENTITY_TO_DTO).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Запрос задачи по идентификатору",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
                    ),
                    @ApiResponse(
                            description = "Указанная задача не найдена", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    )
            }
    )
    public TaskDto getTaskById(
            @Parameter(description = "Идентификатор задачи", required = true, schema = @Schema(type = "string", maxLength = 36, example = "bde76ffa-f133-4c23-9bca-03618b2a94b2"))
            @PathVariable String id
    ) {
        return ENTITY_TO_DTO.apply(tasksService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Перевод не найден")));
    }

    @PostMapping
    @Operation(summary = "Запрос на создание задачи")
    public void createTask(
            @Parameter(description = "Логин администратора", required = true, schema = @Schema(type = "string", maxLength = 50, example = "admin"))
            @RequestHeader(name = "admin-login") String adminLogin,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для создания задачи", required = true)
            @Parameter(description = "Данные для создания задачи", required = true)
            @RequestBody CreateTaskDtoRq createTaskDtoRq
    ) {
        tasksService.createTask(adminLogin, createTaskDtoRq);
    }

    @PatchMapping
    @Operation(summary = "Запрос на изменение статуса задачи")
    public void changeTaskStatus(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для изменения статуса задачи", required = true)
            @Parameter(description = "Данные для изменения статуса задачи", required = true)
            @RequestBody ChangeTaskStatusDtoRq changeTaskStatusDtoRq
    ) {
        tasksService.changeTaskStatus(changeTaskStatusDtoRq);
    }
}
