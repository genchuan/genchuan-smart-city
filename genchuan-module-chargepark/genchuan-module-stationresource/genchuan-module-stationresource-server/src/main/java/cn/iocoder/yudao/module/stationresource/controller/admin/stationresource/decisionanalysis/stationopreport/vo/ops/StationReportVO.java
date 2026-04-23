package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StationReportVO {

    // ========================== 1. 基础周期信息 ==========================
    private String reportCycleType;    // 周期类型：daily/weekly/monthly/quarterly/halfyear/year/custom
    private String cycleStartTime;     // 周期开始时间
    private String cycleEndTime;       // 周期结束时间
    private LocalDateTime generateTime;// 报表生成时间
    private String operator;           // 操作人
    private Integer exportCount;       // 导出次数
    private String generateStatus;     // 生成状态：已生成/生成中/失败

    // ========================== 2. 片区统计 ==========================
    private Integer totalAreaCount;
    // 表来源：area_info（片区信息表）
    // 统计规则：tenant_id = ? AND deleted = 0 的总记录数

    // ========================== 3. 场站统计 ==========================
    private Integer totalStationCount;
    // 表来源：station_info（场站信息表）
    // 统计规则：tenant_id = ? AND deleted = 0 的总站场数

    private Integer coveredStationCount;
    // 表来源：area_info.station_count（片区信息表.覆盖场站数字段）
    // 统计规则：所有片区 station_count 求和 / 直接统计已归属片区的场站数

    private Integer normalOperationCount;
    // 表来源：station_info（场站信息表）
    // 统计规则：status = '已生效' AND deleted = 0

    // ========================== 4. 车位统计 ==========================
    private Integer totalParkingSpaceCount;
    // 表来源：parking_space_info（车位信息表）
    // 统计规则：deleted = 0 的总记录数

    private Integer availableParkingSpaceCount;
    // 表来源：parking_space_info（车位信息表）
    // 统计规则：real_status = '空闲' AND status = '已绑定' AND deleted = 0

    // ========================== 5. 规则统计 ==========================
    private Integer effectiveRuleCount;
    // 表来源：以下所有规则表【生效状态】求和
    // time_permission、fee_rule、charge_park_link、black_white_list、offtime_rule、deposit_plan
    // 统计规则：status = '已生效' AND deleted = 0

    // ========================== 6. 订单 & 营收 ==========================
    private Integer orderCount;         // 订单量
    // 表来源：订单主表（charge-park 订单表，你文档未新建，统一按订单表统计）
    // 统计规则：时间周期内订单总数

    private BigDecimal totalIncome;     // 总营收
    // 表来源：订单主表（charge-park 订单表）
    // 统计规则：时间周期内已支付订单金额总和

    // ========================== 7. 周报独有 ==========================
    private BigDecimal orderMatchRate;  // 订单匹配率
    // 表来源：fee_rule.match_rate（收费规则表.订单匹配率）
    // 统计规则：周期内生效规则平均匹配率 / 按订单统计匹配率

    // ========================== 8. 月报独有 ==========================
    private BigDecimal recoveryCompleteRate; // 追缴完成率
    // 表来源：debt_expand.recovery_rate（追缴拓场配置表.追缴完成率）
    // 统计规则：所有合作场站追缴完成率平均值/合计值

    private Integer depositOrderCount;  // 押金订单量
    // 表来源：deposit_plan.deposit_order_count（押金方案表.押金订单量）
    // 统计规则：周期内累计新增

    // ========================== 9. 季报/年报独有 ==========================
    private BigDecimal payRate;         // 支付率
    // 表来源：charge_park_link.pay_rate（充停联动表.支付率）
    // 统计规则：周期内平均支付率

    private Integer expandCompleteCount;// 拓场完成数
    // 表来源：debt_expand（追缴拓场配置表）
    // 统计规则：status = '已生效' AND progress = 100% 的记录数

    // ========================== 10. 半年报独有 ==========================
    private Integer interceptCount;     // 拦截次数
    // 表来源：black_white_list.intercept_count（黑白名单表.拦截次数）
    // 统计规则：周期内累计拦截次数

    // ========================== 11. 自定义报表 ==========================
    private String yearOnYearData;      // 同比数据（基于以上所有指标计算）
    private String ringRatioData;       // 环比数据（基于以上所有指标计算）
}
