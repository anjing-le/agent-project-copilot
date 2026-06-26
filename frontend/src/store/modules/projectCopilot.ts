import { computed, ref } from 'vue'
import { defineStore } from 'pinia'
import {
  createMeetingMinutes as createMeetingMinutesApi,
  createProjectSpace as createProjectSpaceApi,
  fetchActionItems,
  fetchCollaborationHistory,
  fetchMeetingMinutes,
  fetchProjectCopilotOverview,
  fetchProjectSpace,
  fetchProjectSpaces,
  fetchRiskRadar,
  fetchTaskBreakdown,
  fetchWeeklyReports,
  generateTaskBreakdown as generateTaskBreakdownApi,
  generateWeeklyReport as generateWeeklyReportApi,
  updateActionItem as updateActionItemApi,
  updateRiskRadarItem
} from '@/api/project-copilot'
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
  RiskItem,
  TaskBreakdown,
  UpdateActionItemParams,
  UpdateRiskParams,
  WeeklyReport
} from '@/api/model/projectCopilotModel'

export const useProjectCopilotStore = defineStore('projectCopilotStore', () => {
  const overview = ref<ProjectOverview>()
  const spaces = ref<ProjectSpace[]>([])
  const selectedSpaceId = ref('')
  const selectedSpace = ref<ProjectSpace>()
  const tasks = ref<TaskBreakdown[]>([])
  const meetings = ref<MeetingMinutes[]>([])
  const risks = ref<RiskItem[]>([])
  const weeklyReports = ref<WeeklyReport[]>([])
  const actionItems = ref<ActionItem[]>([])
  const history = ref<CollaborationHistory[]>([])
  const loading = ref(false)
  const mutating = ref(false)
  const activeTab = ref('tasks')

  const currentSpace = computed(() => {
    return selectedSpace.value || spaces.value.find((space) => space.spaceId === selectedSpaceId.value)
  })

  const projectMetrics = computed(() => overview.value?.metrics || [])

  const orderedRisks = computed(() => {
    return [...risks.value].sort((left, right) => (right.score || 0) - (left.score || 0))
  })

  const openActionItems = computed(() => {
    return actionItems.value.filter((item) => item.status !== 'done' && item.status !== 'closed')
  })

  const latestWeeklyReport = computed(() => {
    return [...weeklyReports.value].sort((left, right) => {
      return String(right.generatedAt || '').localeCompare(String(left.generatedAt || ''))
    })[0]
  })

  const syncOverview = async () => {
    const [overviewData, spaceList] = await Promise.all([
      fetchProjectCopilotOverview(),
      fetchProjectSpaces()
    ])
    overview.value = overviewData
    spaces.value = spaceList || overviewData.spaces || []

    if (!selectedSpaceId.value && spaces.value[0]?.spaceId) {
      selectedSpaceId.value = spaces.value[0].spaceId
    }
  }

  const loadSpaceArtifacts = async (spaceId = selectedSpaceId.value) => {
    if (!spaceId) return
    selectedSpaceId.value = spaceId

    const [
      space,
      taskList,
      meetingList,
      riskList,
      reportList,
      actionItemList,
      historyList
    ] = await Promise.all([
      fetchProjectSpace(spaceId),
      fetchTaskBreakdown(spaceId),
      fetchMeetingMinutes(spaceId),
      fetchRiskRadar(spaceId),
      fetchWeeklyReports(spaceId),
      fetchActionItems(spaceId),
      fetchCollaborationHistory(spaceId)
    ])

    selectedSpace.value = space
    tasks.value = taskList || []
    meetings.value = meetingList || []
    risks.value = riskList || []
    weeklyReports.value = reportList || []
    actionItems.value = actionItemList || []
    history.value = historyList || []
  }

  const loadOverview = async () => {
    loading.value = true
    try {
      await syncOverview()
      await loadSpaceArtifacts()
    } finally {
      loading.value = false
    }
  }

  const selectSpace = async (spaceId: string) => {
    loading.value = true
    try {
      await loadSpaceArtifacts(spaceId)
    } finally {
      loading.value = false
    }
  }

  const createProjectSpace = async (params: CreateProjectSpaceParams) => {
    mutating.value = true
    try {
      const space = await createProjectSpaceApi(params)
      selectedSpaceId.value = space.spaceId || ''
      await syncOverview()
      await loadSpaceArtifacts(selectedSpaceId.value)
      return space
    } finally {
      mutating.value = false
    }
  }

  const generateTaskBreakdown = async (params: GenerateTaskBreakdownParams) => {
    if (!selectedSpaceId.value) return []
    mutating.value = true
    try {
      const generated = await generateTaskBreakdownApi(selectedSpaceId.value, params)
      tasks.value = generated || []
      await syncOverview()
      await loadSpaceArtifacts(selectedSpaceId.value)
      return generated
    } finally {
      mutating.value = false
    }
  }

  const createMeetingMinutes = async (params: CreateMeetingMinutesParams) => {
    if (!selectedSpaceId.value) return undefined
    mutating.value = true
    try {
      const meeting = await createMeetingMinutesApi(selectedSpaceId.value, params)
      await loadSpaceArtifacts(selectedSpaceId.value)
      return meeting
    } finally {
      mutating.value = false
    }
  }

  const updateRisk = async (riskId: string, params: UpdateRiskParams) => {
    if (!selectedSpaceId.value) return undefined
    mutating.value = true
    try {
      const updatedRisk = await updateRiskRadarItem(selectedSpaceId.value, riskId, params)
      await syncOverview()
      await loadSpaceArtifacts(selectedSpaceId.value)
      return updatedRisk
    } finally {
      mutating.value = false
    }
  }

  const generateWeeklyReport = async (params: GenerateWeeklyReportParams) => {
    if (!selectedSpaceId.value) return undefined
    mutating.value = true
    try {
      const report = await generateWeeklyReportApi(selectedSpaceId.value, params)
      await loadSpaceArtifacts(selectedSpaceId.value)
      return report
    } finally {
      mutating.value = false
    }
  }

  const updateActionItem = async (actionItemId: string, params: UpdateActionItemParams) => {
    if (!selectedSpaceId.value) return undefined
    mutating.value = true
    try {
      const item = await updateActionItemApi(selectedSpaceId.value, actionItemId, params)
      await syncOverview()
      await loadSpaceArtifacts(selectedSpaceId.value)
      return item
    } finally {
      mutating.value = false
    }
  }

  return {
    overview,
    spaces,
    selectedSpaceId,
    selectedSpace,
    tasks,
    meetings,
    risks,
    weeklyReports,
    actionItems,
    history,
    loading,
    mutating,
    activeTab,
    currentSpace,
    projectMetrics,
    orderedRisks,
    openActionItems,
    latestWeeklyReport,
    loadOverview,
    loadSpaceArtifacts,
    selectSpace,
    createProjectSpace,
    generateTaskBreakdown,
    createMeetingMinutes,
    updateRisk,
    generateWeeklyReport,
    updateActionItem
  }
})
