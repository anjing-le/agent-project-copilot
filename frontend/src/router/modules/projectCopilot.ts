import { AppRouteRecord } from '@/types/router'

export const projectCopilotRoutes: AppRouteRecord = {
  name: 'ProjectCopilot',
  path: '/project-copilot',
  component: '/index/index',
  meta: {
    title: '项目协作 Agent',
    icon: 'ri:mind-map',
    roles: ['R_SUPER', 'R_ADMIN', 'R_USER', 'R_GUEST']
  },
  children: [
    {
      path: 'workbench',
      name: 'ProjectCopilotWorkbench',
      component: '/project-copilot',
      meta: {
        title: '协作工作台',
        keepAlive: true,
        fixedTab: true,
        roles: ['R_SUPER', 'R_ADMIN', 'R_USER', 'R_GUEST']
      }
    }
  ]
}
