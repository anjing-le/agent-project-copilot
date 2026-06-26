/* eslint-disable */
// Generated from OpenAPI JSON. Do not edit manually.
// Run: node scripts/generate-openapi-frontend-types.js <openapi-json-file>

export type JsonObject = Record<string, unknown>

/**
 * 行动项
 */
export interface ActionItemResponse {
  /**
   * 行动项 ID
   */
  actionItemId?: string
  /**
   * 协作边界
   */
  boundary?: string
  /**
   * 到期日期
   */
  dueDate?: string
  /**
   * 负责人
   */
  owner?: string
  /**
   * 优先级
   */
  priority?: string
  /**
   * 提醒时间
   */
  reminderAt?: string
  /**
   * 来源 ID
   */
  sourceId?: string
  /**
   * 来源类型
   */
  sourceType?: string
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 状态
   */
  status?: string
  /**
   * 标题
   */
  title?: string
  /**
   * 更新时间
   */
  updatedAt?: string
}

/**
 * 行动项更新请求
 */
export interface ActionItemUpdateRequest {
  /**
   * 到期日期
   */
  dueDate?: string
  /**
   * 负责人
   */
  owner?: string
  /**
   * 提醒时间
   */
  reminderAt?: string
  /**
   * 行动项状态
   */
  status: string
}

