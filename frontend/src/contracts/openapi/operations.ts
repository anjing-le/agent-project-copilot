/* eslint-disable */
// Generated from OpenAPI JSON. Do not edit manually.
// Run: node scripts/generate-openapi-frontend-types.js <openapi-json-file>

import type * as Schemas from './schemas'

export type OpenApiHttpMethod = 'DELETE' | 'GET' | 'PATCH' | 'POST' | 'PUT'

export interface OpenApiOperationMeta {
  method: OpenApiHttpMethod
  path: string
  operationId: string
}

export const OPENAPI_OPERATIONS = {
  createItem: {
    method: "POST",
    path: "/api/test/items",
    operationId: "createItem"
  },
  createMeetingMinutes: {
    method: "POST",
    path: "/api/project-copilot/spaces/{spaceId}/meetings",
    operationId: "createMeetingMinutes"
  },
  createProjectSpace: {
    method: "POST",
    path: "/api/project-copilot/spaces",
    operationId: "createProjectSpace"
  },
  deleteItem: {
    method: "DELETE",
    path: "/api/test/items/{id}",
    operationId: "deleteItem"
  },
  features: {
    method: "GET",
    path: "/api/test/features",
    operationId: "features"
  },
  generateTaskBreakdown: {
    method: "POST",
    path: "/api/project-copilot/spaces/{spaceId}/task-breakdown/generate",
    operationId: "generateTaskBreakdown"
  },
  generateWeeklyReport: {
    method: "POST",
    path: "/api/project-copilot/spaces/{spaceId}/weekly-reports/generate",
    operationId: "generateWeeklyReport"
  },
  getCurrentUser: {
    method: "GET",
    path: "/api/auth/me",
    operationId: "getCurrentUser"
  },
  getItem: {
    method: "GET",
    path: "/api/test/items/{id}",
    operationId: "getItem"
  },
  getProjectCopilotOverview: {
    method: "GET",
    path: "/api/project-copilot/overview",
    operationId: "getProjectCopilotOverview"
  },
  getProjectSpace: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}",
    operationId: "getProjectSpace"
  },
  health: {
    method: "GET",
    path: "/api/test/health",
    operationId: "health"
  },
  listActionItems: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}/action-items",
    operationId: "listActionItems"
  },
  listCollaborationHistory: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}/collaboration-history",
    operationId: "listCollaborationHistory"
  },
  listItems: {
    method: "GET",
    path: "/api/test/items",
    operationId: "listItems"
  },
  listMeetingMinutes: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}/meetings",
    operationId: "listMeetingMinutes"
  },
  listProjectSpaces: {
    method: "GET",
    path: "/api/project-copilot/spaces",
    operationId: "listProjectSpaces"
  },
  listRiskRadar: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}/risks",
    operationId: "listRiskRadar"
  },
  listTaskBreakdown: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}/task-breakdown",
    operationId: "listTaskBreakdown"
  },
  listWeeklyReports: {
    method: "GET",
    path: "/api/project-copilot/spaces/{spaceId}/weekly-reports",
    operationId: "listWeeklyReports"
  },
  login: {
    method: "POST",
    path: "/api/auth/login",
    operationId: "login"
  },
  logout: {
    method: "POST",
    path: "/api/auth/logout",
    operationId: "logout"
  },
  ping: {
    method: "GET",
    path: "/api/test/ping",
    operationId: "ping"
  },
  refreshToken: {
    method: "POST",
    path: "/api/auth/refresh",
    operationId: "refreshToken"
  },
  testBizException: {
    method: "GET",
    path: "/api/test/exception/biz",
    operationId: "testBizException"
  },
  testSystemException: {
    method: "GET",
    path: "/api/test/exception/system",
    operationId: "testSystemException"
  },
  updateActionItem: {
    method: "PATCH",
    path: "/api/project-copilot/spaces/{spaceId}/action-items/{actionItemId}",
    operationId: "updateActionItem"
  },
  updateItem: {
    method: "PUT",
    path: "/api/test/items/{id}",
    operationId: "updateItem"
  },
  updateRiskRadarItem: {
    method: "PATCH",
    path: "/api/project-copilot/spaces/{spaceId}/risks/{riskId}",
    operationId: "updateRiskRadarItem"
  },
} as const satisfies Record<string, OpenApiOperationMeta>

export type OpenApiOperationId = keyof typeof OPENAPI_OPERATIONS

