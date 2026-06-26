package com.anjing.projectcopilot.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "风险雷达项")
public class RiskItemResponse {

    @Schema(description = "风险 ID")
    private String riskId;

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "严重级别")
    private String severity;

    @Schema(description = "发生概率")
    private Integer probability;

    @Schema(description = "影响程度")
    private Integer impact;

    @Schema(description = "风险分")
    private Integer score;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "负责人")
    private String owner;

    @Schema(description = "触发信号")
    private String triggerSignal;

    @Schema(description = "缓解方案")
    private String mitigationPlan;

    @Schema(description = "下次复盘日期")
    private String nextReviewDate;

    @Schema(description = "更新时间")
    private String updatedAt;
}
