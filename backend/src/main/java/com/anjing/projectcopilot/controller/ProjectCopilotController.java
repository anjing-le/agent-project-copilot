package com.anjing.projectcopilot.controller;

import com.anjing.model.constants.ApiConstants;
import com.anjing.model.response.APIResponse;
import com.anjing.projectcopilot.model.request.ActionItemUpdateRequest;
import com.anjing.projectcopilot.model.request.MeetingMinutesCreateRequest;
import com.anjing.projectcopilot.model.request.ProjectSpaceCreateRequest;
import com.anjing.projectcopilot.model.request.RiskUpdateRequest;
import com.anjing.projectcopilot.model.request.TaskBreakdownGenerateRequest;
import com.anjing.projectcopilot.model.request.WeeklyReportGenerateRequest;
import com.anjing.projectcopilot.model.response.ActionItemResponse;
import com.anjing.projectcopilot.model.response.CollaborationHistoryResponse;
import com.anjing.projectcopilot.model.response.MeetingMinutesResponse;
import com.anjing.projectcopilot.model.response.ProjectOverviewResponse;
import com.anjing.projectcopilot.model.response.ProjectSpaceResponse;
import com.anjing.projectcopilot.model.response.RiskItemResponse;
import com.anjing.projectcopilot.model.response.TaskBreakdownResponse;
import com.anjing.projectcopilot.model.response.WeeklyReportResponse;
import com.anjing.projectcopilot.service.ProjectCopilotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目交付协作 Agent 运行接口边界。
 */
@Slf4j
@Validated
@RestController
@RequestMapping(ApiConstants.ProjectCopilot.BASE)
@RequiredArgsConstructor
@Tag(name = "Project Copilot", description = "Project delivery collaboration agent APIs")
public class ProjectCopilotController {

    private final ProjectCopilotService projectCopilotService;

    @GetMapping(ApiConstants.ProjectCopilot.OVERVIEW)
    @Operation(summary = "Get project copilot overview", operationId = "getProjectCopilotOverview")
    public APIResponse<ProjectOverviewResponse> getProjectCopilotOverview() {
        return APIResponse.success(projectCopilotService.getOverview());
    }

    @GetMapping(ApiConstants.ProjectCopilot.SPACES)
    @Operation(summary = "List project spaces", operationId = "listProjectSpaces")
    public APIResponse<List<ProjectSpaceResponse>> listProjectSpaces() {
        return APIResponse.success(projectCopilotService.listSpaces());
    }

    @PostMapping(ApiConstants.ProjectCopilot.SPACES)
    @Operation(summary = "Create project space", operationId = "createProjectSpace")
    public APIResponse<ProjectSpaceResponse> createProjectSpace(@Valid @RequestBody ProjectSpaceCreateRequest request) {
        return APIResponse.success(projectCopilotService.createSpace(request));
    }

    @GetMapping(ApiConstants.ProjectCopilot.SPACE_DETAIL)
    @Operation(summary = "Get project space", operationId = "getProjectSpace")
    public APIResponse<ProjectSpaceResponse> getProjectSpace(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.getSpace(spaceId));
    }

    @GetMapping(ApiConstants.ProjectCopilot.TASK_BREAKDOWN)
    @Operation(summary = "List task breakdown", operationId = "listTaskBreakdown")
    public APIResponse<List<TaskBreakdownResponse>> listTaskBreakdown(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.listTaskBreakdown(spaceId));
    }

    @PostMapping(ApiConstants.ProjectCopilot.TASK_BREAKDOWN_GENERATE)
    @Operation(summary = "Generate task breakdown", operationId = "generateTaskBreakdown")
    public APIResponse<List<TaskBreakdownResponse>> generateTaskBreakdown(
            @PathVariable String spaceId,
            @Valid @RequestBody TaskBreakdownGenerateRequest request) {
        return APIResponse.success(projectCopilotService.generateTaskBreakdown(spaceId, request));
    }

    @GetMapping(ApiConstants.ProjectCopilot.MEETINGS)
    @Operation(summary = "List meeting minutes", operationId = "listMeetingMinutes")
    public APIResponse<List<MeetingMinutesResponse>> listMeetingMinutes(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.listMeetingMinutes(spaceId));
    }

    @PostMapping(ApiConstants.ProjectCopilot.MEETINGS)
    @Operation(summary = "Create meeting minutes", operationId = "createMeetingMinutes")
    public APIResponse<MeetingMinutesResponse> createMeetingMinutes(
            @PathVariable String spaceId,
            @Valid @RequestBody MeetingMinutesCreateRequest request) {
        return APIResponse.success(projectCopilotService.createMeetingMinutes(spaceId, request));
    }

    @GetMapping(ApiConstants.ProjectCopilot.RISKS)
    @Operation(summary = "List risk radar items", operationId = "listRiskRadar")
    public APIResponse<List<RiskItemResponse>> listRiskRadar(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.listRisks(spaceId));
    }

    @PatchMapping(ApiConstants.ProjectCopilot.RISK_DETAIL)
    @Operation(summary = "Update risk radar item", operationId = "updateRiskRadarItem")
    public APIResponse<RiskItemResponse> updateRiskRadarItem(
            @PathVariable String spaceId,
            @PathVariable String riskId,
            @Valid @RequestBody RiskUpdateRequest request) {
        return APIResponse.success(projectCopilotService.updateRisk(spaceId, riskId, request));
    }

    @GetMapping(ApiConstants.ProjectCopilot.WEEKLY_REPORTS)
    @Operation(summary = "List weekly reports", operationId = "listWeeklyReports")
    public APIResponse<List<WeeklyReportResponse>> listWeeklyReports(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.listWeeklyReports(spaceId));
    }

    @PostMapping(ApiConstants.ProjectCopilot.WEEKLY_REPORT_GENERATE)
    @Operation(summary = "Generate weekly report", operationId = "generateWeeklyReport")
    public APIResponse<WeeklyReportResponse> generateWeeklyReport(
            @PathVariable String spaceId,
            @Valid @RequestBody WeeklyReportGenerateRequest request) {
        return APIResponse.success(projectCopilotService.generateWeeklyReport(spaceId, request));
    }

    @GetMapping(ApiConstants.ProjectCopilot.ACTION_ITEMS)
    @Operation(summary = "List action items", operationId = "listActionItems")
    public APIResponse<List<ActionItemResponse>> listActionItems(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.listActionItems(spaceId));
    }

    @PatchMapping(ApiConstants.ProjectCopilot.ACTION_ITEM_DETAIL)
    @Operation(summary = "Update action item", operationId = "updateActionItem")
    public APIResponse<ActionItemResponse> updateActionItem(
            @PathVariable String spaceId,
            @PathVariable String actionItemId,
            @Valid @RequestBody ActionItemUpdateRequest request) {
        return APIResponse.success(projectCopilotService.updateActionItem(spaceId, actionItemId, request));
    }

    @GetMapping(ApiConstants.ProjectCopilot.COLLABORATION_HISTORY)
    @Operation(summary = "List collaboration history", operationId = "listCollaborationHistory")
    public APIResponse<List<CollaborationHistoryResponse>> listCollaborationHistory(@PathVariable String spaceId) {
        return APIResponse.success(projectCopilotService.listCollaborationHistory(spaceId));
    }
}
