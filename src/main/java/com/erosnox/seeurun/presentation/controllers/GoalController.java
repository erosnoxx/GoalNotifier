package com.erosnox.seeurun.presentation.controllers;

import com.erosnox.seeurun.application.contracts.usecases.goals.*;
import com.erosnox.seeurun.application.enums.GoalStatus;
import com.erosnox.seeurun.application.models.request.goal.GoalRequest;
import com.erosnox.seeurun.application.models.response.goal.GoalResponse;
import com.erosnox.seeurun.infrastructure.config.security.SecurityUtils;
import com.erosnox.seeurun.presentation.schemas.ApiResponseFactory;
import com.erosnox.seeurun.presentation.schemas.common.ApiSchema;
import com.erosnox.seeurun.presentation.schemas.common.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController @RequestMapping("/users/{userId}/goals")
public class GoalController {
    @Autowired
    private CreateGoalUsecase createGoalUsecase;
    @Autowired
    private GetAllGoalsUsecase getAllGoalsUsecase;
    @Autowired
    private ToggleCompletedUsecase toggleCompletedUsecase;
    @Autowired
    private GetGoalUsecase getGoalUsecase;
    @Autowired
    private GetAllByStatusUsecase getAllByStatusUsecase;

    @PostMapping
    public ResponseEntity<ApiSchema<GoalResponse>> createGoal(
            @PathVariable UUID userId,
            @RequestBody GoalRequest request) {
        var currentUser = SecurityUtils.getCurrentUser();

        var response = createGoalUsecase.execute(request, userId, currentUser);
        var links =  getLinks(response.id(), userId);
        var selfLink = links.get("_self");

        return ApiResponseFactory.created(
                response, links, "Goal created successfully", selfLink.href());
    }

    @GetMapping
    public ResponseEntity<List<GoalResponse>> getAllGoals(@PathVariable UUID userId) {
        var currentUser = SecurityUtils.getCurrentUser();
        var response = getAllGoalsUsecase.execute(userId, currentUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSchema<GoalResponse>> getGoal(
            @PathVariable UUID id, @PathVariable UUID userId) {
        var currentUser = SecurityUtils.getCurrentUser();
        var response =  getGoalUsecase.execute(id, userId, currentUser);
        var links =  getLinks(response.id(), userId);
        return ApiResponseFactory.ok(response, links, "Goal retrieved successfully");
    }

    @GetMapping("/status")
    public ResponseEntity<List<GoalResponse>> getAllGoalsStatus(
            @RequestParam GoalStatus status,
            @PathVariable UUID userId) {
        var currentUser = SecurityUtils.getCurrentUser();
        var response = getAllByStatusUsecase.execute(userId, status, currentUser);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiSchema<GoalResponse>> toggleCompletedGoal(
            @PathVariable UUID userId,
            @PathVariable UUID id) {
        var currentUser = SecurityUtils.getCurrentUser();
        var response = toggleCompletedUsecase.execute(userId, id, currentUser);
        var links =  getLinks(response.id(), userId);

        return ApiResponseFactory.ok(response, links, "Goal toggle completed successfully");
    }

    private Map<String, Link> getLinks(UUID id, UUID userId) {
        var selfLink = new Link("/users/" + userId + "/goals/" + id, "GET");
        var deleteLink = new Link("/users/" + userId + "/goals/" + id, "DELETE");
        var updateLink = new Link("/users/" + userId + "/goals/" + id, "PATCH");

        return Map.of(
                "_self", selfLink,
                "delete", deleteLink,
                "update", updateLink
        );
    }
}