export interface APIResponseActionItemResponse {
  code?: string
  data?: ActionItemResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseAuthTokenResponse {
  code?: string
  data?: AuthTokenResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseCurrentUserResponse {
  code?: string
  data?: CurrentUserResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListActionItemResponse {
  code?: string
  data?: ActionItemResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListCollaborationHistoryResponse {
  code?: string
  data?: CollaborationHistoryResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListMeetingMinutesResponse {
  code?: string
  data?: MeetingMinutesResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListProjectSpaceResponse {
  code?: string
  data?: ProjectSpaceResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListRiskItemResponse {
  code?: string
  data?: RiskItemResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListTaskBreakdownResponse {
  code?: string
  data?: TaskBreakdownResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseListWeeklyReportResponse {
  code?: string
  data?: WeeklyReportResponse[]
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseMapStringObject {
  code?: string
  data?: Record<string, unknown>
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseMeetingMinutesResponse {
  code?: string
  data?: MeetingMinutesResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseMiddlewareStatusReport {
  code?: string
  data?: MiddlewareStatusReport
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseProjectOverviewResponse {
  code?: string
  data?: ProjectOverviewResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseProjectSpaceResponse {
  code?: string
  data?: ProjectSpaceResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseRiskItemResponse {
  code?: string
  data?: RiskItemResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseString {
  code?: string
  data?: string
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseVoid {
  code?: string
  data?: unknown
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

export interface APIResponseWeeklyReportResponse {
  code?: string
  data?: WeeklyReportResponse
  message?: string
  requestId?: string
  success?: boolean
  timestamp?: number
}

/**
 * Authentication token payload
 */
export interface AuthTokenResponse {
  /**
   * Access token used in Authorization header
   */
  accessToken: string
  /**
   * Access token lifetime in seconds
   */
  expiresIn: number
  /**
   * Refresh token used to renew access token
   */
  refreshToken: string
  /**
   * Token type
   */
  tokenType: string
}

/**
 * 协作历史
 */
export interface CollaborationHistoryResponse {
  /**
   * 操作者
   */
  actor?: string
  /**
   * 事件 ID
   */
  eventId?: string
  /**
   * 事件类型
   */
  eventType?: string
  /**
   * 发生时间
   */
  occurredAt?: string
  /**
   * 关联对象 ID
   */
  relatedItemId?: string
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 摘要
   */
  summary?: string
  /**
   * 标题
   */
  title?: string
}

/**
 * Current authenticated user payload
 */
export interface CurrentUserResponse {
  /**
   * Avatar URL
   */
  avatar?: string
  /**
   * User creation time in ISO-8601 UTC format
   */
  createTime?: string
  /**
   * User email
   */
  email?: string
  /**
   * Display nickname
   */
  nickName?: string
  /**
   * Permission codes
   */
  permissions?: string[]
  /**
   * Role codes
   */
  roles: string[]
  /**
   * User id
   */
  userId: number
  /**
   * Login username
   */
  userName: string
}

/**
 * Login request
 */
export interface LoginRequest {
  /**
   * Captcha code when enabled
   */
  captcha?: string
  /**
   * Password
   */
  password: string
  /**
   * Whether to keep the session longer
   */
  rememberMe?: boolean
  /**
   * Username or email
   */
  username: string
}

/**
 * 会议纪要创建请求
 */
export interface MeetingMinutesCreateRequest {
  /**
   * 阻塞点
   */
  blockers?: string[]
  /**
   * 已确认决策
   */
  decisions?: string[]
  /**
   * 主持人
   */
  facilitator?: string
  /**
   * 会议时间
   */
  heldAt?: string
  /**
   * 会议类型
   */
  meetingType?: string
  /**
   * 参会人
   */
  participants: string[]
  /**
   * 会议标题
   */
  title: string
  /**
   * 会议原文或摘要输入
   */
  transcript: string
}

/**
 * 会议纪要
 */
export interface MeetingMinutesResponse {
  /**
   * 阻塞点
   */
  blockers?: string[]
  /**
   * 创建时间
   */
  createdAt?: string
  /**
   * 决策
   */
  decisions?: string[]
  /**
   * 主持人
   */
  facilitator?: string
  /**
   * 会议时间
   */
  heldAt?: string
  /**
   * 会议 ID
   */
  meetingId?: string
  /**
   * 会议类型
   */
  meetingType?: string
  /**
   * 参会人
   */
  participants?: string[]
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 摘要
   */
  summary?: string
  /**
   * 标题
   */
  title?: string
}

export interface MiddlewareInfo {
  details?: string
  enabled?: boolean
  name?: string
  status?: "disabled" | "configured" | "ready" | "degraded"
  statusCode?: string
  statusDescription?: string
  version?: string
}

export interface MiddlewareStatusReport {
  features?: MiddlewareInfo[]
  status?: "disabled" | "configured" | "ready" | "degraded"
  statusCode?: string
  statusDescription?: string
  summary?: MiddlewareSummary
}

export interface MiddlewareSummary {
  byStatus?: Record<string, number>
  enabled?: number
  total?: number
}

/**
 * 项目协作指标
 */
export interface ProjectMetricResponse {
  /**
   * 指标键
   */
  key?: string
  /**
   * 指标名称
   */
  label?: string
  /**
   * 展示语义
   */
  tone?: string
  /**
   * 趋势
   */
  trend?: string
  /**
   * 指标值
   */
  value?: string
}

/**
 * 项目协作总览
 */
export interface ProjectOverviewResponse {
  /**
   * 活跃任务数
   */
  activeTasks?: number
  /**
   * 当前周
   */
  currentWeekLabel?: string
  /**
   * 高风险数量
   */
  highRiskCount?: number
  /**
   * 协作指标
   */
  metrics?: ProjectMetricResponse[]
  /**
   * 逾期行动项数
   */
  overdueActionItems?: number
  /**
   * 项目空间列表
   */
  spaces?: ProjectSpaceResponse[]
  /**
   * 项目空间总数
   */
  totalSpaces?: number
  /**
   * 周报是否已就绪
   */
  weeklyReportReady?: boolean
}

/**
 * 项目空间创建请求
 */
export interface ProjectSpaceCreateRequest {
  /**
   * 协作边界
   */
  collaborationBoundaries?: string[]
  /**
   * 协作者
   */
  collaborators?: string[]
  /**
   * 交付目标
   */
  deliveryGoal: string
  /**
   * 项目空间名称
   */
  name: string
  /**
   * 空间负责人
   */
  owner: string
  /**
   * 交付阶段
   */
  stage?: string
}

/**
 * 项目空间
 */
export interface ProjectSpaceResponse {
  /**
   * 活跃风险数量
   */
  activeRiskCount?: number
  /**
   * 团队协作边界
   */
  collaborationBoundaries?: string[]
  /**
   * 协作者数量
   */
  collaboratorCount?: number
  /**
   * 交付目标
   */
  deliveryGoal?: string
  /**
   * 健康状态
   */
  healthStatus?: string
  /**
   * 空间名称
   */
  name?: string
  /**
   * 下一检查点
   */
  nextCheckpointAt?: string
  /**
   * 打开的行动项数量
   */
  openActionItemCount?: number
  /**
   * 负责人
   */
  owner?: string
  /**
   * 进度百分比
   */
  progressPercent?: number
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 迭代标签
   */
  sprintLabel?: string
  /**
   * 交付阶段
   */
  stage?: string
  /**
   * 更新时间
   */
  updatedAt?: string
}

/**
 * Refresh token request
 */
export interface RefreshTokenRequest {
  /**
   * Refresh token returned by login
   */
  refreshToken: string
}

/**
 * 风险雷达项
 */
export interface RiskItemResponse {
  /**
   * 分类
   */
  category?: string
  /**
   * 影响程度
   */
  impact?: number
  /**
   * 缓解方案
   */
  mitigationPlan?: string
  /**
   * 下次复盘日期
   */
  nextReviewDate?: string
  /**
   * 负责人
   */
  owner?: string
  /**
   * 发生概率
   */
  probability?: number
  /**
   * 风险 ID
   */
  riskId?: string
  /**
   * 风险分
   */
  score?: number
  /**
   * 严重级别
   */
  severity?: string
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 状态
   */
  status?: string
  /**
   * 标题
   */
  title?: string
  /**
   * 触发信号
   */
  triggerSignal?: string
  /**
   * 更新时间
   */
  updatedAt?: string
}

/**
 * 风险项更新请求
 */
export interface RiskUpdateRequest {
  /**
   * 缓解方案
   */
  mitigationPlan?: string
  /**
   * 下次复盘日期
   */
  nextReviewDate?: string
  /**
   * 负责人
   */
  owner?: string
  /**
   * 风险状态
   */
  status: string
}

/**
 * 任务拆解生成请求
 */
export interface TaskBreakdownGenerateRequest {
  /**
   * 交付截止日期
   */
  dueDate?: string
  /**
   * 拆解粒度
   */
  granularity?: string
  /**
   * 是否同步扫描风险
   */
  includeRiskScan?: boolean
  /**
   * 拆解目标
   */
  objective: string
  /**
   * 角色边界
   */
  roleBoundaries?: string[]
}

/**
 * 任务拆解项
 */
export interface TaskBreakdownResponse {
  /**
   * 验收标准
   */
  acceptanceCriteria?: string
  /**
   * 协作边界
   */
  collaborationBoundary?: string
  /**
   * 依赖任务 ID
   */
  dependencyTaskIds?: string[]
  /**
   * 说明
   */
  description?: string
  /**
   * 截止日期
   */
  dueDate?: string
  /**
   * 预估工时
   */
  estimateHours?: number
  /**
   * 父任务 ID
   */
  parentTaskId?: string
  /**
   * 优先级
   */
  priority?: string
  /**
   * 进度百分比
   */
  progressPercent?: number
  /**
   * 角色负责人
   */
  roleOwner?: string
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 状态
   */
  status?: string
  /**
   * 任务 ID
   */
  taskId?: string
  /**
   * 标题
   */
  title?: string
  /**
   * 更新时间
   */
  updatedAt?: string
}

/**
 * 周报生成请求
 */
export interface WeeklyReportGenerateRequest {
  /**
   * 生成者
   */
  generatedBy?: string
  /**
   * 是否纳入行动项
   */
  includeActionItems?: boolean
  /**
   * 是否纳入风险
   */
  includeRisks?: boolean
  /**
   * 周报周期
   */
  weekLabel: string
}

/**
 * 项目周报
 */
export interface WeeklyReportResponse {
  /**
   * 已完成事项
   */
  completedItems?: string[]
  /**
   * 管理摘要
   */
  executiveSummary?: string
  /**
   * 生成时间
   */
  generatedAt?: string
  /**
   * 生成者
   */
  generatedBy?: string
  /**
   * 需要协助
   */
  helpNeeded?: string[]
  /**
   * 下周重点
   */
  nextWeekFocus?: string[]
  /**
   * 计划摘要
   */
  planSummary?: string
  /**
   * 进度摘要
   */
  progressSummary?: string
  /**
   * 周报 ID
   */
  reportId?: string
  /**
   * 风险摘要
   */
  riskSummary?: string
  /**
   * 项目空间 ID
   */
  spaceId?: string
  /**
   * 周标签
   */
  weekLabel?: string
}

export interface OpenApiSchemas {
  ActionItemResponse: ActionItemResponse
  ActionItemUpdateRequest: ActionItemUpdateRequest
  APIResponseActionItemResponse: APIResponseActionItemResponse
  APIResponseAuthTokenResponse: APIResponseAuthTokenResponse
  APIResponseCurrentUserResponse: APIResponseCurrentUserResponse
  APIResponseListActionItemResponse: APIResponseListActionItemResponse
  APIResponseListCollaborationHistoryResponse: APIResponseListCollaborationHistoryResponse
  APIResponseListMeetingMinutesResponse: APIResponseListMeetingMinutesResponse
  APIResponseListProjectSpaceResponse: APIResponseListProjectSpaceResponse
  APIResponseListRiskItemResponse: APIResponseListRiskItemResponse
  APIResponseListTaskBreakdownResponse: APIResponseListTaskBreakdownResponse
  APIResponseListWeeklyReportResponse: APIResponseListWeeklyReportResponse
  APIResponseMapStringObject: APIResponseMapStringObject
  APIResponseMeetingMinutesResponse: APIResponseMeetingMinutesResponse
  APIResponseMiddlewareStatusReport: APIResponseMiddlewareStatusReport
  APIResponseProjectOverviewResponse: APIResponseProjectOverviewResponse
  APIResponseProjectSpaceResponse: APIResponseProjectSpaceResponse
  APIResponseRiskItemResponse: APIResponseRiskItemResponse
  APIResponseString: APIResponseString
  APIResponseVoid: APIResponseVoid
  APIResponseWeeklyReportResponse: APIResponseWeeklyReportResponse
  AuthTokenResponse: AuthTokenResponse
  CollaborationHistoryResponse: CollaborationHistoryResponse
  CurrentUserResponse: CurrentUserResponse
  LoginRequest: LoginRequest
  MeetingMinutesCreateRequest: MeetingMinutesCreateRequest
  MeetingMinutesResponse: MeetingMinutesResponse
  MiddlewareInfo: MiddlewareInfo
  MiddlewareStatusReport: MiddlewareStatusReport
  MiddlewareSummary: MiddlewareSummary
  ProjectMetricResponse: ProjectMetricResponse
  ProjectOverviewResponse: ProjectOverviewResponse
  ProjectSpaceCreateRequest: ProjectSpaceCreateRequest
  ProjectSpaceResponse: ProjectSpaceResponse
  RefreshTokenRequest: RefreshTokenRequest
  RiskItemResponse: RiskItemResponse
  RiskUpdateRequest: RiskUpdateRequest
  TaskBreakdownGenerateRequest: TaskBreakdownGenerateRequest
  TaskBreakdownResponse: TaskBreakdownResponse
  WeeklyReportGenerateRequest: WeeklyReportGenerateRequest
  WeeklyReportResponse: WeeklyReportResponse
}
