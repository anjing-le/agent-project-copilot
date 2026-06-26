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
@Schema(description = "任务拆解项")
public class TaskBreakdownResponse {

    @Schema(description = "任务 ID")
    private String taskId;

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "父任务 ID")
    private String parentTaskId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "说明")
    private String description;

    @Schema(description = "角色负责人")
    private String roleOwner;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "优先级")
    private String priority;

    @Schema(description = "进度百分比")
    private Integer progressPercent;

    @Schema(description = "预估工时")
    private Integer estimateHours;

    @Schema(description = "依赖任务 ID")
    private List<String> dependencyTaskIds;

    @Schema(description = "协作边界")
    private String collaborationBoundary;

    @Schema(description = "验收标准")
    private String acceptanceCriteria;

    @Schema(description = "截止日期")
    private String dueDate;

    @Schema(description = "更新时间")
    private String updatedAt;
}
