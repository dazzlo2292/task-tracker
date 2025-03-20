package ru.otus.task.tracker.configs;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public GroupedOpenApi tasksApiV1() {
        final String[] packagesToScan = {"ru.otus.task.tracker.controllers"};
        return GroupedOpenApi
                .builder()
                .group("1. task-tracker-v1")
                .packagesToScan(packagesToScan)
                .pathsToMatch("/api/v1/**")
                .addOpenApiCustomizer(
                        openApi -> openApi.info(
                                new Info()
                                        .title("Сервис отслеживания задач")
                                        .description("OTUS Java Pro Project — Task Tracker")
                                        .version("1.0.0")
                        )
                )
                .build();
    }
}
