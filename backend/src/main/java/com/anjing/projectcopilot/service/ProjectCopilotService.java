package com.anjing.projectcopilot.service;

import com.anjing.model.errorcode.ProjectCopilotErrorCode;
import com.anjing.model.exception.BizException;
import com.anjing.projectcopilot.model.request.ActionItemUpdateRequest;
import com.anjing.projectcopilot.model.request.MeetingMinutesCreateRequest;
import com.anjing.projectcopilot.model.request.ProjectSpaceCreateRequest;
import com.anjing.projectcopilot.model.request.RiskUpdateRequest;
import com.anjing.projectcopilot.model.request.TaskBreakdownGenerateRequest;
import com.anjing.projectcopilot.model.request.WeeklyReportGenerateRequest;
import com.anjing.projectcopilot.model.response.ActionItemResponse;
import com.anjing.projectcopilot.model.response.CollaborationHistoryResponse;
import com.anjing.projectcopilot.model.response.MeetingMinutesResponse;
import com.anjing.projectcopilot.model.response.ProjectMetricResponse;
import com.anjing.projectcopilot.model.response.ProjectOverviewResponse;
import com.anjing.projectcopilot.model.response.ProjectSpaceResponse;
import com.anjing.projectcopilot.model.response.RiskItemResponse;
import com.anjing.projectcopilot.model.response.TaskBreakdownResponse;
import com.anjing.projectcopilot.model.response.WeeklyReportResponse;
import com.anjing.util.DateUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In-memory teaching service for project delivery collaboration agent scenarios.
 */
@Service
public class ProjectCopilotService {

    private static final String DEFAULT_SPACE_ID = "space-crm-delivery";

    private final Map<String, ProjectSpaceResponse> spaces = new LinkedHashMap<>();
    private final Map<String, List<TaskBreakdownResponse>> tasks = new LinkedHashMap<>();
    private final Map<String, List<MeetingMinutesResponse>> meetings = new LinkedHashMap<>();
    private final Map<String, List<RiskItemResponse>> risks = new LinkedHashMap<>();
    private final Map<String, List<WeeklyReportResponse>> weeklyReports = new LinkedHashMap<>();
    private final Map<String, List<ActionItemResponse>> actionItems = new LinkedHashMap<>();
    private final Map<String, List<CollaborationHistoryResponse>> collaborationHistory = new LinkedHashMap<>();

    private final AtomicInteger spaceSequence = new AtomicInteger(2);
    private final AtomicInteger taskSequence = new AtomicInteger(5);
    private final AtomicInteger meetingSequence = new AtomicInteger(2);
    private final AtomicInteger riskSequence = new AtomicInteger(3);
    private final AtomicInteger reportSequence = new AtomicInteger(2);
    private final AtomicInteger actionSequence = new AtomicInteger(4);
    private final AtomicInteger historySequence = new AtomicInteger(6);

