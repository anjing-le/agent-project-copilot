<template>
  <div class="project-copilot-page" v-loading="loading">
    <header class="workbench-header">
      <div>
        <p class="eyebrow">Project Copilot</p>
        <h1>项目交付协作工作台</h1>
        <p class="subtitle">
          {{ currentSpace?.name || '加载项目空间中' }} ·
          {{ currentSpace?.stage || '等待阶段信息' }}
        </p>
      </div>
      <div class="header-actions">
        <el-button :icon="Refresh" @click="handleRefresh">刷新</el-button>
        <el-button type="primary" :icon="Plus" @click="showCreateSpaceDialog = true">
          项目空间
        </el-button>
      </div>
    </header>

    <section class="metric-strip">
      <article v-for="metric in metricCards" :key="metric.key" class="metric-item">
        <span>{{ metric.label }}</span>
        <strong>{{ metric.value }}</strong>
        <small>{{ metric.trend }}</small>
      </article>
    </section>

    <div class="workspace-grid">
      <aside class="space-panel">
        <div class="panel-title-row">
          <div>
            <p class="section-kicker">项目空间</p>
            <h2>协作上下文</h2>
          </div>
          <el-button circle :icon="Plus" @click="showCreateSpaceDialog = true" />
        </div>

        <el-empty v-if="spaces.length === 0" description="暂无项目空间" />
        <button
          v-for="space in spaces"
          :key="space.spaceId"
          class="space-item"
          :class="{ active: space.spaceId === selectedSpaceId }"
          type="button"
          @click="handleSelectSpace(space.spaceId)"
        >
          <span class="space-item-head">
            <strong>{{ space.name }}</strong>
            <el-tag size="small" :type="healthTagType(space.healthStatus)">
              {{ healthText(space.healthStatus) }}
            </el-tag>
          </span>
          <span class="space-goal">{{ space.deliveryGoal }}</span>
          <el-progress :percentage="space.progressPercent || 0" :stroke-width="8" />
          <span class="space-meta">
            <span>{{ space.owner }}</span>
            <span>{{ space.openActionItemCount || 0 }} 个行动项</span>
          </span>
        </button>
      </aside>

      <main class="artifact-workbench">
        <section class="space-summary">
          <div class="summary-copy">
            <p class="section-kicker">当前空间</p>
            <h2>{{ currentSpace?.deliveryGoal || '等待项目目标' }}</h2>
            <p>
              负责人 {{ currentSpace?.owner || '-' }}，团队 {{ currentSpace?.collaboratorCount || 0 }}
              人，下一检查点 {{ formatDateTime(currentSpace?.nextCheckpointAt) }}。
            </p>
          </div>
          <div class="summary-progress">
            <el-progress
              type="dashboard"
              :percentage="currentSpace?.progressPercent || 0"
              :stroke-width="12"
            />
            <span>{{ currentSpace?.sprintLabel || overview?.currentWeekLabel || '-' }}</span>
          </div>
        </section>

        <section class="boundary-band">
          <div
            v-for="boundary in currentSpace?.collaborationBoundaries || []"
            :key="boundary"
            class="boundary-chip"
          >
            {{ boundary }}
          </div>
        </section>

        <section class="artifact-panel">
          <el-tabs v-model="activeTab">
            <el-tab-pane name="tasks">
              <template #label>
                <span class="tab-label">
                  <el-icon><Flag /></el-icon>
                  任务拆解
                </span>
              </template>
              <div class="tab-toolbar">
                <div>
                  <h3>任务拆解</h3>
                  <p>{{ tasks.length }} 个工作包，{{ blockedTaskCount }} 个阻塞项</p>
                </div>
                <el-button type="primary" :icon="DataAnalysis" @click="showTaskDialog = true">
                  生成拆解
                </el-button>
              </div>
              <el-table :data="tasks" row-key="taskId" class="agent-table">
                <el-table-column prop="title" label="任务" min-width="220">
                  <template #default="{ row }">
                    <div class="table-title">
                      <strong>{{ row.title }}</strong>
                      <span>{{ row.description }}</span>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="roleOwner" label="负责人" width="140" />
                <el-table-column prop="status" label="状态" width="120">
                  <template #default="{ row }">
                    <el-tag :type="statusTagType(row.status)">
                      {{ statusText(row.status) }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="priority" label="优先级" width="110">
                  <template #default="{ row }">
                    <el-tag :type="priorityTagType(row.priority)" effect="plain">
                      {{ priorityText(row.priority) }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="progressPercent" label="进度" width="160">
                  <template #default="{ row }">
                    <el-progress :percentage="row.progressPercent || 0" :stroke-width="8" />
                  </template>
                </el-table-column>
                <el-table-column prop="dueDate" label="截止" width="120" />
              </el-table>
            </el-tab-pane>

            <el-tab-pane name="meetings">
              <template #label>
                <span class="tab-label">
                  <el-icon><Notebook /></el-icon>
                  会议纪要
                </span>
              </template>
              <div class="tab-toolbar">
                <div>
                  <h3>会议纪要</h3>
                  <p>{{ meetings.length }} 次同步，决策和阻塞点会进入协作历史</p>
                </div>
                <el-button type="primary" :icon="DocumentAdd" @click="showMeetingDialog = true">
                  新增纪要
                </el-button>
              </div>
              <el-empty v-if="meetings.length === 0" description="暂无会议纪要" />
              <div v-else class="meeting-list">
                <article v-for="meeting in meetings" :key="meeting.meetingId" class="meeting-item">
                  <div class="item-heading">
                    <div>
                      <strong>{{ meeting.title }}</strong>
                      <span>{{ formatDateTime(meeting.heldAt) }} · {{ meeting.facilitator }}</span>
                    </div>
                    <el-tag effect="plain">{{ meeting.meetingType || 'delivery_sync' }}</el-tag>
                  </div>
                  <p>{{ meeting.summary }}</p>
                  <div class="tag-row">
                    <el-tag
                      v-for="decision in meeting.decisions || []"
                      :key="decision"
                      type="success"
                      effect="plain"
                    >
                      {{ decision }}
                    </el-tag>
                    <el-tag
                      v-for="blocker in meeting.blockers || []"
                      :key="blocker"
                      type="warning"
                      effect="plain"
                    >
                      {{ blocker }}
                    </el-tag>
                  </div>
                </article>
              </div>
            </el-tab-pane>

            <el-tab-pane name="risks">
              <template #label>
                <span class="tab-label">
                  <el-icon><Warning /></el-icon>
                  风险雷达
                </span>
              </template>
              <div class="tab-toolbar">
                <div>
                  <h3>风险雷达</h3>
                  <p>{{ orderedRisks.length }} 个风险，最高风险分 {{ topRiskScore }}</p>
                </div>
              </div>
              <el-table :data="orderedRisks" row-key="riskId" class="agent-table">
                <el-table-column prop="title" label="风险" min-width="240">
                  <template #default="{ row }">
                    <div class="table-title">
                      <strong>{{ row.title }}</strong>
                      <span>{{ row.triggerSignal }}</span>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="severity" label="级别" width="100">
                  <template #default="{ row }">
                    <el-tag :type="severityTagType(row.severity)">
                      {{ severityText(row.severity) }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="score" label="风险分" width="100" />
                <el-table-column prop="owner" label="负责人" width="130" />
                <el-table-column prop="status" label="状态" width="150">
                  <template #default="{ row }">
                    <el-select
                      v-model="row.status"
                      size="small"
                      @change="(status: string) => handleRiskStatusChange(row, status)"
                    >
                      <el-option
                        v-for="option in riskStatusOptions"
                        :key="option.value"
                        :label="option.label"
                        :value="option.value"
                      />
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column prop="nextReviewDate" label="复盘日期" width="120" />
              </el-table>
            </el-tab-pane>

            <el-tab-pane name="weekly">
              <template #label>
                <span class="tab-label">
                  <el-icon><Collection /></el-icon>
                  周报生成
                </span>
              </template>
              <div class="tab-toolbar">
                <div>
                  <h3>周报生成</h3>
                  <p>{{ weeklyReports.length }} 份周报，{{ weeklyReadyText }}</p>
                </div>
                <el-button type="primary" :icon="Finished" @click="showWeeklyDialog = true">
                  生成周报
                </el-button>
              </div>
              <el-empty v-if="!latestWeeklyReport" description="暂无项目周报" />
              <article v-else class="weekly-report">
                <div class="item-heading">
                  <div>
                    <strong>{{ latestWeeklyReport.weekLabel }}</strong>
                    <span>
                      {{ latestWeeklyReport.generatedBy }} ·
                      {{ formatDateTime(latestWeeklyReport.generatedAt) }}
                    </span>
                  </div>
                  <el-tag type="success">草稿</el-tag>
                </div>
                <p class="report-summary">{{ latestWeeklyReport.executiveSummary }}</p>
                <div class="report-grid">
                  <div>
                    <h4>进度</h4>
                    <p>{{ latestWeeklyReport.progressSummary }}</p>
                  </div>
                  <div>
                    <h4>风险</h4>
                    <p>{{ latestWeeklyReport.riskSummary }}</p>
                  </div>
                  <div>
                    <h4>计划</h4>
                    <p>{{ latestWeeklyReport.planSummary }}</p>
                  </div>
                </div>
                <div class="report-lists">
                  <div>
                    <h4>已完成</h4>
                    <ul>
                      <li v-for="item in latestWeeklyReport.completedItems || []" :key="item">
                        {{ item }}
                      </li>
                    </ul>
                  </div>
                  <div>
                    <h4>下周重点</h4>
                    <ul>
                      <li v-for="item in latestWeeklyReport.nextWeekFocus || []" :key="item">
                        {{ item }}
                      </li>
                    </ul>
                  </div>
                  <div>
                    <h4>需要协助</h4>
                    <ul>
                      <li v-for="item in latestWeeklyReport.helpNeeded || []" :key="item">
                        {{ item }}
                      </li>
                    </ul>
                  </div>
                </div>
              </article>
            </el-tab-pane>

            <el-tab-pane name="actions">
              <template #label>
                <span class="tab-label">
                  <el-icon><CircleCheck /></el-icon>
                  行动项看板
                </span>
              </template>
              <div class="tab-toolbar">
                <div>
                  <h3>行动项看板</h3>
                  <p>{{ openActionItems.length }} 个未关闭行动项</p>
                </div>
              </div>
              <div class="action-board">
                <section
                  v-for="column in actionColumns"
                  :key="column.key"
                  class="action-column"
                >
                  <div class="column-title">
                    <strong>{{ column.title }}</strong>
                    <span>{{ column.items.length }}</span>
                  </div>
                  <article v-for="item in column.items" :key="item.actionItemId" class="action-card">
                    <div class="item-heading">
                      <div>
                        <strong>{{ item.title }}</strong>
                        <span>{{ item.owner }} · {{ item.dueDate }}</span>
                      </div>
                      <el-tag :type="priorityTagType(item.priority)" effect="plain">
                        {{ priorityText(item.priority) }}
                      </el-tag>
                    </div>
                    <p>{{ item.boundary }}</p>
                    <el-select
                      v-model="item.status"
                      size="small"
                      @change="(status: string) => handleActionStatusChange(item, status)"
                    >
                      <el-option
                        v-for="option in actionStatusOptions"
                        :key="option.value"
                        :label="option.label"
                        :value="option.value"
                      />
                    </el-select>
                  </article>
                </section>
              </div>
            </el-tab-pane>

            <el-tab-pane name="history">
              <template #label>
                <span class="tab-label">
                  <el-icon><Timer /></el-icon>
                  协作历史
                </span>
              </template>
              <div class="tab-toolbar">
                <div>
                  <h3>协作历史</h3>
                  <p>{{ history.length }} 条边界与状态变更记录</p>
                </div>
              </div>
              <el-timeline class="history-timeline">
                <el-timeline-item
                  v-for="event in history"
                  :key="event.eventId"
                  :timestamp="formatDateTime(event.occurredAt)"
                  placement="top"
                >
                  <article class="history-item">
                    <div class="item-heading">
                      <div>
                        <strong>{{ event.title }}</strong>
                        <span>{{ event.actor }} · {{ event.eventType }}</span>
                      </div>
                    </div>
                    <p>{{ event.summary }}</p>
                  </article>
                </el-timeline-item>
              </el-timeline>
            </el-tab-pane>
          </el-tabs>
        </section>
      </main>
    </div>

    <el-dialog v-model="showCreateSpaceDialog" title="创建项目空间" width="560px">
      <el-form label-position="top">
        <el-form-item label="空间名称">
          <el-input v-model="createSpaceForm.name" maxlength="80" />
        </el-form-item>
        <el-form-item label="交付目标">
          <el-input v-model="createSpaceForm.deliveryGoal" type="textarea" :rows="3" />
        </el-form-item>
        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="负责人">
              <el-input v-model="createSpaceForm.owner" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="阶段">
              <el-input v-model="createSpaceForm.stage" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="协作者">
          <el-input v-model="createSpaceForm.collaborators" />
        </el-form-item>
        <el-form-item label="协作边界">
          <el-input v-model="createSpaceForm.boundaries" type="textarea" :rows="4" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showCreateSpaceDialog = false">取消</el-button>
        <el-button type="primary" :loading="mutating" @click="handleCreateSpace">创建</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="showTaskDialog" title="生成任务拆解" width="560px">
      <el-form label-position="top">
        <el-form-item label="拆解目标">
          <el-input v-model="taskForm.objective" type="textarea" :rows="3" />
        </el-form-item>
        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="粒度">
              <el-select v-model="taskForm.granularity">
                <el-option label="按角色拆解" value="role" />
                <el-option label="按里程碑拆解" value="milestone" />
                <el-option label="按风险拆解" value="risk" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="截止日期">
              <el-date-picker
                v-model="taskForm.dueDate"
                type="date"
                value-format="YYYY-MM-DD"
                class="full-control"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="角色边界">
          <el-input v-model="taskForm.roleBoundaries" type="textarea" :rows="4" />
        </el-form-item>
        <el-checkbox v-model="taskForm.includeRiskScan">同步生成风险扫描</el-checkbox>
      </el-form>
      <template #footer>
        <el-button @click="showTaskDialog = false">取消</el-button>
        <el-button type="primary" :loading="mutating" @click="handleGenerateTasks">
          生成
        </el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="showMeetingDialog" title="新增会议纪要" width="620px">
      <el-form label-position="top">
        <el-form-item label="会议标题">
          <el-input v-model="meetingForm.title" />
        </el-form-item>
        <el-form-item label="会议输入">
          <el-input v-model="meetingForm.transcript" type="textarea" :rows="5" />
        </el-form-item>
        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="主持人">
              <el-input v-model="meetingForm.facilitator" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="会议类型">
              <el-input v-model="meetingForm.meetingType" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="参会人">
          <el-input v-model="meetingForm.participants" />
        </el-form-item>
        <el-form-item label="决策">
          <el-input v-model="meetingForm.decisions" type="textarea" :rows="3" />
        </el-form-item>
        <el-form-item label="阻塞点">
          <el-input v-model="meetingForm.blockers" type="textarea" :rows="3" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showMeetingDialog = false">取消</el-button>
        <el-button type="primary" :loading="mutating" @click="handleCreateMeeting">
          保存
        </el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="showWeeklyDialog" title="生成项目周报" width="520px">
      <el-form label-position="top">
        <el-form-item label="周报周期">
          <el-input v-model="weeklyForm.weekLabel" />
        </el-form-item>
        <el-form-item label="生成者">
          <el-input v-model="weeklyForm.generatedBy" />
        </el-form-item>
        <div class="dialog-checks">
          <el-checkbox v-model="weeklyForm.includeRisks">纳入风险摘要</el-checkbox>
          <el-checkbox v-model="weeklyForm.includeActionItems">纳入行动项</el-checkbox>
        </div>
      </el-form>
      <template #footer>
        <el-button @click="showWeeklyDialog = false">取消</el-button>
        <el-button type="primary" :loading="mutating" @click="handleGenerateWeekly">
          生成
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
  import { computed, onMounted, reactive, ref } from 'vue'
  import { storeToRefs } from 'pinia'
  import { ElMessage } from 'element-plus'
  import {
    CircleCheck,
    Collection,
    DataAnalysis,
    DocumentAdd,
    Finished,
    Flag,
    Notebook,
    Plus,
    Refresh,
    Timer,
    Warning
  } from '@element-plus/icons-vue'
  import { useProjectCopilotStore } from '@/store/modules/projectCopilot'
  import type { ActionItem, ProjectSpace, RiskItem } from '@/api/model/projectCopilotModel'

  defineOptions({ name: 'ProjectCopilotWorkbench' })

  const store = useProjectCopilotStore()
  const {
    overview,
    spaces,
    selectedSpaceId,
    tasks,
    meetings,
    orderedRisks,
    weeklyReports,
    actionItems,
    openActionItems,
    latestWeeklyReport,
    history,
    loading,
    mutating,
    activeTab,
    currentSpace,
    projectMetrics
  } = storeToRefs(store)

  const showCreateSpaceDialog = ref(false)
  const showTaskDialog = ref(false)
  const showMeetingDialog = ref(false)
  const showWeeklyDialog = ref(false)

  const createSpaceForm = reactive({
    name: '客户验收冲刺空间',
    deliveryGoal: '完成验收材料、风险复盘和上线行动项闭环',
    owner: '交付 PM',
    stage: '验收准备',
    collaborators: '交付 PM, Tech Lead, QA Lead, 客户成功',
    boundaries: 'Agent 输出拆解和风险草稿，负责人确认后推进\n客户承诺事项必须进入行动项看板'
  })

  const taskForm = reactive({
    objective: '客户验收闭环',
    granularity: 'role',
    dueDate: '2026-07-03',
    roleBoundaries: 'PM 确认验收范围\nTech Lead 确认技术依赖\nQA Lead 确认验收口径',
    includeRiskScan: true
  })

  const meetingForm = reactive({
    title: '客户验收准备会',
    transcript: '确认验收材料、客户承诺事项和上线风险复盘安排。',
    facilitator: '交付 PM',
    meetingType: 'delivery_sync',
    participants: '交付 PM, Tech Lead, QA Lead, 客户成功',
    decisions: '验收材料按上线门槛优先整理\n高风险项每日复盘一次',
    blockers: '客户运营导出范围仍需确认'
  })

  const weeklyForm = reactive({
    weekLabel: '2026-W26',
    generatedBy: 'Project Copilot',
    includeRisks: true,
    includeActionItems: true
  })

  const riskStatusOptions = [
    { label: '未处理', value: 'open' },
    { label: '观察中', value: 'watching' },
    { label: '缓解中', value: 'mitigating' },
    { label: '已关闭', value: 'closed' }
  ]

  const actionStatusOptions = [
    { label: '未开始', value: 'open' },
    { label: '推进中', value: 'in_progress' },
    { label: '阻塞', value: 'blocked' },
    { label: '已完成', value: 'done' }
  ]

  const metricCards = computed(() => {
    if (projectMetrics.value.length > 0) {
      return projectMetrics.value.map((metric) => ({
        key: metric.key || metric.label || 'metric',
        label: metric.label || '-',
        value: metric.value || '0',
        trend: metric.trend || ''
      }))
    }

    return [
      {
        key: 'spaces',
        label: '项目空间',
        value: String(overview.value?.totalSpaces || 0),
        trend: overview.value?.currentWeekLabel || '当前周'
      },
      {
        key: 'tasks',
        label: '活跃任务',
        value: String(overview.value?.activeTasks || tasks.value.length),
        trend: `${blockedTaskCount.value} 个阻塞`
      },
      {
        key: 'risks',
        label: '高风险',
        value: String(overview.value?.highRiskCount || 0),
        trend: '风险雷达'
      },
      {
        key: 'actions',
        label: '逾期行动项',
        value: String(overview.value?.overdueActionItems || 0),
        trend: `${openActionItems.value.length} 个未关闭`
      }
    ]
  })

  const blockedTaskCount = computed(() => {
    return tasks.value.filter((task) => task.status === 'blocked').length
  })

  const topRiskScore = computed(() => orderedRisks.value[0]?.score || 0)

  const weeklyReadyText = computed(() => {
    return overview.value?.weeklyReportReady ? '本周周报已就绪' : '等待补齐周报素材'
  })

  const actionColumns = computed(() => {
    const pick = (statusList: string[]) => {
      return actionItems.value.filter((item) => statusList.includes(item.status || 'open'))
    }

    return [
      { key: 'open', title: '待启动', items: pick(['open', 'todo', '']) },
      { key: 'in_progress', title: '推进中', items: pick(['in_progress', 'review']) },
      { key: 'blocked', title: '阻塞', items: pick(['blocked']) },
      { key: 'done', title: '已完成', items: pick(['done', 'closed']) }
    ]
  })

  const handleRefresh = async () => {
    await store.loadOverview()
    ElMessage.success('协作工作台已刷新')
  }

  const handleSelectSpace = async (spaceId?: ProjectSpace['spaceId']) => {
    if (!spaceId || spaceId === selectedSpaceId.value) return
    await store.selectSpace(spaceId)
  }

  const handleCreateSpace = async () => {
    if (!createSpaceForm.name.trim() || !createSpaceForm.deliveryGoal.trim()) {
      ElMessage.warning('请补齐空间名称和交付目标')
      return
    }

    await store.createProjectSpace({
      name: createSpaceForm.name.trim(),
      deliveryGoal: createSpaceForm.deliveryGoal.trim(),
      owner: createSpaceForm.owner.trim() || '交付 PM',
      stage: createSpaceForm.stage.trim() || '启动对齐',
      collaborators: splitInput(createSpaceForm.collaborators),
      collaborationBoundaries: splitInput(createSpaceForm.boundaries)
    })
    showCreateSpaceDialog.value = false
    ElMessage.success('项目空间已创建')
  }

  const handleGenerateTasks = async () => {
    if (!taskForm.objective.trim()) {
      ElMessage.warning('请填写拆解目标')
      return
    }

    await store.generateTaskBreakdown({
      objective: taskForm.objective.trim(),
      granularity: taskForm.granularity,
      dueDate: taskForm.dueDate,
      roleBoundaries: splitInput(taskForm.roleBoundaries),
      includeRiskScan: taskForm.includeRiskScan
    })
    showTaskDialog.value = false
    activeTab.value = 'tasks'
    ElMessage.success('任务拆解已生成')
  }

  const handleCreateMeeting = async () => {
    if (!meetingForm.title.trim() || !meetingForm.transcript.trim()) {
      ElMessage.warning('请填写会议标题和会议输入')
      return
    }

    await store.createMeetingMinutes({
      title: meetingForm.title.trim(),
      transcript: meetingForm.transcript.trim(),
      facilitator: meetingForm.facilitator.trim(),
      meetingType: meetingForm.meetingType.trim() || 'delivery_sync',
      participants: splitInput(meetingForm.participants),
      decisions: splitInput(meetingForm.decisions),
      blockers: splitInput(meetingForm.blockers)
    })
    showMeetingDialog.value = false
    activeTab.value = 'meetings'
    ElMessage.success('会议纪要已保存')
  }

  const handleRiskStatusChange = async (risk: RiskItem, status: string) => {
    if (!risk.riskId) return
    await store.updateRisk(risk.riskId, {
      status,
      owner: risk.owner,
      mitigationPlan: risk.mitigationPlan,
      nextReviewDate: risk.nextReviewDate
    })
    ElMessage.success('风险状态已更新')
  }

  const handleGenerateWeekly = async () => {
    if (!weeklyForm.weekLabel.trim()) {
      ElMessage.warning('请填写周报周期')
      return
    }

    await store.generateWeeklyReport({
      weekLabel: weeklyForm.weekLabel.trim(),
      generatedBy: weeklyForm.generatedBy.trim() || 'Project Copilot',
      includeRisks: weeklyForm.includeRisks,
      includeActionItems: weeklyForm.includeActionItems
    })
    showWeeklyDialog.value = false
    activeTab.value = 'weekly'
    ElMessage.success('周报草稿已生成')
  }

  const handleActionStatusChange = async (item: ActionItem, status: string) => {
    if (!item.actionItemId) return
    await store.updateActionItem(item.actionItemId, {
      status,
      owner: item.owner,
      dueDate: item.dueDate,
      reminderAt: item.reminderAt
    })
    ElMessage.success('行动项状态已更新')
  }

  const splitInput = (value: string) => {
    return value
      .split(/[\n,，]/)
      .map((item) => item.trim())
      .filter(Boolean)
  }

  const formatDateTime = (value?: string) => {
    if (!value) return '未设置'
    return value.replace('T', ' ').replace('Z', '')
  }

  const statusText = (status?: string) => {
    const map: Record<string, string> = {
      open: '未开始',
      in_progress: '推进中',
      blocked: '阻塞',
      review: '评审中',
      done: '已完成',
      closed: '已关闭'
    }
    return map[status || ''] || status || '未设置'
  }

  const priorityText = (priority?: string) => {
    const map: Record<string, string> = {
      high: '高',
      medium: '中',
      low: '低'
    }
    return map[priority || ''] || priority || '-'
  }

  const severityText = (severity?: string) => {
    const map: Record<string, string> = {
      high: '高',
      medium: '中',
      low: '低'
    }
    return map[severity || ''] || severity || '-'
  }

  const healthText = (health?: string) => {
    const map: Record<string, string> = {
      healthy: '健康',
      attention: '关注',
      critical: '告警'
    }
    return map[health || ''] || health || '未知'
  }

  type TagType = 'primary' | 'success' | 'warning' | 'info' | 'danger'

  const statusTagType = (status?: string) => {
    const map: Record<string, TagType> = {
      open: 'info',
      in_progress: 'primary',
      blocked: 'danger',
      review: 'warning',
      done: 'success',
      closed: 'success'
    }
    return map[status || ''] || 'info'
  }

  const priorityTagType = (priority?: string) => {
    const map: Record<string, TagType> = {
      high: 'danger',
      medium: 'warning',
      low: 'info'
    }
    return map[priority || ''] || 'info'
  }

  const severityTagType = (severity?: string) => {
    const map: Record<string, TagType> = {
      high: 'danger',
      medium: 'warning',
      low: 'info'
    }
    return map[severity || ''] || 'info'
  }

  const healthTagType = (health?: string) => {
    const map: Record<string, TagType> = {
      healthy: 'success',
      attention: 'warning',
      critical: 'danger'
    }
    return map[health || ''] || 'info'
  }

  onMounted(() => {
    store.loadOverview()
  })
</script>

<style lang="scss" scoped>
  .project-copilot-page {
    min-height: 100%;
    padding: 20px;
    color: #1f2937;
    background: #f6f8fb;
  }

  .workbench-header {
    display: flex;
    gap: 16px;
    align-items: flex-start;
    justify-content: space-between;
    padding: 6px 0 18px;

    h1 {
      margin: 4px 0;
      font-size: 28px;
      font-weight: 700;
      line-height: 1.25;
      letter-spacing: 0;
    }
  }

  .eyebrow,
  .section-kicker {
    margin: 0;
    font-size: 12px;
    font-weight: 700;
    color: #227067;
    text-transform: uppercase;
    letter-spacing: 0;
  }

  .subtitle {
    margin: 0;
    color: #64748b;
  }

  .header-actions {
    display: flex;
    gap: 10px;
    align-items: center;
    justify-content: flex-end;
  }

  .metric-strip {
    display: grid;
    grid-template-columns: repeat(4, minmax(0, 1fr));
    gap: 12px;
    margin-bottom: 16px;
  }

  .metric-item {
    display: grid;
    gap: 6px;
    min-width: 0;
    padding: 16px;
    background: #fff;
    border: 1px solid #dbe4ee;
    border-radius: 8px;

    span,
    small {
      overflow: hidden;
      color: #64748b;
      text-overflow: ellipsis;
      white-space: nowrap;
    }

    strong {
      overflow: hidden;
      font-size: 24px;
      line-height: 1.2;
      color: #0f172a;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }

  .workspace-grid {
    display: grid;
    grid-template-columns: 300px minmax(0, 1fr);
    gap: 16px;
    align-items: start;
  }

  .space-panel,
  .artifact-workbench,
  .artifact-panel,
  .space-summary {
    min-width: 0;
  }

  .space-panel {
    display: grid;
    gap: 12px;
    padding: 16px;
    background: #fff;
    border: 1px solid #dbe4ee;
    border-radius: 8px;
  }

  .panel-title-row,
  .tab-toolbar,
  .item-heading,
  .column-title {
    display: flex;
    gap: 12px;
    align-items: flex-start;
    justify-content: space-between;
    min-width: 0;
  }

  .panel-title-row h2,
  .space-summary h2,
  .tab-toolbar h3 {
    margin: 4px 0 0;
    font-size: 18px;
    line-height: 1.3;
    letter-spacing: 0;
  }

  .space-item {
    display: grid;
    gap: 10px;
    width: 100%;
    min-width: 0;
    padding: 14px;
    color: inherit;
    text-align: left;
    cursor: pointer;
    background: #f8fafc;
    border: 1px solid #e2e8f0;
    border-radius: 8px;
    transition:
      border-color 0.2s ease,
      box-shadow 0.2s ease,
      transform 0.2s ease;

    &:hover,
    &.active {
      border-color: #228b7b;
      box-shadow: 0 10px 24px rgb(15 23 42 / 8%);
      transform: translateY(-1px);
    }
  }

  .space-item-head,
  .space-meta {
    display: flex;
    gap: 8px;
    align-items: center;
    justify-content: space-between;
    min-width: 0;
  }

  .space-item-head strong,
  .item-heading strong,
  .column-title strong {
    min-width: 0;
    overflow: hidden;
    color: #0f172a;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .space-goal,
  .space-meta,
  .item-heading span,
  .tab-toolbar p,
  .summary-copy p,
  .table-title span {
    color: #64748b;
  }

  .space-goal {
    display: -webkit-box;
    overflow: hidden;
    line-height: 1.5;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }

  .artifact-workbench {
    display: grid;
    gap: 14px;
  }

  .space-summary {
    display: grid;
    grid-template-columns: minmax(0, 1fr) 132px;
    gap: 16px;
    align-items: center;
    padding: 18px;
    background: #fff;
    border: 1px solid #dbe4ee;
    border-radius: 8px;
  }

  .summary-copy {
    min-width: 0;

    p {
      margin: 8px 0 0;
      line-height: 1.6;
    }
  }

  .summary-progress {
    display: grid;
    justify-items: center;
    color: #64748b;
  }

  .boundary-band {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
  }

  .boundary-chip {
    max-width: 100%;
    padding: 8px 10px;
    overflow: hidden;
    font-size: 13px;
    color: #155e75;
    text-overflow: ellipsis;
    white-space: nowrap;
    background: #ecfeff;
    border: 1px solid #a5f3fc;
    border-radius: 8px;
  }

  .artifact-panel {
    padding: 16px 18px 20px;
    background: #fff;
    border: 1px solid #dbe4ee;
    border-radius: 8px;
  }

  .tab-label {
    display: inline-flex;
    gap: 6px;
    align-items: center;
  }

  .tab-toolbar {
    align-items: center;
    margin: 8px 0 16px;

    h3,
    p {
      margin: 0;
    }
  }

  .agent-table {
    width: 100%;
  }

  .table-title {
    display: grid;
    gap: 4px;
    min-width: 0;

    strong,
    span {
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }

  .meeting-list,
  .action-board {
    display: grid;
    gap: 12px;
  }

  .meeting-item,
  .weekly-report,
  .history-item,
  .action-card {
    min-width: 0;
    padding: 14px;
    background: #f8fafc;
    border: 1px solid #e2e8f0;
    border-radius: 8px;

    p {
      margin: 10px 0;
      line-height: 1.6;
      color: #475569;
    }
  }

  .tag-row {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
  }

  .weekly-report {
    display: grid;
    gap: 14px;
  }

  .report-summary {
    padding: 12px;
    margin: 0;
    color: #334155;
    background: #f1f5f9;
    border-radius: 8px;
  }

  .report-grid,
  .report-lists {
    display: grid;
    grid-template-columns: repeat(3, minmax(0, 1fr));
    gap: 12px;

    h4 {
      margin: 0 0 8px;
      font-size: 14px;
      color: #0f172a;
    }

    p,
    ul {
      margin: 0;
      color: #475569;
      line-height: 1.6;
    }

    ul {
      padding-left: 18px;
    }
  }

  .action-board {
    grid-template-columns: repeat(4, minmax(180px, 1fr));
    align-items: start;
  }

  .action-column {
    display: grid;
    gap: 10px;
    min-width: 0;
    padding: 12px;
    background: #f8fafc;
    border: 1px solid #e2e8f0;
    border-radius: 8px;
  }

  .column-title span {
    display: inline-grid;
    place-items: center;
    min-width: 28px;
    height: 24px;
    color: #155e75;
    background: #cffafe;
    border-radius: 999px;
  }

  .history-timeline {
    padding: 4px 0 0;
  }

  .dialog-checks {
    display: flex;
    gap: 16px;
    align-items: center;
  }

  .full-control {
    width: 100%;
  }

  :deep(.el-tabs__content) {
    overflow: visible;
  }

  @media (max-width: 1180px) {
    .metric-strip,
    .report-grid,
    .report-lists {
      grid-template-columns: repeat(2, minmax(0, 1fr));
    }

    .workspace-grid {
      grid-template-columns: 1fr;
    }

    .action-board {
      grid-template-columns: repeat(2, minmax(180px, 1fr));
    }
  }

  @media (max-width: 720px) {
    .project-copilot-page {
      padding: 12px;
    }

    .workbench-header,
    .space-summary,
    .tab-toolbar {
      grid-template-columns: 1fr;
      flex-direction: column;
      align-items: stretch;
    }

    .header-actions {
      justify-content: flex-start;
    }

    .metric-strip,
    .report-grid,
    .report-lists,
    .action-board {
      grid-template-columns: 1fr;
    }
  }
</style>
