package ru.otus.task.tracker.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.task.tracker.dtos.UserDto;
import ru.otus.task.tracker.dtos.requests.CreateUserDtoRq;
import ru.otus.task.tracker.dtos.requests.DeleteUserDtoRq;
import ru.otus.task.tracker.entities.User;
import ru.otus.task.tracker.exceptions_handling.ErrorDto;
import ru.otus.task.tracker.exceptions_handling.ResourceNotFoundException;
import ru.otus.task.tracker.services.UsersService;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Пользователи", description = "Методы работы с пользователями")
public class UsersController {
    private final UsersService usersService;

    private static final Function<User, UserDto> ENTITY_TO_DTO = a -> new UserDto(a.getId(), a.getName(), a.getLogin(), a.getRole(), a.getBlockFlag());

    @Operation(
            summary = "Запрос конкретного пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(
                            description = "Пользователь не найден", responseCode = "404",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    )
            }
    )
    @GetMapping("/{login}")
    public UserDto getAccountByLogin(
            @Parameter(description = "Логин пользователя", required = true, schema = @Schema(type = "string", maxLength = 50, example = "nickname"))
            @PathVariable String login
    ) {
        return ENTITY_TO_DTO.apply(usersService.getUserByLogin(login).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден")));
    }

    @PostMapping
    @Operation(summary = "Запрос на создание/активацию пользователя")
    public void createUser(
            @Parameter(description = "Логин администратора", required = true, schema = @Schema(type = "string", maxLength = 50, example = "admin"))
            @RequestHeader(name = "admin-login") String adminLogin,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для создания/активации пользователя", required = true)
            @Parameter(description = "Данные для создания/активации пользователя", required = true)
            @RequestBody CreateUserDtoRq createUserDtoRq
    ) {
        usersService.createUser(adminLogin, createUserDtoRq);
    }

    @DeleteMapping
    @Operation(summary = "Запрос на удаление пользователя")
    public void deleteUser(
            @Parameter(description = "Логин администратора", required = true, schema = @Schema(type = "string", maxLength = 50, example = "admin"))
            @RequestHeader(name = "admin-login") String adminLogin,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные для удаления пользователя", required = true)
            @Parameter(description = "Данные для удаления пользователя", required = true)
            @RequestBody DeleteUserDtoRq deleteUserDtoRq
    ) {
        usersService.deleteUser(adminLogin, deleteUserDtoRq);
    }

}
