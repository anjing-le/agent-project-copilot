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
@Schema(description = "项目空间创建请求")
public class ProjectSpaceCreateRequest extends BaseRequest {

    @NotBlank(message = "项目空间名称不能为空")
    @Schema(description = "项目空间名称", example = "CRM 二期交付")
    private String name;

    @NotBlank(message = "交付目标不能为空")
    @Schema(description = "交付目标", example = "完成客户 360 视图上线")
    private String deliveryGoal;

    @NotBlank(message = "负责人不能为空")
    @Schema(description = "空间负责人", example = "交付 PM")
    private String owner;

    @Schema(description = "交付阶段", example = "需求澄清")
    private String stage;

    @Schema(description = "协作者")
    private List<String> collaborators;

    @Schema(description = "协作边界")
    private List<String> collaborationBoundaries;
}
