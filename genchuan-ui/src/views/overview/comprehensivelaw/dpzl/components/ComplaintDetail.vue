<template>
  <div class="complaint-detail-container">
    <div class="detail-header">
      <h3>投诉举报详情 - {{ complaintData.title }}</h3>
      <div class="header-actions">
        <el-button-group>
          <el-button
            type="primary"
            size="small"
            @click="handleProcess"
            v-if="!complaintData.processed"
          >
            <i class="el-icon-check"></i> 处理投诉
          </el-button>
          <el-button
            type="success"
            size="small"
            @click="handleClose"
            v-if="complaintData.processed && complaintData.status !== 'closed'"
          >
            <i class="el-icon-circle-close"></i> 关闭投诉
          </el-button>
          <el-button type="text" size="small" @click="printComplaint">
            <i class="el-icon-printer"></i> 打印
          </el-button>
        </el-button-group>
      </div>
    </div>

    <div class="detail-content">
      <!-- 状态标识 -->
      <div class="status-badge">
        <el-tag :type="getStatusType(complaintData.status)" size="large">
          <i :class="getStatusIcon(complaintData.status)" style="margin-right: 5px;"></i>
          {{ getStatusText(complaintData.status) }}
        </el-tag>
        <span class="complaint-id">投诉编号：{{ complaintData.id }}</span>
      </div>

      <!-- 基本信息 -->
      <div class="basic-info-section">
        <h4><i class="el-icon-info"></i> 基本信息</h4>
        <div class="info-grid">
          <div class="info-item">
            <label>投诉人：</label>
            <span>{{ complaintData.complainant }}</span>
          </div>
          <div class="info-item">
            <label>联系电话：</label>
            <span>{{ complaintData.phone }}</span>
          </div>
          <div class="info-item">
            <label>投诉类型：</label>
            <el-tag :type="getComplaintType(complaintData.type)" size="small">
              {{ complaintData.type }}
            </el-tag>
          </div>
          <div class="info-item">
            <label>投诉时间：</label>
            <span>{{ formatTime(complaintData.createTime) }}</span>
          </div>
          <div class="info-item">
            <label>紧急程度：</label>
            <span :class="getUrgencyClass(complaintData.urgency)">
              {{ complaintData.urgency }}
            </span>
          </div>
          <div class="info-item">
            <label>处理时限：</label>
            <span>{{ complaintData.timeLimit }}小时</span>
          </div>
          <div class="info-item full-width">
            <label>投诉地址：</label>
            <span>{{ complaintData.address }}</span>
          </div>
        </div>
      </div>

      <!-- 投诉内容 -->
      <div class="content-section">
        <h4><i class="el-icon-edit-outline"></i> 投诉内容</h4>
        <div class="content-card">
          <div class="content-title">{{ complaintData.title }}</div>
          <div class="content-body">{{ complaintData.content }}</div>
          <div v-if="complaintData.attachments?.length" class="content-attachments">
            <div class="attachments-title">附件：</div>
            <div class="attachments-list">
              <div v-for="(attachment, index) in complaintData.attachments" :key="index" class="attachment-item">
                <i class="el-icon-picture-outline" v-if="attachment.type === 'image'"></i>
                <i class="el-icon-video-camera" v-else-if="attachment.type === 'video'"></i>
                <i class="el-icon-document" v-else></i>
                <span class="attachment-name">{{ attachment.name }}</span>
                <el-button type="text" size="small" @click="previewAttachment(attachment)">预览</el-button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 处理信息 -->
      <div v-if="complaintData.processRecords?.length" class="process-section">
        <h4><i class="el-icon-notebook-2"></i> 处理记录</h4>
        <div class="process-timeline">
          <el-timeline>
            <el-timeline-item
              v-for="(record, index) in complaintData.processRecords"
              :key="index"
              :timestamp="formatTime(record.time)"
              :color="getRecordColor(record.action)"
              placement="top"
            >
              <div class="record-card">
                <div class="record-header">
                  <span class="record-action">{{ record.action }}</span>
                  <span class="record-handler">{{ record.handler }}</span>
                </div>
                <div class="record-content">{{ record.content }}</div>
                <div v-if="record.feedback" class="record-feedback">
                  <strong>处理反馈：</strong>{{ record.feedback }}
                </div>
                <div v-if="record.files?.length" class="record-files">
                  <span class="files-label">处理材料：</span>
                  <el-tag
                    v-for="(file, idx) in record.files"
                    :key="idx"
                    size="small"
                    type="info"
                    class="file-tag"
                    @click="viewFile(file)"
                  >
                    {{ file.name }}
                  </el-tag>
                </div>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>

      <!-- 关联案件 -->
      <div v-if="complaintData.relatedCases?.length" class="related-cases-section">
        <h4><i class="el-icon-connection"></i> 关联案件</h4>
        <div class="cases-list">
          <div v-for="(caseItem, index) in complaintData.relatedCases" :key="index" class="case-item">
            <div class="case-header">
              <span class="case-id">{{ caseItem.caseId }}</span>
              <span :class="['case-status', getCaseStatusClass(caseItem.status)]">
                {{ caseItem.status }}
              </span>
            </div>
            <div class="case-title">{{ caseItem.title }}</div>
            <div class="case-info">
              <span>办理部门：{{ caseItem.department }}</span>
              <span>受理时间：{{ formatTime(caseItem.acceptTime) }}</span>
            </div>
            <div class="case-actions">
              <el-button type="text" size="small" @click="viewCaseDetail(caseItem)">
                查看详情
              </el-button>
            </div>
          </div>
        </div>
      </div>

      <!-- 处理表单（未处理时显示） -->
      <div v-if="!complaintData.processed" class="process-form-section">
        <h4><i class="el-icon-edit"></i> 处理投诉</h4>
        <el-form
          ref="processFormRef"
          :model="processForm"
          :rules="processRules"
          label-width="100px"
        >
          <el-form-item label="处理部门：" prop="department">
            <el-select
              v-model="processForm.department"
              placeholder="请选择处理部门"
              style="width: 300px;"
            >
              <el-option label="执法一队" value="dept1" />
              <el-option label="执法二队" value="dept2" />
              <el-option label="执法三队" value="dept3" />
            </el-select>
          </el-form-item>

          <el-form-item label="处理人员：" prop="handler">
            <el-select
              v-model="processForm.handler"
              placeholder="请选择处理人员"
              style="width: 300px;"
            >
              <el-option label="张三" value="zhangsan" />
              <el-option label="李四" value="lisi" />
              <el-option label="王五" value="wangwu" />
            </el-select>
          </el-form-item>

          <el-form-item label="处理意见：" prop="opinion">
            <el-input
              v-model="processForm.opinion"
              type="textarea"
              :rows="4"
              placeholder="请输入处理意见..."
              maxlength="500"
              show-word-limit
              style="width: 500px;"
            />
          </el-form-item>

          <el-form-item label="上传材料：">
            <el-upload
              v-model:file-list="processForm.files"
              action="#"
              multiple
              :limit="5"
              :on-exceed="handleExceed"
              :auto-upload="false"
            >
              <el-button type="primary">
                <i class="el-icon-upload"></i> 选择文件
              </el-button>
              <template #tip>
                <div class="el-upload__tip">
                  支持上传图片、文档等材料，不超过5个文件
                </div>
              </template>
            </el-upload>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitProcess">提交处理</el-button>
            <el-button @click="resetProcessForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox, type UploadFile } from 'element-plus'
