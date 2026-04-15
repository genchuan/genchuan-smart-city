# 订单交易模块 - 接口调试闭环流程

> 前提：先执行 `test-data.sql` 写入基础数据
> Knife4j地址：http://localhost:58090/doc.html
> 全局Header：tenant-id = 1

---

## 🔵 第一条闭环：停车订单完整生命周期

**流程：创建 → 查询 → 支付 → 开票**

### Step 1：创建主订单
```
POST /ordertrade/all-order/create
Body:
{
  "orderNo": "NEW-PARK-001",
  "userId": 1001,
  "stationId": 1,
  "type": "temp_park",
  "totalAmount": 30.00,
  "payAmount": 30.00,
  "status": "pending_pay",
  "remark": "新建停车订单"
}
✅ 期望返回：{ "code": 200, "data": 9 }（返回新订单ID）
```

### Step 2：创建临时停车子订单（关联上面的ID）
```
POST /ordertrade/temp-park-order/create
Body:
{
  "orderId": 9,
  "spaceId": 101,
  "inTime": "2025-04-11 10:00:00",
  "parkHour": 2.0,
  "fee": 30.00,
  "remark": "B区101车位"
}
✅ 期望返回：{ "code": 200, "data": 4 }
```

### Step 3：查询订单列表（确认创建成功）
```
GET /ordertrade/all-order/page
Params: pageNo=1&pageSize=10
✅ 期望返回：list中包含刚创建的订单，status=pending_pay
```

### Step 4：查询订单详情
```
GET /ordertrade/all-order/get?id=9
✅ 期望返回：订单完整信息
```

### Step 5：支付订单
```
PUT /ordertrade/all-order/pay
Body: { "id": 9 }
✅ 期望返回：{ "code": 200, "data": true }
再查 /get?id=9，status 变为 paid
```

### Step 6：申请开票
```
PUT /ordertrade/all-order/invoice
Body: { "id": 9 }
✅ 期望返回：{ "code": 200, "data": true }
```

### Step 7：查看图表统计
```
GET /ordertrade/all-order/chart
Params: startTime=2025-01-01 00:00:00&endTime=2025-12-31 23:59:59
✅ 期望返回：包含 todayOrderCount、todayRevenue 等数据
```

### Step 8：导出Excel
```
GET /ordertrade/all-order/export
Params: pageNo=1&pageSize=999
✅ 期望返回：下载 Excel 文件
```

---

## 🔵 第二条闭环：充电订单 → 停止 → 支付

### Step 1：查询充电中的订单
```
GET /ordertrade/car-charge-order/page
Params: pageNo=1&pageSize=10
✅ 数据库里 TEST-CHARGE-001 处于充电中
```

### Step 2：停止充电
```
PUT /ordertrade/car-charge-order/stop
Body: { "id": 1, "remark": "手动停止充电" }
✅ 期望：充电结束，等待支付
```

### Step 3：支付充电费用
```
PUT /ordertrade/car-charge-order/pay
Body: { "id": 1 }
✅ 期望：支付成功
```

---

## 🔵 第三条闭环：退款申请全流程

### Step 1：查看待审核退款申请
```
GET /ordertrade/refund-apply/page
Params: pageNo=1&pageSize=10&auditStatus=pending_audit
✅ 数据库里有一条待审核的退款申请
```

### Step 2：审核通过
```
PUT /ordertrade/refund-apply/approve
Body: { "id": 1, "remark": "审核通过，同意退款" }
✅ 期望：auditStatus 变为 pending_exec
```

### Step 3：执行退款
```
PUT /ordertrade/refund-apply/execute
Body: { "id": 1 }
✅ 期望：退款执行，状态变为 completed
```

### Step 4：查看退款记录
```
GET /ordertrade/refund-record/page
Params: pageNo=1&pageSize=10
✅ 期望：看到刚生成的退款记录，status=success
```

