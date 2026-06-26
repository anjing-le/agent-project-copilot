package com.anjing.projectcopilot.model.request;

import com.anjing.model.request.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Schema(description = "会议纪要创建请求")
public class MeetingMinutesCreateRequest extends BaseRequest {

    @NotBlank(message = "会议标题不能为空")
    @Schema(description = "会议标题", example = "Sprint 2 联调站会")
    private String title;

    @Schema(description = "会议类型", example = "daily_sync")
    private String meetingType;

    @Schema(description = "会议时间", example = "2026-06-26T02:30:00Z")
    private String heldAt;

    @Schema(description = "主持人", example = "交付 PM")
    private String facilitator;

    @NotEmpty(message = "参会人不能为空")
    @Schema(description = "参会人")
    private List<String> participants;

    @NotBlank(message = "会议原文不能为空")
    @Schema(description = "会议原文或摘要输入")
    private String transcript;

    @Schema(description = "已确认决策")
    private List<String> decisions;

    @Schema(description = "阻塞点")
    private List<String> blockers;
}
