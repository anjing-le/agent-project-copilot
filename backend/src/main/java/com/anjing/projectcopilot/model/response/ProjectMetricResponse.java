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
@Schema(description = "项目协作指标")
public class ProjectMetricResponse {

    @Schema(description = "指标键")
    private String key;

    @Schema(description = "指标名称")
    private String label;

    @Schema(description = "指标值")
    private String value;

    @Schema(description = "趋势")
    private String trend;

    @Schema(description = "展示语义")
    private String tone;
}