export interface OpenApiOperationTypes {
  createItem: {
    pathParams: undefined
    query: undefined
    request: Record<string, unknown>
    response: Schemas.APIResponseMapStringObject
    data: NonNullable<Schemas.APIResponseMapStringObject['data']>
  }
  createMeetingMinutes: {
    pathParams: { spaceId: string }
    query: undefined
    request: Schemas.MeetingMinutesCreateRequest
    response: Schemas.APIResponseMeetingMinutesResponse
    data: NonNullable<Schemas.APIResponseMeetingMinutesResponse['data']>
  }
  createProjectSpace: {
    pathParams: undefined
    query: undefined
    request: Schemas.ProjectSpaceCreateRequest
    response: Schemas.APIResponseProjectSpaceResponse
    data: NonNullable<Schemas.APIResponseProjectSpaceResponse['data']>
  }
  deleteItem: {
    pathParams: { id: number }
    query: undefined
    request: undefined
    response: Schemas.APIResponseVoid
    data: NonNullable<Schemas.APIResponseVoid['data']>
  }
  features: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseMiddlewareStatusReport
    data: NonNullable<Schemas.APIResponseMiddlewareStatusReport['data']>
  }
  generateTaskBreakdown: {
    pathParams: { spaceId: string }
    query: undefined
    request: Schemas.TaskBreakdownGenerateRequest
    response: Schemas.APIResponseListTaskBreakdownResponse
    data: NonNullable<Schemas.APIResponseListTaskBreakdownResponse['data']>
  }
  generateWeeklyReport: {
    pathParams: { spaceId: string }
    query: undefined
    request: Schemas.WeeklyReportGenerateRequest
    response: Schemas.APIResponseWeeklyReportResponse
    data: NonNullable<Schemas.APIResponseWeeklyReportResponse['data']>
  }
  getCurrentUser: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseCurrentUserResponse
    data: NonNullable<Schemas.APIResponseCurrentUserResponse['data']>
  }
  getItem: {
    pathParams: { id: number }
    query: undefined
    request: undefined
    response: Schemas.APIResponseMapStringObject
    data: NonNullable<Schemas.APIResponseMapStringObject['data']>
  }
  getProjectCopilotOverview: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseProjectOverviewResponse
    data: NonNullable<Schemas.APIResponseProjectOverviewResponse['data']>
  }
  getProjectSpace: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseProjectSpaceResponse
    data: NonNullable<Schemas.APIResponseProjectSpaceResponse['data']>
  }
  health: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseMapStringObject
    data: NonNullable<Schemas.APIResponseMapStringObject['data']>
  }
  listActionItems: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseListActionItemResponse
    data: NonNullable<Schemas.APIResponseListActionItemResponse['data']>
  }
  listCollaborationHistory: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseListCollaborationHistoryResponse
    data: NonNullable<Schemas.APIResponseListCollaborationHistoryResponse['data']>
  }
  listItems: {
    pathParams: undefined
    query: { keyword?: string }
    request: undefined
    response: Schemas.APIResponseMapStringObject
    data: NonNullable<Schemas.APIResponseMapStringObject['data']>
  }
  listMeetingMinutes: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseListMeetingMinutesResponse
    data: NonNullable<Schemas.APIResponseListMeetingMinutesResponse['data']>
  }
  listProjectSpaces: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseListProjectSpaceResponse
    data: NonNullable<Schemas.APIResponseListProjectSpaceResponse['data']>
  }
  listRiskRadar: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseListRiskItemResponse
    data: NonNullable<Schemas.APIResponseListRiskItemResponse['data']>
  }
  listTaskBreakdown: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseListTaskBreakdownResponse
    data: NonNullable<Schemas.APIResponseListTaskBreakdownResponse['data']>
  }
  listWeeklyReports: {
    pathParams: { spaceId: string }
    query: undefined
    request: undefined
    response: Schemas.APIResponseListWeeklyReportResponse
    data: NonNullable<Schemas.APIResponseListWeeklyReportResponse['data']>
  }
  login: {
    pathParams: undefined
    query: undefined
    request: Schemas.LoginRequest
    response: Schemas.APIResponseAuthTokenResponse
    data: NonNullable<Schemas.APIResponseAuthTokenResponse['data']>
  }
  logout: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseVoid
    data: NonNullable<Schemas.APIResponseVoid['data']>
  }
  ping: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseString
    data: NonNullable<Schemas.APIResponseString['data']>
  }
  refreshToken: {
    pathParams: undefined
    query: undefined
    request: Schemas.RefreshTokenRequest
    response: Schemas.APIResponseAuthTokenResponse
    data: NonNullable<Schemas.APIResponseAuthTokenResponse['data']>
  }
  testBizException: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseVoid
    data: NonNullable<Schemas.APIResponseVoid['data']>
  }
  testSystemException: {
    pathParams: undefined
    query: undefined
    request: undefined
    response: Schemas.APIResponseVoid
    data: NonNullable<Schemas.APIResponseVoid['data']>
  }
  updateActionItem: {
    pathParams: { actionItemId: string; spaceId: string }
    query: undefined
    request: Schemas.ActionItemUpdateRequest
    response: Schemas.APIResponseActionItemResponse
    data: NonNullable<Schemas.APIResponseActionItemResponse['data']>
  }
  updateItem: {
    pathParams: { id: number }
    query: undefined
    request: Record<string, unknown>
    response: Schemas.APIResponseMapStringObject
    data: NonNullable<Schemas.APIResponseMapStringObject['data']>
  }
  updateRiskRadarItem: {
    pathParams: { riskId: string; spaceId: string }
    query: undefined
    request: Schemas.RiskUpdateRequest
    response: Schemas.APIResponseRiskItemResponse
    data: NonNullable<Schemas.APIResponseRiskItemResponse['data']>
  }
}

export type OpenApiOperationPathParams<T extends OpenApiOperationId> = OpenApiOperationTypes[T]['pathParams']
export type OpenApiOperationQuery<T extends OpenApiOperationId> = OpenApiOperationTypes[T]['query']
export type OpenApiOperationRequest<T extends OpenApiOperationId> = OpenApiOperationTypes[T]['request']
export type OpenApiOperationResponse<T extends OpenApiOperationId> = OpenApiOperationTypes[T]['response']
export type OpenApiOperationData<T extends OpenApiOperationId> = OpenApiOperationTypes[T]['data']
