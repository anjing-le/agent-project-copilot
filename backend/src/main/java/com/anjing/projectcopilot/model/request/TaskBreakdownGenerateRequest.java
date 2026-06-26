package com.anjing.projectcopilot.model.request;

import com.anjing.model.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "任务拆解生成请求")
public class TaskBreakdownGenerateRequest extends BaseRequest {

    @NotBlank(message = "拆解目标不能为空")
    @Schema(description = "拆解目标", example = "上线客户 360 视图")
    private String objective;

    @Schema(description = "拆解粒度", example = "role_task")
    private String granularity;

    @Schema(description = "角色边界")
    private List<String> roleBoundaries;

    @Schema(description = "交付截止日期", example = "2026-07-03")
    private String dueDate;

    @Schema(description = "是否同步扫描风险", example = "true")
    private Boolean includeRiskScan;
}
