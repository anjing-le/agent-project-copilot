# agent-project-copilot

Anjing 协作型 Agent 教学项目：面向项目交付团队，展示 Agent 如何辅助任务拆解、会议纪要、风险识别、进度跟踪、周报生成、行动项追踪和团队协同边界。

项目基于 Anjing 全栈工程母版重构，保留前后端、契约、约束和质量门禁的一体化工程习惯，让教学业务能启动、能演示、能被 AI 接手继续迭代。

`Vue 3.5` · `TypeScript` · `Vite 7` · `Spring Boot 3.4.5` · `Java 17` · `OpenAPI` · `H2 / MySQL`

## 它展示什么

| 模块 | 教学重点 |
| --- | --- |
| 项目空间 | 项目目标、阶段、成员和交付节奏如何被 Agent 组织 |
| 任务拆解 | 从目标到里程碑、任务、依赖和验收口径 |
| 会议纪要 | 把会议输入沉淀成决议、风险和行动项 |
| 风险雷达 | 识别影响、概率、状态和责任边界 |
| 周报生成 | 将进度、风险、阻塞和下一步汇总为可交付周报 |
| 行动项看板 | 跟踪 owner、截止时间、状态和逾期提醒 |
| 协作历史 | 记录 Agent 建议、人类确认和团队交接痕迹 |

## 快速开始

前端：

```bash
cd frontend
pnpm install
pnpm dev
```

打开 `http://localhost:13016`。

后端：

```bash
cd backend
mvn spring-boot:run
```

后端端口是 `18090`。默认 dev profile 使用 H2，本地不需要 MySQL、Redis 或其他中间件。

## 技术栈

| 层 | 技术 |
| --- | --- |
| Frontend | Vue 3.5, TypeScript, Vite 7, Pinia, Vue Router |
| UI | Element Plus, SCSS, Tailwind CSS 4 |
| Backend | Spring Boot 3.4.5, Java 17, Maven |
| Data | H2 for dev/test, MySQL-ready |
| Contract | OpenAPI, generated types, API path checks |
| Governance | Shell/Node scripts, project constraints, CI template |

## 使用入口

| 想做什么 | 看这里 |
| --- | --- |
| 理解协作 Agent 边界 | [PROJECT_CONSTRAINTS.md](./project_document/PROJECT_CONSTRAINTS.md) |
| 新增业务模块 | [NEW_MODULE_GUIDE.md](./project_document/NEW_MODULE_GUIDE.md) |
| 查看演示证据 | [DEMO_EVIDENCE.md](./project_document/DEMO_EVIDENCE.md) |
| 调整界面风格 | [UI_DESIGN_GUIDE.md](./project_document/UI_DESIGN_GUIDE.md) |
| 让 Codex 接手 | [SCAFFOLD_ADOPTION_PROMPT.md](./project_document/SCAFFOLD_ADOPTION_PROMPT.md) |
| 复制成新项目 | [COPY_GUIDE.md](./project_document/COPY_GUIDE.md) |

## 质量门禁

重要提交前跑完整门禁：

```bash
./scripts/quality-gate.sh
```

文档或轻量模板调整：

```bash
./scripts/check-template.sh
./scripts/check-contracts.sh
```

GitHub Actions 模板在 [project_document/ci/quality-gate.yml](./project_document/ci/quality-gate.yml)。

## 目录

```text
frontend/           Vue 前端工程
backend/            Spring Boot 后端工程
contracts/          平台契约和服务边界 manifest
project_document/   路线图、约束、指南和状态记录
scripts/            自检、生成、复制和质量门禁脚本
```

## License

MIT. 前端工程基于 Art Design Pro 定制，保留 [frontend/LICENSE](./frontend/LICENSE) 中的上游许可说明。