    @PostConstruct
    public void seed() {
        ProjectSpaceResponse space = ProjectSpaceResponse.builder()
                .spaceId(DEFAULT_SPACE_ID)
                .name("CRM 交付协作演练")
                .deliveryGoal("在两周内完成客户 360 视图、权限边界和上线验收闭环")
                .stage("Sprint 2 / 集成联调")
                .healthStatus("attention")
                .progressPercent(68)
                .sprintLabel("2026-W26")
                .owner("交付 PM")
                .collaboratorCount(7)
                .activeRiskCount(2)
                .openActionItemCount(3)
                .nextCheckpointAt("2026-06-28T08:00:00Z")
                .updatedAt(DateUtils.nowIso())
                .collaborationBoundaries(List.of(
                        "Agent 给出拆解、风险和周报草稿，由 PM 确认后对外发布",
                        "技术方案归 Tech Lead 决策，Agent 只标注依赖和冲突",
                        "客户承诺事项必须进入行动项看板并绑定负责人"))
                .build();
        spaces.put(space.getSpaceId(), space);

        tasks.put(DEFAULT_SPACE_ID, new ArrayList<>(List.of(
                TaskBreakdownResponse.builder()
                        .taskId("task-001")
                        .spaceId(DEFAULT_SPACE_ID)
                        .title("交付主线拆解")
                        .description("围绕上线目标拆解跨角色工作包")
                        .roleOwner("交付 PM")
                        .status("in_progress")
                        .priority("high")
                        .progressPercent(80)
                        .estimateHours(6)
                        .dependencyTaskIds(Collections.emptyList())
                        .collaborationBoundary("PM 负责确认承诺范围，Agent 负责持续提醒范围漂移")
                        .acceptanceCriteria("所有工作包都有负责人、截止日期和验收标准")
                        .dueDate("2026-06-27")
                        .updatedAt(DateUtils.nowIso())
                        .build(),
                TaskBreakdownResponse.builder()
                        .taskId("task-002")
                        .spaceId(DEFAULT_SPACE_ID)
                        .parentTaskId("task-001")
                        .title("客户 360 API 联调")
                        .description("完成客户详情、标签和跟进记录接口联调")
                        .roleOwner("Backend Lead")
                        .status("blocked")
                        .priority("high")
                        .progressPercent(55)
                        .estimateHours(14)
                        .dependencyTaskIds(List.of("task-003"))
                        .collaborationBoundary("后端确认接口语义，前端只消费 OpenAPI 合约")
                        .acceptanceCriteria("冒烟用例通过，接口错误码和字段说明完整")
                        .dueDate("2026-06-29")
                        .updatedAt(DateUtils.nowIso())
                        .build(),
                TaskBreakdownResponse.builder()
                        .taskId("task-003")
                        .spaceId(DEFAULT_SPACE_ID)
                        .parentTaskId("task-001")
                        .title("权限边界验收用例")
                        .description("覆盖销售、主管、运营三类角色的数据边界")
                        .roleOwner("QA Lead")
                        .status("in_progress")
                        .priority("medium")
                        .progressPercent(70)
                        .estimateHours(10)
                        .dependencyTaskIds(Collections.emptyList())
                        .collaborationBoundary("QA 输出验收口径，Agent 只辅助发现遗漏场景")
                        .acceptanceCriteria("权限矩阵与自动化冒烟清单一致")
                        .dueDate("2026-06-30")
                        .updatedAt(DateUtils.nowIso())
                        .build(),
                TaskBreakdownResponse.builder()
                        .taskId("task-004")
                        .spaceId(DEFAULT_SPACE_ID)
                        .parentTaskId("task-001")
                        .title("前端行动项看板交互")
                        .description("展示负责人、状态、提醒和来源会议")
                        .roleOwner("Frontend Lead")
                        .status("review")
                        .priority("medium")
                        .progressPercent(76)
                        .estimateHours(12)
                        .dependencyTaskIds(List.of("task-002"))
                        .collaborationBoundary("前端只承载协作状态，不替代 PM 的优先级判断")
                        .acceptanceCriteria("移动端和桌面端均可快速筛选未完成行动项")
                        .dueDate("2026-07-01")
                        .updatedAt(DateUtils.nowIso())
                        .build()
        )));

        meetings.put(DEFAULT_SPACE_ID, new ArrayList<>(List.of(
                MeetingMinutesResponse.builder()
                        .meetingId("meeting-001")
                        .spaceId(DEFAULT_SPACE_ID)
                        .title("Sprint 2 联调例会")
                        .meetingType("delivery_sync")
                        .heldAt("2026-06-25T07:00:00Z")
                        .facilitator("交付 PM")
                        .participants(List.of("交付 PM", "Backend Lead", "Frontend Lead", "QA Lead", "客户成功"))
                        .decisions(List.of(
                                "客户 360 API 以 OpenAPI 字段为准，周五前冻结",
                                "权限验收先覆盖三类核心角色，再补充边界角色"))
                        .blockers(List.of("测试账号权限数据不完整", "客户标签接口返回字段仍有歧义"))
                        .summary("会议确认了联调冻结点、权限验收优先级和客户承诺事项，Agent 将阻塞点转入风险雷达和行动项。")
                        .createdAt(DateUtils.nowIso())
                        .build()
        )));

        risks.put(DEFAULT_SPACE_ID, new ArrayList<>(List.of(
                RiskItemResponse.builder()
                        .riskId("risk-001")
                        .spaceId(DEFAULT_SPACE_ID)
                        .title("接口字段冻结晚于前端联调窗口")
                        .category("delivery")
                        .severity("high")
                        .probability(4)
                        .impact(5)
                        .score(20)
                        .status("watching")
                        .owner("Backend Lead")
                        .triggerSignal("同一字段在会议纪要和接口草稿中出现两个命名")
                        .mitigationPlan("当天完成字段表对齐，未确认字段用 nullable 兼容")
                        .nextReviewDate("2026-06-27")
                        .updatedAt(DateUtils.nowIso())
                        .build(),
                RiskItemResponse.builder()
                        .riskId("risk-002")
                        .spaceId(DEFAULT_SPACE_ID)
                        .title("客户验收口径扩展导致范围漂移")
                        .category("scope")
                        .severity("medium")
                        .probability(3)
                        .impact(4)
                        .score(12)
                        .status("open")
                        .owner("交付 PM")
                        .triggerSignal("客户新增运营视角导出诉求")
                        .mitigationPlan("将新增诉求进入变更池，本周只确认是否影响上线门槛")
                        .nextReviewDate("2026-06-28")
                        .updatedAt(DateUtils.nowIso())
                        .build()
        )));

        weeklyReports.put(DEFAULT_SPACE_ID, new ArrayList<>(List.of(
                WeeklyReportResponse.builder()
                        .reportId("report-001")
                        .spaceId(DEFAULT_SPACE_ID)
                        .weekLabel("2026-W26")
                        .executiveSummary("本周整体进度 68%，核心风险集中在接口冻结和验收范围，建议 PM 今日完成字段冻结确认。")
                        .progressSummary("任务拆解已覆盖 PM、后端、前端、QA 四条主线，权限验收和行动项看板推进稳定。")
                        .riskSummary("高风险 1 个，中风险 1 个；均已绑定负责人和复盘日期。")
                        .planSummary("下周聚焦客户 360 冒烟、权限验收闭环和上线复盘材料。")
                        .completedItems(List.of("完成交付主线拆解", "沉淀联调例会纪要", "建立风险雷达"))
                        .nextWeekFocus(List.of("冻结客户 360 API", "完成权限矩阵自动化冒烟", "整理客户验收材料"))
                        .helpNeeded(List.of("客户确认运营导出是否进入本次上线范围"))
                        .generatedAt(DateUtils.nowIso())
                        .generatedBy("Project Copilot")
                        .build()
        )));

        actionItems.put(DEFAULT_SPACE_ID, new ArrayList<>(List.of(
                ActionItemResponse.builder()
                        .actionItemId("action-001")
                        .spaceId(DEFAULT_SPACE_ID)
                        .sourceType("meeting")
                        .sourceId("meeting-001")
                        .title("确认客户标签接口字段命名")
                        .owner("Backend Lead")
                        .status("in_progress")
                        .priority("high")
                        .dueDate("2026-06-27")
                        .reminderAt("2026-06-27T02:00:00Z")
                        .boundary("技术负责人确认字段，Agent 仅提醒风险和影响面")
                        .updatedAt(DateUtils.nowIso())
                        .build(),
                ActionItemResponse.builder()
                        .actionItemId("action-002")
                        .spaceId(DEFAULT_SPACE_ID)
                        .sourceType("risk")
                        .sourceId("risk-002")
                        .title("判断运营导出是否进入上线门槛")
                        .owner("交付 PM")
                        .status("open")
                        .priority("medium")
                        .dueDate("2026-06-28")
                        .reminderAt("2026-06-28T01:30:00Z")
                        .boundary("范围变更由 PM 与客户确认，Agent 提供影响清单")
                        .updatedAt(DateUtils.nowIso())
                        .build(),
                ActionItemResponse.builder()
                        .actionItemId("action-003")
                        .spaceId(DEFAULT_SPACE_ID)
                        .sourceType("task")
                        .sourceId("task-003")
                        .title("补齐三类角色权限测试数据")
                        .owner("QA Lead")
                        .status("open")
                        .priority("high")
                        .dueDate("2026-06-30")
                        .reminderAt("2026-06-29T07:00:00Z")
                        .boundary("QA 负责验收数据，Agent 标记未覆盖角色")
                        .updatedAt(DateUtils.nowIso())
                        .build()
        )));

        collaborationHistory.put(DEFAULT_SPACE_ID, new ArrayList<>(List.of(
                history("history-001", DEFAULT_SPACE_ID, "space_created", "建立项目空间", "交付 PM", "2026-06-24T02:00:00Z",
                        "创建 CRM 交付协作演练空间并明确 Agent 协作边界。", DEFAULT_SPACE_ID),
                history("history-002", DEFAULT_SPACE_ID, "task_breakdown", "生成任务拆解", "Project Copilot", "2026-06-24T03:10:00Z",
                        "按角色生成 PM、后端、前端、QA 工作包。", "task-001"),
                history("history-003", DEFAULT_SPACE_ID, "meeting_minutes", "沉淀联调例会纪要", "Project Copilot", "2026-06-25T07:20:00Z",
                        "从会议内容提取决策、阻塞点和行动项。", "meeting-001"),
                history("history-004", DEFAULT_SPACE_ID, "risk_scan", "识别字段冻结风险", "Project Copilot", "2026-06-25T07:26:00Z",
                        "将接口字段歧义升级为高风险观察项。", "risk-001"),
                history("history-005", DEFAULT_SPACE_ID, "weekly_report", "生成本周周报", "Project Copilot", "2026-06-26T09:00:00Z",
                        "汇总进度、风险、下周计划和需要协助事项。", "report-001")
        )));
    }