### Step 5：退款金额核算
```
GET /ordertrade/amount-check/page
Params: pageNo=1&pageSize=10
✅ 查看核算状态
```

### Step 6：确认核算结果
```
PUT /ordertrade/amount-check/confirm
Body: { "id": 1 }
✅ 期望：status 变为 checked
```

---

## 🔵 第四条闭环：逃费识别 → 追缴跟踪

### Step 1：查看逃费识别列表
```
GET /ordertrade/debt-identify/page
Params: pageNo=1&pageSize=10
✅ 数据库里有待识别和已识别的数据
```

### Step 2：识别逃费
```
PUT /ordertrade/debt-identify/identify
Body: { "id": 1, "remark": "确认为逃费" }
✅ 期望：status 变为 identified
```

### Step 3：标记欠费
```
PUT /ordertrade/debt-identify/mark
Body: { "id": 1, "remark": "生成欠费记录" }
✅ 期望：生成 debt_record 记录
```

### Step 4：查看逃费记录
```
GET /ordertrade/debt-record/page
Params: pageNo=1&pageSize=10
✅ 数据库里有3条测试数据：未追缴/追缴中/已完成
```

### Step 5：发起追缴
```
PUT /ordertrade/debt-record/start-collect
Body: { "id": 1, "remark": "开始追缴" }
✅ 期望：状态变为 collecting
```

### Step 6：查看追缴配置
```
GET /ordertrade/collect-config/page
Params: pageNo=1&pageSize=10
✅ 看到两条测试配置
```

### Step 7：查看追缴跟踪
```
GET /ordertrade/collect-track/page
Params: pageNo=1&pageSize=10
✅ 看到3条跟踪记录
```

### Step 8：推送追缴消息
```
PUT /ordertrade/collect-track/push
Body: { "id": 1, "remark": "短信推送成功" }
✅ 期望：status 变为 collecting
```

### Step 9：查看欠费记录
```
GET /ordertrade/arrear-record/page
Params: pageNo=1&pageSize=10
✅ 看到欠费汇总数据
```

### Step 10：催缴
```
PUT /ordertrade/arrear-record/urge
Body: { "id": 1, "remark": "发送催缴通知" }
✅ 期望：操作成功
```

---

## 🔵 第五条闭环：取消订单

### Step 1：取消待支付订单
```
PUT /ordertrade/all-order/cancel
Body: { "id": 1, "remark": "用户主动取消" }
✅ 期望：status 变为 cancelled
```

---

## 📋 所有接口快速验证清单

| 模块 | 接口 | 验证数据 |
|------|------|---------|
| 全部订单 | GET /page | 返回8条测试数据 |
| 临时停车 | GET /page | 返回3条 |
| 错时停车 | GET /page | 返回1条 |
| 汽车充电 | GET /page | 返回2条 |
| 两轮充电 | GET /page | 返回1条 |
| 共享充电 | GET /page | 返回1条 |
| 异常订单 | GET /page | 返回2条 |
| 逃费识别 | GET /page | 返回2条 |
| 逃费记录 | GET /page | 返回3条 |
| 欠费记录 | GET /page | 返回2条 |
| 追缴配置 | GET /page | 返回2条 |
| 追缴跟踪 | GET /page | 返回3条 |
| 退款申请 | GET /page | 返回4条 |
| 退款记录 | GET /page | 返回1条 |
| 金额核算 | GET /page | 返回2条 |

---

## ⚠️ 注意事项

1. **执行顺序**：先执行 `test-data.sql`，再按上面顺序调试接口
2. **ID问题**：数据库自增ID从1开始，如果多次执行SQL，ID会不同，以实际查询到的ID为准
3. **时间格式**：所有时间参数格式为 `yyyy-MM-dd HH:mm:ss`
4. **ServiceImpl TODO**：业务操作接口（pay/stop/approve等）目前只有校验逻辑，实际状态流转需要按业务补充
