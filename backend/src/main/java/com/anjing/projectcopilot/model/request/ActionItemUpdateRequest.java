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
@Schema(description = "行动项更新请求")
public class ActionItemUpdateRequest extends BaseRequest {

    @NotBlank(message = "行动项状态不能为空")
    @Schema(description = "行动项状态", example = "in_progress")
    private String status;

    @Schema(description = "负责人", example = "QA Lead")
    private String owner;

    @Schema(description = "到期日期", example = "2026-06-30")
    private String dueDate;

    @Schema(description = "提醒时间", example = "2026-06-29T09:30:00Z")
    private String reminderAt;
}
