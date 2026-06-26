import type { OpenApiOperationData, OpenApiOperationRequest } from '@/contracts/openapi/operations'

export type ProjectOverview = OpenApiOperationData<'getProjectCopilotOverview'>
export type ProjectMetric = NonNullable<ProjectOverview['metrics']>[number]

export type ProjectSpace = OpenApiOperationData<'getProjectSpace'>
export type ProjectSpaceList = OpenApiOperationData<'listProjectSpaces'>

export type TaskBreakdown = OpenApiOperationData<'listTaskBreakdown'>[number]
export type MeetingMinutes = OpenApiOperationData<'listMeetingMinutes'>[number]
export type RiskItem = OpenApiOperationData<'listRiskRadar'>[number]
export type WeeklyReport = OpenApiOperationData<'listWeeklyReports'>[number]
export type ActionItem = OpenApiOperationData<'listActionItems'>[number]
export type CollaborationHistory = OpenApiOperationData<'listCollaborationHistory'>[number]

export type CreateProjectSpaceParams = OpenApiOperationRequest<'createProjectSpace'>
export type GenerateTaskBreakdownParams = OpenApiOperationRequest<'generateTaskBreakdown'>
export type CreateMeetingMinutesParams = OpenApiOperationRequest<'createMeetingMinutes'>
export type UpdateRiskParams = OpenApiOperationRequest<'updateRiskRadarItem'>
export type GenerateWeeklyReportParams = OpenApiOperationRequest<'generateWeeklyReport'>
export type UpdateActionItemParams = OpenApiOperationRequest<'updateActionItem'>