    public ProjectOverviewResponse getOverview() {
        List<ProjectSpaceResponse> spaceList = listSpaces();
        int activeTasks = tasks.values().stream()
                .flatMap(List::stream)
                .filter(task -> !"done".equals(task.getStatus()))
                .mapToInt(task -> 1)
                .sum();
        int highRiskCount = risks.values().stream()
                .flatMap(List::stream)
                .filter(risk -> "high".equals(risk.getSeverity()))
                .filter(risk -> !"closed".equals(risk.getStatus()))
                .mapToInt(risk -> 1)
                .sum();
        int overdueActionItems = actionItems.values().stream()
                .flatMap(List::stream)
                .filter(item -> !"done".equals(item.getStatus()))
                .filter(item -> item.getDueDate() != null && item.getDueDate().compareTo(DateUtils.today()) < 0)
                .mapToInt(item -> 1)
                .sum();

        return ProjectOverviewResponse.builder()
                .totalSpaces(spaceList.size())
                .activeTasks(activeTasks)
                .overdueActionItems(overdueActionItems)
                .highRiskCount(highRiskCount)
                .currentWeekLabel("2026-W26")
                .weeklyReportReady(weeklyReports.values().stream().anyMatch(list -> !list.isEmpty()))
                .spaces(spaceList)
                .metrics(List.of(
                        metric("delivery-progress", "平均交付进度", averageProgress(spaceList) + "%", "+8%", "success"),
                        metric("risk-pressure", "未关闭风险", String.valueOf(countOpenRisks()), "需关注", "warning"),
                        metric("action-followup", "未完成行动项", String.valueOf(countOpenActionItems()), "持续追踪", "primary"),
                        metric("collaboration-boundary", "协作边界覆盖", "3 条", "稳定", "info")))
                .build();
    }

