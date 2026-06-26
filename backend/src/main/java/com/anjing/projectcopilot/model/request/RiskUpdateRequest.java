package com.anjing.projectcopilot.model.request;

import com.anjing.model.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "风险项更新请求")
public class RiskUpdateRequest extends BaseRequest {

    @NotBlank(message = "风险状态不能为空")
    @Schema(description = "风险状态", example = "watching")
    private String status;

    @Schema(description = "缓解方案", example = "将集成冒烟提前到每日 16:00")
    private String mitigationPlan;

    @Schema(description = "负责人", example = "Tech Lead")
    private String owner;

    @Schema(description = "下次复盘日期", example = "2026-06-28")
    private String nextReviewDate;
}