import type { FormInstance, FormRules } from 'element-plus'

const props = defineProps<{
  data: any
}>()

// 投诉数据
const complaintData = ref({
  id: 'COMP20240115001',
  title: '关于XX工地夜间施工噪音扰民的投诉',
  complainant: '王先生',
  phone: '138****1234',
  type: '噪音扰民',
  urgency: '紧急',
  timeLimit: 24,
  address: 'XX市XX区XX街道XX工地',
  content: 'XX工地夜间10点后仍在施工，噪音严重影响周边居民休息，多次沟通无果，望有关部门处理。',
  status: 'processing',
  processed: false,
  createTime: '2024-01-15 20:30:00',
  attachments: [
    { name: '噪音录音.mp3', type: 'audio', url: '#' },
    { name: '现场照片1.jpg', type: 'image', url: '#' },
    { name: '现场照片2.jpg', type: 'image', url: '#' }
  ],
  processRecords: [
    {
      time: '2024-01-15 21:00:00',
      action: '投诉受理',
      handler: '系统',
      content: '投诉已受理，分配至执法部门处理'
    }
  ],
  relatedCases: [
    {
      caseId: 'CASE20240116001',
      title: 'XX工地夜间施工噪音扰民案',
      status: '办理中',
      department: '执法一队',
      acceptTime: '2024-01-16 09:00:00'
    }
  ]
})

// 处理表单
const processFormRef = ref<FormInstance>()
const processForm = reactive({
  department: '',
  handler: '',
  opinion: '',
  files: [] as UploadFile[]
})