    public List<ProjectSpaceResponse> listSpaces() {
        return new ArrayList<>(spaces.values());
    }

    public ProjectSpaceResponse getSpace(String spaceId) {
        return requireSpace(spaceId);
    }

    public ProjectSpaceResponse createSpace(ProjectSpaceCreateRequest request) {
        String spaceId = "space-" + String.format("%03d", spaceSequence.getAndIncrement());
        List<String> boundaries = safeList(request.getCollaborationBoundaries());
        if (boundaries.isEmpty()) {
            boundaries = List.of(
                    "Agent 输出为协作建议，项目负责人确认后生效",
                    "所有客户承诺必须形成行动项并绑定负责人");
        }

        ProjectSpaceResponse space = ProjectSpaceResponse.builder()
                .spaceId(spaceId)
                .name(request.getName())
                .deliveryGoal(request.getDeliveryGoal())
                .stage(defaultString(request.getStage(), "启动 / 范围对齐"))
                .healthStatus("healthy")
                .progressPercent(12)
                .sprintLabel("2026-W26")
                .owner(request.getOwner())
                .collaboratorCount(safeList(request.getCollaborators()).size())
                .activeRiskCount(0)
                .openActionItemCount(0)
                .nextCheckpointAt("2026-06-29T02:00:00Z")
                .updatedAt(DateUtils.nowIso())
                .collaborationBoundaries(boundaries)
                .build();
        spaces.put(spaceId, space);
        tasks.put(spaceId, new ArrayList<>());
        meetings.put(spaceId, new ArrayList<>());
        risks.put(spaceId, new ArrayList<>());
        weeklyReports.put(spaceId, new ArrayList<>());
        actionItems.put(spaceId, new ArrayList<>());
        collaborationHistory.put(spaceId, new ArrayList<>(List.of(
                history(nextHistoryId(), spaceId, "space_created", "建立项目空间", request.getOwner(), DateUtils.nowIso(),
                        "创建项目空间并登记协作边界。", spaceId)
        )));
        return space;
    }

