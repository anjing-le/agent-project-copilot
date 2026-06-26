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
@Schema(description = "周报生成请求")
public class WeeklyReportGenerateRequest extends BaseRequest {

    @NotBlank(message = "周报周期不能为空")
    @Schema(description = "周报周期", example = "2026-W26")
    private String weekLabel;

    @Schema(description = "是否纳入风险", example = "true")
    private Boolean includeRisks;

    @Schema(description = "是否纳入行动项", example = "true")
    private Boolean includeActionItems;

    @Schema(description = "生成者", example = "Project Copilot")
    private String generatedBy;
}
