import { openApiRequest } from './openapiClient'
import type {
  ActionItem,
  CollaborationHistory,
  CreateMeetingMinutesParams,
  CreateProjectSpaceParams,
  GenerateTaskBreakdownParams,
  GenerateWeeklyReportParams,
  MeetingMinutes,
  ProjectOverview,
  ProjectSpace,
  ProjectSpaceList,
  RiskItem,
  TaskBreakdown,
  UpdateActionItemParams,
  UpdateRiskParams,
  WeeklyReport
} from './model/projectCopilotModel'

export function fetchProjectCopilotOverview(): Promise<ProjectOverview> {
  return openApiRequest('getProjectCopilotOverview')
}

export function fetchProjectSpaces(): Promise<ProjectSpaceList> {
  return openApiRequest('listProjectSpaces')
}

export function createProjectSpace(params: CreateProjectSpaceParams): Promise<ProjectSpace> {
  return openApiRequest('createProjectSpace', {
    body: params,
    showSuccessMessage: true
  })
}

export function fetchProjectSpace(spaceId: string): Promise<ProjectSpace> {
  return openApiRequest('getProjectSpace', {
    pathParams: { spaceId }
  })
}

export function fetchTaskBreakdown(spaceId: string): Promise<TaskBreakdown[]> {
  return openApiRequest('listTaskBreakdown', {
    pathParams: { spaceId }
  })
}

export function generateTaskBreakdown(
  spaceId: string,
  params: GenerateTaskBreakdownParams
): Promise<TaskBreakdown[]> {
  return openApiRequest('generateTaskBreakdown', {
    pathParams: { spaceId },
    body: params,
    showSuccessMessage: true
  })
}

export function fetchMeetingMinutes(spaceId: string): Promise<MeetingMinutes[]> {
  return openApiRequest('listMeetingMinutes', {
    pathParams: { spaceId }
  })
}

export function createMeetingMinutes(
  spaceId: string,
  params: CreateMeetingMinutesParams
): Promise<MeetingMinutes> {
  return openApiRequest('createMeetingMinutes', {
    pathParams: { spaceId },
    body: params,
    showSuccessMessage: true
  })
}

export function fetchRiskRadar(spaceId: string): Promise<RiskItem[]> {
  return openApiRequest('listRiskRadar', {
    pathParams: { spaceId }
  })
}

export function updateRiskRadarItem(
  spaceId: string,
  riskId: string,
  params: UpdateRiskParams
): Promise<RiskItem> {
  return openApiRequest('updateRiskRadarItem', {
    pathParams: { spaceId, riskId },
    body: params,
    showSuccessMessage: true
  })
}

export function fetchWeeklyReports(spaceId: string): Promise<WeeklyReport[]> {
  return openApiRequest('listWeeklyReports', {
    pathParams: { spaceId }
  })
}

export function generateWeeklyReport(
  spaceId: string,
  params: GenerateWeeklyReportParams
): Promise<WeeklyReport> {
  return openApiRequest('generateWeeklyReport', {
    pathParams: { spaceId },
    body: params,
    showSuccessMessage: true
  })
}

export function fetchActionItems(spaceId: string): Promise<ActionItem[]> {
  return openApiRequest('listActionItems', {
    pathParams: { spaceId }
  })
}

export function updateActionItem(
  spaceId: string,
  actionItemId: string,
  params: UpdateActionItemParams
): Promise<ActionItem> {
  return openApiRequest('updateActionItem', {
    pathParams: { spaceId, actionItemId },
    body: params,
    showSuccessMessage: true
  })
}

export function fetchCollaborationHistory(spaceId: string): Promise<CollaborationHistory[]> {
  return openApiRequest('listCollaborationHistory', {
    pathParams: { spaceId }
  })
}