    public List<TaskBreakdownResponse> listTaskBreakdown(String spaceId) {
        requireSpace(spaceId);
        return copy(tasks.get(spaceId));
    }

    public List<TaskBreakdownResponse> generateTaskBreakdown(String spaceId, TaskBreakdownGenerateRequest request) {
        ProjectSpaceResponse space = requireSpace(spaceId);
        List<TaskBreakdownResponse> generated = new ArrayList<>();
        List<String> boundaries = safeList(request.getRoleBoundaries());
        String dueDate = defaultString(request.getDueDate(), "2026-07-03");
        generated.add(task(spaceId, null, request.getObjective() + " / 交付拆解确认", "交付目标、范围和验收口径确认",
                "交付 PM", "open", "high", 0, 4, Collections.emptyList(),
                boundaryAt(boundaries, 0, "PM 确认范围，Agent 提供拆解草稿"), "范围、优先级和截止日期完成确认", dueDate));
        generated.add(task(spaceId, generated.get(0).getTaskId(), request.getObjective() + " / 技术实现路径", "拆分后端、前端和测试协作接口",
                "Tech Lead", "open", "high", 0, 8, List.of(generated.get(0).getTaskId()),
                boundaryAt(boundaries, 1, "Tech Lead 确认方案，Agent 标注依赖"), "接口、页面和测试依赖均可追踪", dueDate));
        generated.add(task(spaceId, generated.get(0).getTaskId(), request.getObjective() + " / 验收与周报素材", "沉淀验收标准、风险摘要和周报素材",
                "QA Lead", "open", "medium", 0, 6, List.of(generated.get(1).getTaskId()),
                boundaryAt(boundaries, 2, "QA 定义验收口径，Agent 辅助发现遗漏"), "验收清单可直接进入周报", dueDate));

        tasks.computeIfAbsent(spaceId, key -> new ArrayList<>()).addAll(generated);
        if (Boolean.TRUE.equals(request.getIncludeRiskScan())) {
            RiskItemResponse risk = risk(spaceId, "新拆解任务依赖尚未验证", "delivery", "medium", 3, 3,
                    "open", "交付 PM", "任务生成后出现跨角色依赖", "在下一次站会确认依赖负责人", dueDate);
            risks.computeIfAbsent(spaceId, key -> new ArrayList<>()).add(risk);
        }
        collaborationHistory.get(spaceId).add(history(nextHistoryId(), spaceId, "task_breakdown", "生成任务拆解", "Project Copilot",
                DateUtils.nowIso(), "围绕目标生成角色化任务拆解，并保留人工确认边界。", generated.get(0).getTaskId()));
        refreshSpaceCounters(space);
        return copy(tasks.get(spaceId));
    }

