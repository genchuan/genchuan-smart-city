package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StationReportVO {

    // 1. 基础周期信息
    private String reportCycleType;    // 周期类型：daily/weekly/monthly/quarterly/halfyear/year/custom
    private String cycleStartTime;     // 周期开始时间
    private String cycleEndTime;      // 周期结束时间
    private LocalDateTime generateTime;// 报表生成时间
    private String operator;           // 操作人
    private Integer exportCount;       // 导出次数
    private String generateStatus;     // 生成状态：已生成/生成中/失败

    // 2. 片区统计
    private Integer totalAreaCount;    // 总片区数

    // 3. 场站统计
    private Integer totalStationCount;      // 总站场数
    private Integer coveredStationCount;    // 覆盖场站数
    private Integer normalOperationCount;   // 正常运营数

    // 4. 车位统计
    private Integer totalParkingSpaceCount;   // 总车位数
    private Integer availableParkingSpaceCount;// 可用车位数

    // 5. 规则统计
    private Integer effectiveRuleCount;     // 生效规则数

    // 6. 订单 & 营收
    private Integer orderCount;         // 订单量
    private BigDecimal totalIncome;      // 总营收

    // 7. 周报独有
    private BigDecimal orderMatchRate;   // 订单匹配率

    // 8. 月报独有
    private BigDecimal recoveryCompleteRate;// 追缴完成率
    private Integer depositOrderCount;    // 押金订单量

    // 9. 季报/年报独有
    private BigDecimal payRate;          // 支付率
    private Integer expandCompleteCount; // 拓场完成数

    // 10. 半年报独有
    private Integer interceptCount;      // 拦截次数

    // 11. 自定义报表
    private String yearOnYearData;      // 同比数据
    private String ringRatioData;       // 环比数据
}
