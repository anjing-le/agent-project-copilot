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
@Schema(description = "协作历史")
public class CollaborationHistoryResponse {

    @Schema(description = "事件 ID")
    private String eventId;

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "事件类型")
    private String eventType;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "操作者")
    private String actor;

    @Schema(description = "发生时间")
    private String occurredAt;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "关联对象 ID")
    private String relatedItemId;
}
