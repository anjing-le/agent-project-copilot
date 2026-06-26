import { AppRouteRecord } from '@/types/router'
import { projectCopilotRoutes } from './projectCopilot'
import { dashboardRoutes } from './dashboard'
import { systemRoutes } from './system'
import { resultRoutes } from './result'
import { exceptionRoutes } from './exception'
import { themeRoutes } from './theme'

/**
 * 导出所有模块化路由
 */
export const routeModules: AppRouteRecord[] = [
  projectCopilotRoutes,
  dashboardRoutes,
  systemRoutes,
  resultRoutes,
  exceptionRoutes,
  themeRoutes
]
