<template>
  <div class="case-detail-container">
    <div class="detail-header">
      <h3>案件详情 - {{ caseData.caseId }}</h3>
      <div class="header-actions">
        <el-button-group>
          <el-button type="primary" size="small" @click="handleDispatch" v-if="caseData.status === 'pending'">
            <i class="el-icon-sort"></i> 派单
          </el-button>
          <el-button type="success" size="small" @click="handleComplete" v-if="caseData.status === 'processing'">
            <i class="el-icon-check"></i> 办结
          </el-button>
          <el-button size="small" @click="exportCaseData">
            <i class="el-icon-download"></i> 导出
          </el-button>
          <el-button size="small" @click="printCase">
            <i class="el-icon-printer"></i> 打印
          </el-button>
        </el-button-group>
      </div>
    </div>

    <div class="detail-content">
      <!-- 案件状态标签 -->
      <div class="case-status-bar">
        <el-steps :active="getStepActive(caseData.status)" align-center>
          <el-step title="案件受理" :description="formatTime(caseData.acceptTime)" />
          <el-step title="案件调查" :description="formatTime(caseData.investigateTime)" />
          <el-step title="处罚决定" :description="formatTime(caseData.decisionTime)" />
          <el-step title="案件办结" :description="formatTime(caseData.completeTime)" />
        </el-steps>
      </div>

      <!-- 基础信息 -->
      <div class="basic-info-section">
        <h4><i class="el-icon-document"></i> 基本信息</h4>
        <div class="info-grid">
          <div class="info-item">
            <label>案件编号：</label>
            <span class="highlight">{{ caseData.caseId }}</span>
          </div>
          <div class="info-item">
            <label>案件来源：</label>
            <el-tag :type="getSourceType(caseData.source)" size="small">
              {{ caseData.source }}
            </el-tag>
          </div>
          <div class="info-item">
            <label>案件类型：</label>
            <span>{{ caseData.type }}</span>
          </div>
          <div class="info-item">
            <label>办理部门：</label>
            <span>{{ caseData.department }}</span>
          </div>
          <div class="info-item">
            <label>办理人员：</label>
            <span>{{ caseData.handler }}</span>
          </div>
          <div class="info-item">
            <label>受理时间：</label>
            <span>{{ formatTime(caseData.acceptTime) }}</span>
          </div>
          <div class="info-item">
            <label>办理时限：</label>
            <span>{{ caseData.timeLimit }}天</span>
          </div>
          <div class="info-item">
            <label>剩余时间：</label>
            <span :class="getRemainingTimeClass(caseData.remainingDays)">
              {{ caseData.remainingDays }}天
            </span>
          </div>
          <div class="info-item full-width">
            <label>案件描述：</label>
            <div class="case-description">{{ caseData.description }}</div>
          </div>
        </div>
      </div>

      <!-- 涉案信息 -->
      <div v-if="caseData.partyInfo" class="party-info-section">
        <h4><i class="el-icon-user"></i> 涉案信息</h4>
        <div class="party-grid">
          <div class="party-item">
            <label>当事人：</label>
            <span>{{ caseData.partyInfo.name }}</span>
          </div>
          <div class="party-item">
            <label>证件类型：</label>
            <span>{{ caseData.partyInfo.idType }}</span>
          </div>
          <div class="party-item">
            <label>证件号码：</label>
            <span>{{ caseData.partyInfo.idNumber }}</span>
          </div>
          <div class="party-item">
            <label>联系电话：</label>
            <span>{{ caseData.partyInfo.phone }}</span>
          </div>
          <div class="party-item full-width">
            <label>联系地址：</label>
            <span>{{ caseData.partyInfo.address }}</span>
          </div>
        </div>
      </div>

      <!-- 处罚信息 -->
      <div v-if="caseData.punishment" class="punishment-section">
        <h4><i class="el-icon-warning"></i> 处罚信息</h4>
        <div class="punishment-grid">
          <div class="punishment-item">
            <label>处罚类型：</label>
            <span>{{ caseData.punishment.type }}</span>
          </div>
          <div class="punishment-item">
            <label>处罚金额：</label>
            <span class="highlight amount">{{ formatAmount(caseData.punishment.amount) }}</span>
          </div>
          <div class="punishment-item">
            <label>处罚依据：</label>
            <span>{{ caseData.punishment.basis }}</span>
          </div>
          <div class="punishment-item full-width">
            <label>处罚内容：</label>
            <div class="punishment-content">{{ caseData.punishment.content }}</div>
          </div>
        </div>
      </div>

      <!-- 办理记录 -->
      <div class="process-records-section">
        <h4><i class="el-icon-notebook-2"></i> 办理记录</h4>
        <div class="timeline-container">
          <el-timeline>
            <el-timeline-item
              v-for="(record, index) in processRecords"
              :key="index"
              :timestamp="formatTime(record.time)"
              :color="getRecordColor(record.type)"
              placement="top"
            >
              <div class="record-item">
                <div class="record-header">
                  <span class="record-type">{{ record.type }}</span>
                  <span class="record-handler">{{ record.handler }}</span>
                </div>
                <div class="record-content">{{ record.content }}</div>
                <div v-if="record.attachments?.length" class="record-attachments">
                  <span class="attachment-label">附件：</span>
                  <el-tag
                    v-for="(attachment, idx) in record.attachments"
                    :key="idx"
                    size="small"
                    type="info"
                    class="attachment-tag"
                    @click="previewAttachment(attachment)"
                  >
                    {{ attachment.name }}
                  </el-tag>
                </div>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>

      <!-- 关联文件 -->
      <div v-if="caseData.files?.length" class="files-section">
        <h4><i class="el-icon-files"></i> 关联文件</h4>
        <div class="file-list">
          <div v-for="file in caseData.files" :key="file.id" class="file-item">
            <i class="el-icon-document" style="margin-right: 8px;"></i>
            <span class="file-name">{{ file.name }}</span>
            <span class="file-size">({{ file.size }})</span>
            <div class="file-actions">
              <el-button type="text" size="small" @click="previewFile(file)">预览</el-button>
              <el-button type="text" size="small" @click="downloadFile(file)">下载</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const props = defineProps<{
  data: any
}>()