    public List<MeetingMinutesResponse> listMeetingMinutes(String spaceId) {
        requireSpace(spaceId);
        return copy(meetings.get(spaceId));
    }

    public MeetingMinutesResponse createMeetingMinutes(String spaceId, MeetingMinutesCreateRequest request) {
        ProjectSpaceResponse space = requireSpace(spaceId);
        MeetingMinutesResponse response = MeetingMinutesResponse.builder()
                .meetingId("meeting-" + String.format("%03d", meetingSequence.getAndIncrement()))
                .spaceId(spaceId)
                .title(request.getTitle())
                .meetingType(defaultString(request.getMeetingType(), "delivery_sync"))
                .heldAt(defaultString(request.getHeldAt(), DateUtils.nowIso()))
                .facilitator(defaultString(request.getFacilitator(), space.getOwner()))
                .participants(safeList(request.getParticipants()))
                .decisions(safeList(request.getDecisions()))
                .blockers(safeList(request.getBlockers()))
                .summary("Agent 已从会议输入中提取共识、阻塞点和后续行动，需由主持人确认后同步团队。")
                .createdAt(DateUtils.nowIso())
                .build();
        meetings.computeIfAbsent(spaceId, key -> new ArrayList<>()).add(response);
        collaborationHistory.get(spaceId).add(history(nextHistoryId(), spaceId, "meeting_minutes", "新增会议纪要",
                response.getFacilitator(), DateUtils.nowIso(), "新增会议纪要并保留人工确认边界。", response.getMeetingId()));
        return response;
    }

    public List<RiskItemResponse> listRisks(String spaceId) {
        requireSpace(spaceId);
        return copy(risks.get(spaceId));
    }

    public RiskItemResponse updateRisk(String spaceId, String riskId, RiskUpdateRequest request) {
        ProjectSpaceResponse space = requireSpace(spaceId);
        RiskItemResponse risk = findRisk(spaceId, riskId);
        risk.setStatus(request.getStatus());
        risk.setMitigationPlan(defaultString(request.getMitigationPlan(), risk.getMitigationPlan()));
        risk.setOwner(defaultString(request.getOwner(), risk.getOwner()));
        risk.setNextReviewDate(defaultString(request.getNextReviewDate(), risk.getNextReviewDate()));
        risk.setUpdatedAt(DateUtils.nowIso());
        collaborationHistory.get(spaceId).add(history(nextHistoryId(), spaceId, "risk_update", "更新风险状态",
                risk.getOwner(), DateUtils.nowIso(), "风险状态更新为 " + risk.getStatus() + "。", riskId));
        refreshSpaceCounters(space);
        return risk;
    }

    public List<WeeklyReportResponse> listWeeklyReports(String spaceId) {
        requireSpace(spaceId);
        return copy(weeklyReports.get(spaceId));
    }

