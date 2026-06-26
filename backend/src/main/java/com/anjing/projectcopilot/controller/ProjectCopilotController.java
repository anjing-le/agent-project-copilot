package com.anjing.projectcopilot.controller;

import com.anjing.model.constants.ApiConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目交付协作 Agent 运行接口边界。
 */
@Slf4j
@RestController
@RequestMapping(ApiConstants.ProjectCopilot.BASE)
@RequiredArgsConstructor
@Tag(name = "Project Copilot", description = "Project delivery collaboration agent APIs")
public class ProjectCopilotController {
}