// 案件数据
const caseData = ref({
  caseId: 'CASE20240115001',
  source: '热线举报',
  type: '违法建设',
  department: '执法一队',
  handler: '张三',
  acceptTime: '2024-01-15 09:30:00',
  timeLimit: 15,
  remainingDays: 5,
  status: 'processing',
  description: '接群众举报，XX小区存在违法搭建情况，经初步核实情况属实，需进一步调查处理。',
  partyInfo: {
    name: '李四',
    idType: '身份证',
    idNumber: '320************012',
    phone: '138****5678',
    address: 'XX市XX区XX街道XX小区1栋101室'
  },
  punishment: {
    type: '罚款',
    amount: 5000,
    basis: '《城乡规划法》第六十四条',
    content: '责令限期拆除违法建设，并处罚款人民币5000元整。'
  },
  files: [
    { id: 1, name: '现场检查记录表.pdf', size: '2.3MB' },
    { id: 2, name: '询问笔录.docx', size: '1.1MB' },
    { id: 3, name: '现场照片.zip', size: '15.2MB' }
  ],
  investigateTime: '2024-01-16 14:00:00',
  decisionTime: '2024-01-18 10:15:00',
  completeTime: null
})

// 办理记录
const processRecords = ref([
  {
    time: '2024-01-15 09:30:00',
    type: '案件受理',
    handler: '系统',
    content: '案件受理成功，分配至执法一队处理'
  },
  {
    time: '2024-01-15 10:15:00',
    type: '任务分配',
    handler: '王五（队长）',
    content: '分配执法人员张三负责调查处理'
  },
  {
    time: '2024-01-16 14:00:00',
    type: '现场调查',
    handler: '张三',
    content: '前往现场调查，发现违法搭建事实存在',
    attachments: [
      { name: '现场照片1.jpg', url: '#' },
      { name: '现场照片2.jpg', url: '#' }
    ]
  },
  {
    time: '2024-01-16 16:30:00',
    type: '询问笔录',
    handler: '张三',
    content: '制作当事人询问笔录，当事人承认违法事实',
    attachments: [
      { name: '询问笔录.docx', url: '#' }
    ]
  },
  {
    time: '2024-01-18 10:15:00',
    type: '处罚决定',
    handler: '张三',
    content: '根据调查结果，作出罚款5000元的处罚决定'
  }
])

