/**
 * API path registry.
 *
 * Keep endpoint strings here so API modules and generated code reuse one contract.
 */

import { SERVICE_BOUNDARY_ROUTE_PATHS } from '@/contracts/service-boundaries'

const encodePathValue = (value: string | number): string => encodeURIComponent(String(value))

const bindApiPathParams = (
  apiPath: string,
  params: Record<string, string | number>
): string => {
  return Object.entries(params).reduce((path, [name, value]) => {
    return path.replace(`{${name}}`, encodePathValue(value))
  }, apiPath)
}

const normalizeApiBase = (baseUrl: string | undefined): string => {
  const base = (baseUrl || '').trim()
  if (!base || base === '/') return ''
  return base.replace(/\/+$/, '')
}

export const resolveApiPath = (apiPath: string): string => {
  const path = apiPath.startsWith('/') ? apiPath : `/${apiPath}`
  const base = normalizeApiBase(import.meta.env.VITE_API_URL)
  return base ? `${base}${path}` : path
}

export const ApiPaths = {
  auth: {
    login: SERVICE_BOUNDARY_ROUTE_PATHS.auth.login,
    logout: SERVICE_BOUNDARY_ROUTE_PATHS.auth.logout,
    me: SERVICE_BOUNDARY_ROUTE_PATHS.auth.me,
    refresh: SERVICE_BOUNDARY_ROUTE_PATHS.auth.refresh
  },
  test: {
    health: SERVICE_BOUNDARY_ROUTE_PATHS.test.health,
    features: SERVICE_BOUNDARY_ROUTE_PATHS.test.features,
    ping: SERVICE_BOUNDARY_ROUTE_PATHS.test.ping,
    bizException: SERVICE_BOUNDARY_ROUTE_PATHS.test.bizException,
    systemException: SERVICE_BOUNDARY_ROUTE_PATHS.test.systemException,
    items: SERVICE_BOUNDARY_ROUTE_PATHS.test.items,
    itemDetail: (id: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.test.itemDetail, { id })
  },
  projectCopilot: {
    overview: SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.overview,
    spaces: SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.spaces,
    spaceDetail: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.spaceDetail, { spaceId }),
    taskBreakdown: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.taskBreakdown, { spaceId }),
    generateTaskBreakdown: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.generateTaskBreakdown, { spaceId }),
    meetings: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.meetings, { spaceId }),
    risks: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.risks, { spaceId }),
    riskDetail: (spaceId: string | number, riskId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.riskDetail, { spaceId, riskId }),
    weeklyReports: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.weeklyReports, { spaceId }),
    generateWeeklyReport: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.generateWeeklyReport, { spaceId }),
    actionItems: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.actionItems, { spaceId }),
    actionItemDetail: (spaceId: string | number, actionItemId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.actionItemDetail, {
        spaceId,
        actionItemId
      }),
    collaborationHistory: (spaceId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.projectCopilot.collaborationHistory, { spaceId })
  },
  common: {
    upload: SERVICE_BOUNDARY_ROUTE_PATHS.common.upload,
    uploadImage: SERVICE_BOUNDARY_ROUTE_PATHS.common.uploadImage,
    uploadWangEditor: SERVICE_BOUNDARY_ROUTE_PATHS.common.uploadWangEditor,
    download: (fileId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.common.download, { fileId }),
    deleteFile: (fileId: string | number) =>
      bindApiPathParams(SERVICE_BOUNDARY_ROUTE_PATHS.common.deleteFile, { fileId })
  }
} as const

/**
 * Legacy template endpoints kept only for old mock/system pages.
 *
 * New runtime API code must use ApiPaths and the service-boundary manifest.
 */
export const ApiLegacyPaths = {
  auth: {
    verify: '/api/auth/verify',
    verify2FA: '/auth/login/verify-2fa',
    sendOtp: '/auth/otp/send',
    binding: '/auth/binding',
    bindStore: (storeNo: string) => `/auth/binding/${encodePathValue(storeNo)}`,
    tenantMembers: '/auth/tenant/account/list',
    userInfo: '/auth/user/info',
    register: '/auth/register',
    updatePassword: '/auth/user/password',
    userBasic: '/auth/user/basic',
    avatarUpload: '/auth/user/avatar'
  },
  system: {
    users: '/api/user/list',
    roles: '/api/role/list',
    simpleMenus: '/api/v3/system/menus/simple'
  }
} as const

export type ApiPaths = typeof ApiPaths
export type ApiLegacyPaths = typeof ApiLegacyPaths
