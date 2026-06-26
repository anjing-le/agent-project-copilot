package com.anjing.projectcopilot.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "项目协作总览")
public class ProjectOverviewResponse {

    @Schema(description = "项目空间总数")
    private Integer totalSpaces;

    @Schema(description = "活跃任务数")
    private Integer activeTasks;

    @Schema(description = "逾期行动项数")
    private Integer overdueActionItems;

    @Schema(description = "高风险数量")
    private Integer highRiskCount;

    @Schema(description = "当前周")
    private String currentWeekLabel;

    @Schema(description = "周报是否已就绪")
    private Boolean weeklyReportReady;

    @Schema(description = "项目空间列表")
    private List<ProjectSpaceResponse> spaces;

    @Schema(description = "协作指标")
    private List<ProjectMetricResponse> metrics;
}