    public WeeklyReportResponse generateWeeklyReport(String spaceId, WeeklyReportGenerateRequest request) {
        ProjectSpaceResponse space = requireSpace(spaceId);
        List<RiskItemResponse> riskList = risks.getOrDefault(spaceId, Collections.emptyList());
        List<ActionItemResponse> actionList = actionItems.getOrDefault(spaceId, Collections.emptyList());
        WeeklyReportResponse report = WeeklyReportResponse.builder()
                .reportId("report-" + String.format("%03d", reportSequence.getAndIncrement()))
                .spaceId(spaceId)
                .weekLabel(request.getWeekLabel())
                .executiveSummary(space.getName() + " 当前进度 " + space.getProgressPercent() + "%，健康状态为 " + space.getHealthStatus() + "。")
                .progressSummary("任务总数 " + tasks.getOrDefault(spaceId, Collections.emptyList()).size() + " 个，仍需持续追踪跨角色依赖。")
                .riskSummary(Boolean.FALSE.equals(request.getIncludeRisks()) ? "本次周报未纳入风险摘要。" : "未关闭风险 " + countOpenRisks(riskList) + " 个，需按复盘日期推进。")
                .planSummary("下周继续围绕验收闭环、行动项清理和对外同步节奏推进。")
                .completedItems(List.of("同步项目空间进度", "沉淀关键风险与行动项", "确认团队协作边界"))
                .nextWeekFocus(List.of("清理高优行动项", "复盘未关闭风险", "准备客户验收材料"))
                .helpNeeded(Boolean.FALSE.equals(request.getIncludeActionItems()) ? Collections.emptyList() : openActionTitles(actionList))
                .generatedAt(DateUtils.nowIso())
                .generatedBy(defaultString(request.getGeneratedBy(), "Project Copilot"))
                .build();
        weeklyReports.computeIfAbsent(spaceId, key -> new ArrayList<>()).add(report);
        collaborationHistory.get(spaceId).add(history(nextHistoryId(), spaceId, "weekly_report", "生成项目周报",
                report.getGeneratedBy(), DateUtils.nowIso(), "生成周报草稿，等待项目负责人确认后发布。", report.getReportId()));
        return report;
    }

    public List<ActionItemResponse> listActionItems(String spaceId) {
        requireSpace(spaceId);
        return copy(actionItems.get(spaceId));
    }

    public ActionItemResponse updateActionItem(String spaceId, String actionItemId, ActionItemUpdateRequest request) {
        ProjectSpaceResponse space = requireSpace(spaceId);
        ActionItemResponse item = findActionItem(spaceId, actionItemId);
        item.setStatus(request.getStatus());
        item.setOwner(defaultString(request.getOwner(), item.getOwner()));
        item.setDueDate(defaultString(request.getDueDate(), item.getDueDate()));
        item.setReminderAt(defaultString(request.getReminderAt(), item.getReminderAt()));
        item.setUpdatedAt(DateUtils.nowIso());
        collaborationHistory.get(spaceId).add(history(nextHistoryId(), spaceId, "action_item_update", "更新行动项",
                item.getOwner(), DateUtils.nowIso(), "行动项状态更新为 " + item.getStatus() + "。", actionItemId));
        refreshSpaceCounters(space);
        return item;
    }

    public List<CollaborationHistoryResponse> listCollaborationHistory(String spaceId) {
        requireSpace(spaceId);
        return copy(collaborationHistory.get(spaceId));
    }

    private ProjectSpaceResponse requireSpace(String spaceId) {
        ProjectSpaceResponse space = spaces.get(spaceId);
        if (space == null) {
            throw new BizException(ProjectCopilotErrorCode.PROJECT_SPACE_NOT_FOUND);
        }
        return space;
    }

    private RiskItemResponse findRisk(String spaceId, String riskId) {
        return risks.getOrDefault(spaceId, Collections.emptyList()).stream()
                .filter(risk -> Objects.equals(risk.getRiskId(), riskId))
                .findFirst()
                .orElseThrow(() -> new BizException(ProjectCopilotErrorCode.RISK_ITEM_NOT_FOUND));
    }

    private ActionItemResponse findActionItem(String spaceId, String actionItemId) {
        return actionItems.getOrDefault(spaceId, Collections.emptyList()).stream()
                .filter(item -> Objects.equals(item.getActionItemId(), actionItemId))
                .findFirst()
                .orElseThrow(() -> new BizException(ProjectCopilotErrorCode.ACTION_ITEM_NOT_FOUND));
    }

    private void refreshSpaceCounters(ProjectSpaceResponse space) {
        space.setActiveRiskCount(countOpenRisks(risks.getOrDefault(space.getSpaceId(), Collections.emptyList())));
        space.setOpenActionItemCount(openActionTitles(actionItems.getOrDefault(space.getSpaceId(), Collections.emptyList())).size());
        space.setUpdatedAt(DateUtils.nowIso());
    }