// 计算属性
const caseStatus = computed(() => {
  const statusMap: Record<string, string> = {
    pending: '待处理',
    processing: '办理中',
    completed: '已办结',
    overdue: '已超期'
  }
  return statusMap[caseData.value.status] || caseData.value.status
})

// 方法
const getStepActive = (status: string) => {
  const stepMap: Record<string, number> = {
    pending: 0,
    processing: 1,
    completed: 3
  }
  return stepMap[status] || 0
}

const formatTime = (time: string | null) => {
  if (!time) return '未完成'
  return time.replace(' ', ' ').substring(5, 16)
}

const getSourceType = (source: string) => {
  const typeMap: Record<string, string> = {
    '热线举报': 'warning',
    '巡查发现': 'primary',
    '上级交办': 'success',
    '群众举报': 'info'
  }
  return typeMap[source] || 'default'
}

const getRemainingTimeClass = (days: number) => {
  if (days <= 0) return 'time-overdue'
  if (days <= 3) return 'time-warning'
  return 'time-normal'
}

const formatAmount = (amount: number) => {
  return `¥${amount.toLocaleString()}`
}

const getRecordColor = (type: string) => {
  const colorMap: Record<string, string> = {
    '案件受理': '#3B82F6',
    '任务分配': '#8B5CF6',
    '现场调查': '#10B981',
    '询问笔录': '#F59E0B',
    '处罚决定': '#EF4444',
    '案件办结': '#22C55E'
  }
  return colorMap[type] || '#6B7280'
}

const handleDispatch = () => {
  ElMessageBox.prompt('请输入派单备注', '案件派单', {
    confirmButtonText: '确认派单',
    cancelButtonText: '取消',
    inputPlaceholder: '请输入派单备注...'
  }).then(({ value }) => {
    ElMessage.success('派单成功')
    // 实际开发中调用派单API
  }).catch(() => {
    // 取消操作
  })
}

const handleComplete = () => {
  ElMessageBox.confirm(
    '确认办结此案件吗？办结后将不能再修改案件信息。',
    '案件办结确认',
    {
      confirmButtonText: '确认办结',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    ElMessage.success('案件已办结')
    caseData.value.status = 'completed'
    caseData.value.completeTime = new Date().toISOString().replace('T', ' ').substring(0, 19)
    // 实际开发中调用办结API
  }).catch(() => {
    // 取消操作
  })
}

const exportCaseData = () => {
  ElMessage.success('案件数据导出成功')
  // 实际开发中调用导出API
}

const printCase = () => {
  window.print()
}

const previewAttachment = (attachment: any) => {
  ElMessage.info(`预览附件：${attachment.name}`)
  // 实际开发中实现附件预览功能
}

const previewFile = (file: any) => {
  ElMessage.info(`预览文件：${file.name}`)
  // 实际开发中实现文件预览功能
}

const downloadFile = (file: any) => {
  ElMessage.success(`开始下载：${file.name}`)
  // 实际开发中实现文件下载功能
}

// 初始化
if (props.data) {
  Object.assign(caseData.value, props.data)
}
</script>

