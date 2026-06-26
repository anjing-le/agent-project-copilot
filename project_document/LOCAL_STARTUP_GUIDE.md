# Local Startup Guide

本文档记录 Project Copilot 的本地轻启动方式和验证命令。目标是证明后端在没有本机 MySQL、Redis、Kafka、MinIO、OSS 的情况下也能启动，并且前端可以进入协作型 Agent 教学工作台。

## Backend Dev Startup

默认 profile 是 `dev`：

```bash
cd backend
mvn spring-boot:run
```

等价于：

```bash
SPRING_PROFILES_ACTIVE=dev mvn spring-boot:run
```

本项目默认后端端口是 `18090`：

```bash
SPRING_PROFILES_ACTIVE=dev SERVER_PORT=18090 mvn spring-boot:run
```

`dev` profile 默认使用：

- H2 in-memory database, MySQL compatibility mode
- memory cache
- local distributed lock
- Redis / Kafka / MinIO / OSS disabled

## Frontend Dev Startup

```bash
cd frontend
pnpm install --frozen-lockfile
pnpm dev --host 0.0.0.0 --port 13016
```

打开：

```text
http://localhost:13016/#/project-copilot/workbench
```

工作台覆盖项目空间、任务拆解、会议纪要、风险雷达、周报生成、行动项看板和协作历史。

## Probe Commands

推荐直接运行脚本：

```bash
./scripts/probe-backend-dev.sh
```

脚本会使用默认临时端口 `18180`，验证完成后自动停止后端进程。

用临时端口验证，避免和本地已有后端冲突：

```bash
cd backend
SPRING_PROFILES_ACTIVE=dev SERVER_PORT=18180 mvn spring-boot:run
```

另一个终端执行：

```bash
curl -fsS http://localhost:18180/api/test/health
curl -fsS http://localhost:18180/api/test/features
curl -fsS http://localhost:18180/api/project-copilot/overview
curl -fsS http://localhost:18180/v3/api-docs
```

期望关键字段：

```json
{
  "code": "0",
  "data": {
    "status": "UP",
    "activeProfiles": ["dev"]
  }
}
```

```json
{
  "code": "0",
  "data": {
    "status": "ready",
    "summary": {
      "byStatus": {
        "degraded": 0
      }
    }
  }
}
```

## Current Verification

2026-06-26 已验证：

- `SPRING_PROFILES_ACTIVE=dev SERVER_PORT=18180 mvn -q spring-boot:run` 能启动。
- `/api/test/health` 返回 `status=UP`、`activeProfiles=["dev"]`。
- `/api/test/features` 返回 `status=ready`，Database 为 `H2 (MySQL mode)`，Redis/Kafka/MinIO/OSS 为 `disabled`。
- `/api/project-copilot/overview` 返回项目空间、风险、行动项和团队节奏聚合数据。
- `/v3/api-docs` 返回 OpenAPI 3 JSON，并包含 `/api/auth/login`、`/api/auth/me`、`/api/test/health`、`/api/project-copilot/overview` 和平台请求头。
- `http://localhost:13016/#/project-copilot/workbench` 可进入 Project Copilot 工作台。
- `./scripts/probe-backend-dev.sh` 启动后会自动停止进程，避免留下后台服务。

## MySQL Integration

需要联调真实 MySQL 时，显式覆盖数据库变量：

```bash
DB_URL='jdbc:mysql://localhost:3306/agent_project_copilot?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true' \
DB_USERNAME=root \
DB_PASSWORD=your_password \
DB_DRIVER=com.mysql.cj.jdbc.Driver \
DB_DIALECT=org.hibernate.dialect.MySQL8Dialect \
mvn spring-boot:run
```