const processRules: FormRules = {
  department: [
    { required: true, message: '请选择处理部门', trigger: 'change' }
  ],
  handler: [
    { required: true, message: '请选择处理人员', trigger: 'change' }
  ],
  opinion: [
    { required: true, message: '请输入处理意见', trigger: 'blur' },
    { min: 10, message: '处理意见不能少于10个字符', trigger: 'blur' }
  ]
}

// 方法
const getStatusType = (status: string) => {
  const typeMap: Record<string, string> = {
    pending: 'warning',
    processing: 'primary',
    processed: 'success',
    closed: 'info'
  }
  return typeMap[status] || 'default'
}

const getStatusIcon = (status: string) => {
  const iconMap: Record<string, string> = {
    pending: 'el-icon-time',
    processing: 'el-icon-loading',
    processed: 'el-icon-check',
    closed: 'el-icon-circle-close'
  }
  return iconMap[status] || 'el-icon-question'
}

const getStatusText = (status: string) => {
  const textMap: Record<string, string> = {
    pending: '待处理',
    processing: '处理中',
    processed: '已处理',
    closed: '已关闭'
  }
  return textMap[status] || status
}

const getComplaintType = (type: string) => {
  const typeMap: Record<string, string> = {
    '噪音扰民': 'warning',
    '违法建设': 'danger',
    '环境污染': 'success',
    '占道经营': 'info'
  }
  return typeMap[type] || 'default'
}

const getUrgencyClass = (urgency: string) => {
  const classMap: Record<string, string> = {
    '紧急': 'urgency-high',
    '一般': 'urgency-normal',
    '低': 'urgency-low'
  }
  return classMap[urgency] || 'urgency-normal'
}

const formatTime = (time: string) => {
  if (!time) return ''
  return time.replace('T', ' ').substring(0, 19)
}

const getRecordColor = (action: string) => {
  const colorMap: Record<string, string> = {
    '投诉受理': '#3B82F6',
    '任务分配': '#8B5CF6',
    '现场调查': '#10B981',
    '处理完成': '#22C55E',
    '投诉关闭': '#6B7280'
  }
  return colorMap[action] || '#6B7280'
}

const getCaseStatusClass = (status: string) => {
  const classMap: Record<string, string> = {
    '办理中': 'status-processing',
    '已办结': 'status-completed',
    '已超期': 'status-overdue'
  }
  return classMap[status] || ''
}

const handleProcess = () => {
  // 直接显示处理表单
  const processFormSection = document.querySelector('.process-form-section')
  if (processFormSection) {
    processFormSection.scrollIntoView({ behavior: 'smooth' })
  }
}

const handleClose = () => {
  ElMessageBox.prompt('请输入关闭原因', '关闭投诉', {
    confirmButtonText: '确认关闭',
    cancelButtonText: '取消',
    inputPlaceholder: '请输入关闭原因...',
    inputValidator: (value) => {
      if (!value) return '请输入关闭原因'
      if (value.length < 5) return '关闭原因不能少于5个字符'
      return true
    }
  }).then(({ value }) => {
    complaintData.value.status = 'closed'
    complaintData.value.processed = true

    complaintData.value.processRecords.push({
      time: new Date().toISOString().replace('T', ' ').substring(0, 19),
      action: '投诉关闭',
      handler: '系统管理员',
      content: `投诉已关闭，关闭原因：${value}`
    })

    ElMessage.success('投诉已关闭')
  }).catch(() => {
    // 取消操作
  })
}

const previewAttachment = (attachment: any) => {
  ElMessage.info(`预览附件：${attachment.name}`)
  // 实际开发中实现附件预览
}

const viewFile = (file: any) => {
  ElMessage.info(`查看文件：${file.name}`)
  // 实际开发中实现文件查看
}

const viewCaseDetail = (caseItem: any) => {
  ElMessage.info(`查看案件：${caseItem.caseId}`)
  // 实际开发中跳转到案件详情页面
}

const handleExceed = (files: File[], fileList: UploadFile[]) => {
  ElMessage.warning(`最多只能上传5个文件，当前选择了 ${files.length} 个文件，共 ${files.length + fileList.length} 个文件`)
}

const submitProcess = async () => {
  if (!processFormRef.value) return

  try {
    await processFormRef.value.validate()

    // 模拟处理提交
    complaintData.value.processed = true
    complaintData.value.status = 'processed'

    complaintData.value.processRecords.push({
      time: new Date().toISOString().replace('T', ' ').substring(0, 19),
      action: '处理完成',
      handler: processForm.handler,
      content: processForm.opinion,
      feedback: '已对施工单位进行警告并要求整改'
    })

    ElMessage.success('投诉处理完成')

    // 重置表单
    resetProcessForm()
  } catch (error) {
    console.error('表单验证失败:', error)
  }
}

