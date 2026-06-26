# Project Document

本目录存放 `agent-project-copilot` 的定位、规划、验收标准和进度记录。

## 文档

- [ROADMAP.md](./ROADMAP.md)：项目定位、阶段规划、边界和成功标准。
- [PROJECT_COPILOT_TEACHING_GUIDE.md](./PROJECT_COPILOT_TEACHING_GUIDE.md)：协作型 Agent 教学模块、演示路径和团队边界。
- [SEVEN_AGENT_COURSE_BRAND_PLAN.md](./SEVEN_AGENT_COURSE_BRAND_PLAN.md)：七大 Agent 实战课的课程定位、项目矩阵、品牌方向和教学物料规划。
- [PROJECT_CONSTRAINTS.md](./PROJECT_CONSTRAINTS.md)：项目长期约束、防破窗规则和脚本门禁清单。
- [NEW_MODULE_GUIDE.md](./NEW_MODULE_GUIDE.md)：新增业务模块时的前后端契约、交付顺序和 AI Prompt 约束。
- [SCAFFOLD_ADOPTION_PROMPT.md](./SCAFFOLD_ADOPTION_PROMPT.md)：把旧项目接入本母版时可复制给 Codex 的提示词。
- [UI_DESIGN_GUIDE.md](./UI_DESIGN_GUIDE.md)：极简、虚线、轻玻璃 UI 基线和防破窗规则。
- [DEMO_EVIDENCE.md](./DEMO_EVIDENCE.md)：发布和复制前应保留的演示证据。
- [ci/quality-gate.yml](./ci/quality-gate.yml)：GitHub Actions 质量门禁模板，启用时复制到 `.github/workflows/quality-gate.yml`。
- [../contracts/platform-contract.json](../contracts/platform-contract.json)：机器可读平台契约 manifest。
- [STATUS.md](./STATUS.md)：当前阶段状态、验证证据和下一步复用方向。
- [RELEASE_CHECKLIST.md](./RELEASE_CHECKLIST.md)：每轮发布/复制前的检查清单。
- [COPY_GUIDE.md](./COPY_GUIDE.md)：复制为新项目时的改名和验证步骤。
- [TEMPLATE_BOUNDARIES.md](./TEMPLATE_BOUNDARIES.md)：母版能力、示例能力和旧残留边界。
- [PLATFORM_CONTRACT_GUIDE.md](./PLATFORM_CONTRACT_GUIDE.md)：机器可读平台契约 manifest 的范围和维护方式。
- [OPENAPI_CONTRACT_GUIDE.md](./OPENAPI_CONTRACT_GUIDE.md)：OpenAPI 运行接口契约、生产开关和前端 schema 类型生成链路。
- [SERVICE_BOUNDARY_GUIDE.md](./SERVICE_BOUNDARY_GUIDE.md)：服务/模块边界、API 归属和未来微服务拆分方式。
- [SHARED_KERNEL_GUIDE.md](./SHARED_KERNEL_GUIDE.md)：未来共享包可抽取类和运行时依赖边界。
- [AI_ASSETS.md](./AI_ASSETS.md)：Cursor Rules / Prompts 的资产清单和维护规则。

## 维护原则

- 所有规划要服务于“协作型 Agent 教学项目”这个目标，同时继承开源工程母版的质量门禁。
- 新增能力必须先对齐 [PROJECT_CONSTRAINTS.md](./PROJECT_CONSTRAINTS.md)。
- 文档里的启动命令、端口、验证命令必须和当前代码一致。
- 新增功能如果不能解释项目交付协作 Agent 的任务拆解、纪要、风险、进度、周报、行动项或团队边界，应放到临时复制项目或独立示例仓库中。