<style lang="scss" scoped>
.case-detail-container {
  .detail-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid #E2E8F0;

    h3 {
      margin: 0;
      color: #0891B2;
      font-size: 18px;
      font-weight: 600;
    }
  }

  .detail-content {
    > div {
      margin-bottom: 25px;
    }

    h4 {
      margin: 0 0 15px 0;
      color: #1E293B;
      font-size: 16px;
      font-weight: 500;
      display: flex;
      align-items: center;

      i {
        margin-right: 8px;
      }
    }
  }

  .case-status-bar {
    background: #FFFFFF;
    border-radius: 8px;
    padding: 20px;
    border: 1px solid #E2E8F0;
    margin-bottom: 25px;

    :deep(.el-steps) {
      .el-step__head {
        &.is-process {
          color: #0891B2;
          border-color: #0891B2;
        }
      }

      .el-step__title {
        font-size: 14px;
        &.is-process {
          color: #0891B2;
        }
      }

      .el-step__description {
        font-size: 12px;
        color: #64748B;
      }
    }
  }

  .basic-info-section,
  .party-info-section,
  .punishment-section {
    .info-grid,
    .party-grid,
    .punishment-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 15px;

      @media (max-width: 1200px) {
        grid-template-columns: repeat(2, 1fr);
      }

      @media (max-width: 768px) {
        grid-template-columns: 1fr;
      }

      .info-item,
      .party-item,
      .punishment-item {
        background: #F8FAFC;
        border-radius: 8px;
        padding: 15px;
        border: 1px solid #E2E8F0;

        &.full-width {
          grid-column: 1 / -1;
        }

        label {
          color: #64748B;
          font-size: 14px;
          margin-right: 8px;
          display: block;
          margin-bottom: 5px;
        }

        .highlight {
          font-size: 16px;
          font-weight: bold;
          color: #1E293B;

          &.amount {
            color: #D97706;
          }
        }

        .case-description,
        .punishment-content {
          color: #475569;
          font-size: 14px;
          line-height: 1.6;
          padding: 10px;
          background: #FFFFFF;
          border-radius: 4px;
          border: 1px solid #E2E8F0;
        }

        .time-overdue {
          color: #EF4444;
          font-weight: bold;
        }

        .time-warning {
          color: #F59E0B;
          font-weight: bold;
        }

        .time-normal {
          color: #22C55E;
          font-weight: bold;
        }
      }
    }
  }

  .process-records-section {
    .timeline-container {
      background: #FFFFFF;
      border-radius: 8px;
      padding: 20px;
      border: 1px solid #E2E8F0;

      :deep(.el-timeline) {
        padding-left: 20px;

        .el-timeline-item {
          &:last-child {
            .el-timeline-item__tail {
              display: block;
            }
          }

          .el-timeline-item__node {
            width: 12px;
            height: 12px;
            left: -5px;
          }

          .el-timeline-item__timestamp {
            font-size: 12px;
            color: #64748B;
            margin-bottom: 8px;
          }
        }
      }

      .record-item {
        background: #F8FAFC;
        border-radius: 6px;
        padding: 15px;
        margin-bottom: 10px;

        .record-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 8px;

          .record-type {
            font-size: 14px;
            font-weight: 500;
            color: #1E293B;
          }

          .record-handler {
            font-size: 12px;
            color: #64748B;
          }
        }

        .record-content {
          color: #475569;
          font-size: 13px;
          line-height: 1.5;
        }

        .record-attachments {
          margin-top: 10px;

          .attachment-label {
            font-size: 12px;
            color: #64748B;
            margin-right: 8px;
          }

          .attachment-tag {
            margin-right: 5px;
            cursor: pointer;

            &:hover {
              opacity: 0.8;
            }
          }
        }
      }
    }
  }

  .files-section {
    .file-list {
      background: #FFFFFF;
      border-radius: 8px;
      padding: 15px;
      border: 1px solid #E2E8F0;

      .file-item {
        display: flex;
        align-items: center;
        padding: 10px 15px;
        border-bottom: 1px solid #F1F5F9;

        &:last-child {
          border-bottom: none;
        }

        &:hover {
          background: #F8FAFC;
          border-radius: 4px;
        }

        .file-name {
          flex: 1;
          color: #1E293B;
          font-size: 14px;
        }

        .file-size {
          color: #64748B;
          font-size: 12px;
          margin: 0 15px;
        }

        .file-actions {
          display: flex;
          gap: 5px;
        }
      }
    }
  }
}
</style>
