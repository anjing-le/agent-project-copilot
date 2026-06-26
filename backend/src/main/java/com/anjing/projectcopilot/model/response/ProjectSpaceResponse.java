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
@Schema(description = "项目空间")
public class ProjectSpaceResponse {

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "空间名称")
    private String name;

    @Schema(description = "交付目标")
    private String deliveryGoal;

    @Schema(description = "交付阶段")
    private String stage;

    @Schema(description = "健康状态")
    private String healthStatus;

    @Schema(description = "进度百分比")
    private Integer progressPercent;

    @Schema(description = "迭代标签")
    private String sprintLabel;

    @Schema(description = "负责人")
    private String owner;

    @Schema(description = "协作者数量")
    private Integer collaboratorCount;

    @Schema(description = "活跃风险数量")
    private Integer activeRiskCount;

    @Schema(description = "打开的行动项数量")
    private Integer openActionItemCount;

    @Schema(description = "下一检查点")
    private String nextCheckpointAt;

    @Schema(description = "更新时间")
    private String updatedAt;

    @Schema(description = "团队协作边界")
    private List<String> collaborationBoundaries;
}
