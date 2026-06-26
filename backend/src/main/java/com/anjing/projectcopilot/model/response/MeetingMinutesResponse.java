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
@Schema(description = "会议纪要")
public class MeetingMinutesResponse {

    @Schema(description = "会议 ID")
    private String meetingId;

    @Schema(description = "项目空间 ID")
    private String spaceId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "会议类型")
    private String meetingType;

    @Schema(description = "会议时间")
    private String heldAt;

    @Schema(description = "主持人")
    private String facilitator;

    @Schema(description = "参会人")
    private List<String> participants;

    @Schema(description = "决策")
    private List<String> decisions;

    @Schema(description = "阻塞点")
    private List<String> blockers;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "创建时间")
    private String createdAt;
}
