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
@Schema(description = "行动项")
public class ActionItemResponse {

    @Schema(description = "行动项 ID")
    private String actionItemId;

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "来源类型")
    private String sourceType;

    @Schema(description = "来源 ID")
    private String sourceId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "负责人")
    private String owner;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "优先级")
    private String priority;

    @Schema(description = "到期日期")
    private String dueDate;

    @Schema(description = "提醒时间")
    private String reminderAt;

    @Schema(description = "协作边界")
    private String boundary;

    @Schema(description = "更新时间")
    private String updatedAt;
}