const resetProcessForm = () => {
  processFormRef.value?.resetFields()
  processForm.files = []
}

const printComplaint = () => {
  window.print()
}

// 初始化
if (props.data) {
  Object.assign(complaintData.value, props.data)
}
</script>

<style lang="scss" scoped>
.complaint-detail-container {
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

  .status-badge {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
    background: #F8FAFC;
    border-radius: 8px;
    border: 1px solid #E2E8F0;
    margin-bottom: 25px;

    .complaint-id {
      color: #64748B;
      font-size: 14px;
    }
  }

  .basic-info-section {
    .info-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 15px;

      @media (max-width: 1200px) {
        grid-template-columns: repeat(2, 1fr);
      }

      @media (max-width: 768px) {
        grid-template-columns: 1fr;
      }

      .info-item {
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

        span {
          color: #1E293B;
          font-size: 14px;

          &.urgency-high {
            color: #EF4444;
            font-weight: bold;
          }

          &.urgency-normal {
            color: #F59E0B;
            font-weight: bold;
          }

          &.urgency-low {
            color: #22C55E;
            font-weight: bold;
          }
        }
      }
    }
  }

  .content-section {
    .content-card {
      background: #FFFFFF;
      border-radius: 8px;
      padding: 20px;
      border: 1px solid #E2E8F0;

      .content-title {
        font-size: 18px;
        font-weight: 600;
        color: #1E293B;
        margin-bottom: 15px;
        padding-bottom: 15px;
        border-bottom: 1px solid #F1F5F9;
      }

      .content-body {
        color: #475569;
        font-size: 14px;
        line-height: 1.8;
        margin-bottom: 20px;
      }

      .content-attachments {
        .attachments-title {
          color: #64748B;
          font-size: 14px;
          margin-bottom: 10px;
        }

        .attachments-list {
          .attachment-item {
            display: flex;
            align-items: center;
            padding: 8px 12px;
            border: 1px solid #E2E8F0;
            border-radius: 4px;
            margin-bottom: 8px;
            background: #F8FAFC;

            i {
              margin-right: 8px;
              color: #64748B;
            }

            .attachment-name {
              flex: 1;
              color: #1E293B;
              font-size: 13px;
            }
          }
        }
      }
    }
  }

  .process-section {
    .process-timeline {
      background: #FFFFFF;
      border-radius: 8px;
      padding: 20px;
      border: 1px solid #E2E8F0;

      :deep(.el-timeline) {
        .el-timeline-item {
          .el-timeline-item__timestamp {
            font-size: 12px;
            color: #64748B;
            margin-bottom: 8px;
          }
        }
      }

      .record-card {
        background: #F8FAFC;
        border-radius: 6px;
        padding: 15px;
        margin-bottom: 10px;

        .record-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 8px;

          .record-action {
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
          margin-bottom: 8px;
        }

        .record-feedback {
          color: #22C55E;
          font-size: 13px;
          margin-bottom: 8px;
          padding: 8px;
          background: rgba(34, 197, 94, 0.1);
          border-radius: 4px;
        }

        .record-files {
          .files-label {
            font-size: 12px;
            color: #64748B;
            margin-right: 8px;
          }

          .file-tag {
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

  .related-cases-section {
    .cases-list {
      background: #FFFFFF;
      border-radius: 8px;
      padding: 15px;
      border: 1px solid #E2E8F0;

      .case-item {
        padding: 12px 15px;
        border-bottom: 1px solid #F1F5F9;

        &:last-child {
          border-bottom: none;
        }

        &:hover {
          background: #F8FAFC;
          border-radius: 4px;
        }

        .case-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 8px;

          .case-id {
            color: #0891B2;
            font-weight: 500;
          }

          .case-status {
            font-size: 12px;
            padding: 2px 8px;
            border-radius: 10px;

            &.status-processing {
              background: rgba(59, 130, 246, 0.1);
              color: #3B82F6;
            }

            &.status-completed {
              background: rgba(34, 197, 94, 0.1);
              color: #22C55E;
            }

            &.status-overdue {
              background: rgba(239, 68, 68, 0.1);
              color: #EF4444;
            }
          }
        }

        .case-title {
          color: #1E293B;
          font-size: 14px;
          margin-bottom: 6px;
        }

        .case-info {
          display: flex;
          justify-content: space-between;
          font-size: 12px;
          color: #64748B;
          margin-bottom: 8px;
        }
      }
    }
  }

  .process-form-section {
    background: #FFFFFF;
    border-radius: 8px;
    padding: 20px;
    border: 1px solid #E2E8F0;
  }
}
</style>
