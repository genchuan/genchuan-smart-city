export type WorkplaceTotal = {
  project: number
  access: number
  todo: number,
  task:number,
  alarm:number
}

export type Project = {
  name: string
  icon: string
  message: string
  personal: string
  time: Date | number | string
}

export type Notice = {
  title: string
  type: string
  keys: string[]
  date: Date | number | string
}

// 新增：待办任务类型，映射 TaskApi.getTaskTodoPage 返回的核心字段
export type TodoTaskInfo = {
  taskId: string // 任务ID
  taskName: string // 任务名称（如：受理、处置、分拨）
  processName: string // 流程名称（如：燃气管道事件流程）
  processInstanceId: string // 流程实例ID
  createTime: number // 任务创建时间（时间戳）
  startUserName: string // 流程发起者昵称
}

export type Shortcut = {
  name: string
  icon: string
  url: string
}

export type RadarData = {
  personal: number
  team: number
  max: number
  name: string
}
export type AnalysisTotalTypes = {
  users: number
  messages: number
  moneys: number
  shoppings: number
}

export type UserAccessSource = {
  value: number
  name: string
}

export type WeeklyUserActivity = {
  value: number
  name: string
}

export type MonthlySales = {
  name: string
  estimate: number
  actual: number
}
