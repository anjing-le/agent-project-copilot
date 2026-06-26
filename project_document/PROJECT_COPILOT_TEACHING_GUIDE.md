# Project Copilot Teaching Guide

本文面向教学演示，说明 `agent-project-copilot` 如何把脚手架能力重构为项目交付协作 Agent。它重点展示 Agent 如何辅助团队做任务拆解、会议纪要、风险识别、进度跟踪、周报生成、行动项追踪和协作历史沉淀。

## 教学定位

- Project Copilot 是项目交付协作 Agent，不是项目经理、研发负责人或审批人的替代品。
- Agent 负责整理信息、提出拆解建议、识别风险信号、生成周报草稿和追踪行动项。
- 人类团队负责确认优先级、排期、责任人、对外承诺和最终决策。
- 所有演示模块都沿用脚手架的 API envelope、OpenAPI 类型生成、服务边界 manifest、统一路径和质量门禁。

## 演示路径

1. 项目空间：创建一个项目空间，记录目标、阶段、团队、交付节奏和协作边界。
2. 任务拆解：输入项目目标，生成 Epic、Feature、Task、Subtask 的层级拆解和依赖提示。
3. 会议纪要：把会议主题、参会人和原始记录转成摘要、决策、待办和风险信号。
4. 风险雷达：按范围、进度、质量、依赖和人员维度观察风险等级与缓解动作。
5. 周报生成：聚合空间进度、完成项、阻塞、下周计划和需要升级的问题。
6. 行动项看板：按 owner、截止日期、状态和来源追踪会议或风险产生的行动项。
7. 协作历史：保留 Agent 建议、人工确认、任务变更、纪要生成和风险更新记录。

## 模块边界

| 模块 | 后端接口 | 前端视图 | 协作边界 |
|------|----------|----------|----------|
| 总览 | `GET /api/project-copilot/overview` | 指标栏与当前项目摘要 | 只展示聚合态势，不替代团队判断 |
| 项目空间 | `/api/project-copilot/spaces` | 左侧项目空间列表与新建弹窗 | 记录项目语境和边界，人工确认目标与团队 |
| 任务拆解 | `/api/project-copilot/spaces/{spaceId}/tasks` | 任务拆解标签页与生成弹窗 | Agent 给拆解建议，团队确认承诺范围 |
| 会议纪要 | `/api/project-copilot/spaces/{spaceId}/meetings` | 会议纪要标签页与纪要弹窗 | Agent 结构化记录，关键决策需参会人确认 |
| 风险雷达 | `/api/project-copilot/spaces/{spaceId}/risks` | 风险雷达标签页 | Agent 暴露风险信号，负责人确认缓解动作 |
| 周报生成 | `/api/project-copilot/spaces/{spaceId}/weekly-reports` | 周报生成标签页与生成弹窗 | Agent 生成草稿，项目负责人发布前审核 |
| 行动项看板 | `/api/project-copilot/spaces/{spaceId}/action-items` | 行动项看板标签页 | Agent 跟踪状态，owner 对真实完成负责 |
| 协作历史 | `/api/project-copilot/spaces/{spaceId}/history` | 协作历史标签页 | 保留过程证据，避免“黑盒式”自动化 |

## Agent 协作边界

- 可以建议：任务拆分、依赖排序、风险等级、纪要摘要、周报表达和行动项候选。
- 不自动承诺：上线日期、跨团队资源、质量豁免、预算变更和对客户承诺。
- 不覆盖事实：输入材料不足时必须暴露假设，不伪造会议结论或团队共识。
- 不隐藏分歧：对存在争议的决策，应在纪要或协作历史中标记为待确认。
- 不绕过责任人：每个行动项都需要 owner，不能只有 Agent 或系统作为责任主体。

## 验收命令

```bash
./scripts/check-template.sh
./scripts/check-contracts.sh
./scripts/probe-backend-dev.sh
(cd backend && mvn -q -DskipTests package)
(cd frontend && pnpm build)
```

## 演示入口

- Frontend: `http://localhost:13016/#/project-copilot/workbench`
- Backend: `http://localhost:18090`
- OpenAPI: `http://localhost:18090/v3/api-docs`
