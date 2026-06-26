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
@Schema(description = "项目周报")
public class WeeklyReportResponse {

    @Schema(description = "周报 ID")
    private String reportId;

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "周标签")
    private String weekLabel;

    @Schema(description = "管理摘要")
    private String executiveSummary;

    @Schema(description = "进度摘要")
    private String progressSummary;

    @Schema(description = "风险摘要")
    private String riskSummary;

    @Schema(description = "计划摘要")
    private String planSummary;

    @Schema(description = "已完成事项")
    private List<String> completedItems;

    @Schema(description = "下周重点")
    private List<String> nextWeekFocus;

    @Schema(description = "需要协助")
    private List<String> helpNeeded;

    @Schema(description = "生成时间")
    private String generatedAt;

    @Schema(description = "生成者")
    private String generatedBy;
}