    private int averageProgress(List<ProjectSpaceResponse> spaceList) {
        if (spaceList.isEmpty()) {
            return 0;
        }
        int total = spaceList.stream().map(ProjectSpaceResponse::getProgressPercent).filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
        return total / spaceList.size();
    }

    private int countOpenRisks() {
        return risks.values().stream().mapToInt(this::countOpenRisks).sum();
    }

    private int countOpenRisks(List<RiskItemResponse> riskList) {
        return (int) riskList.stream().filter(risk -> !"closed".equals(risk.getStatus())).count();
    }

    private int countOpenActionItems() {
        return actionItems.values().stream().mapToInt(list -> openActionTitles(list).size()).sum();
    }

    private List<String> openActionTitles(List<ActionItemResponse> list) {
        return list.stream()
                .filter(item -> !"done".equals(item.getStatus()))
                .map(ActionItemResponse::getTitle)
                .toList();
    }

    private ProjectMetricResponse metric(String key, String label, String value, String trend, String tone) {
        return ProjectMetricResponse.builder()
                .key(key)
                .label(label)
                .value(value)
                .trend(trend)
                .tone(tone)
                .build();
    }

    private TaskBreakdownResponse task(String spaceId, String parentTaskId, String title, String description,
                                       String roleOwner, String status, String priority, int progressPercent,
                                       int estimateHours, List<String> dependencyTaskIds, String boundary,
                                       String acceptanceCriteria, String dueDate) {
        return TaskBreakdownResponse.builder()
                .taskId("task-" + String.format("%03d", taskSequence.getAndIncrement()))
                .spaceId(spaceId)
                .parentTaskId(parentTaskId)
                .title(title)
                .description(description)
                .roleOwner(roleOwner)
                .status(status)
                .priority(priority)
                .progressPercent(progressPercent)
                .estimateHours(estimateHours)
                .dependencyTaskIds(dependencyTaskIds)
                .collaborationBoundary(boundary)
                .acceptanceCriteria(acceptanceCriteria)
                .dueDate(dueDate)
                .updatedAt(DateUtils.nowIso())
                .build();
    }

    private RiskItemResponse risk(String spaceId, String title, String category, String severity, int probability,
                                  int impact, String status, String owner, String triggerSignal,
                                  String mitigationPlan, String nextReviewDate) {
        return RiskItemResponse.builder()
                .riskId("risk-" + String.format("%03d", riskSequence.getAndIncrement()))
                .spaceId(spaceId)
                .title(title)
                .category(category)
                .severity(severity)
                .probability(probability)
                .impact(impact)
                .score(probability * impact)
                .status(status)
                .owner(owner)
                .triggerSignal(triggerSignal)
                .mitigationPlan(mitigationPlan)
                .nextReviewDate(nextReviewDate)
                .updatedAt(DateUtils.nowIso())
                .build();
    }

    private CollaborationHistoryResponse history(String eventId, String spaceId, String eventType, String title,
                                                 String actor, String occurredAt, String summary,
                                                 String relatedItemId) {
        return CollaborationHistoryResponse.builder()
                .eventId(eventId)
                .spaceId(spaceId)
                .eventType(eventType)
                .title(title)
                .actor(actor)
                .occurredAt(occurredAt)
                .summary(summary)
                .relatedItemId(relatedItemId)
                .build();
    }

    private String nextHistoryId() {
        return "history-" + String.format("%03d", historySequence.getAndIncrement());
    }

    private String boundaryAt(List<String> boundaries, int index, String fallback) {
        if (boundaries.size() > index) {
            return boundaries.get(index);
        }
        return fallback;
    }

    private String defaultString(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private List<String> safeList(List<String> values) {
        return values == null ? new ArrayList<>() : new ArrayList<>(values);
    }

    private <T> List<T> copy(List<T> values) {
        return values == null ? new ArrayList<>() : new ArrayList<>(values);
    }
}
