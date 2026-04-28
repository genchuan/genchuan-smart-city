// 文件: CycleReportDO.java
// 路径: cn/iocoder/yudao/module/inspectop/dal/dataobject/cyclereport/CycleReportDO.java
package cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("cycle_report")
@KeySequence("cycle_report_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CycleReportDO extends BaseDO {
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报表周期
     */
    private String reportCycle;
    /**
     * 场站ID
     */
    private Long stationId;
    /**
     * 场站名称
     */
    private String stationName;
    /**
     * 统计开始时间
     */
    private LocalDateTime statTimeStart;
    /**
     * 统计结束时间
     */
    private LocalDateTime statTimeEnd;
    /**
     * 正常设备数
     */
    private Integer normalDeviceNum;
    /**
     * 异常设备数
     */
    private Integer abnormalDeviceNum;
    /**
     * 巡检任务数
     */
    private Integer inspectTaskNum;
    /**
     * 任务完成率
     */
    private BigDecimal taskCompleteRate;
    /**
     * 油车占位待处置数
     */
    private Integer oilWaitHandleNum;
    /**
     * 处置完成率
     */
    private BigDecimal oilHandleCompleteRate;
    /**
     * 巡检人员在岗数
     */
    private Integer inspectUserOnlineNum;
    /**
     * 资产正常数
     */
    private Integer assetNormalNum;
    /**
     * 库存预警数
     */
    private Integer stockWarnNum;
    /**
     * 生成状态
     */
    private String generateStatus;
    /**
     * 报表生成时间
     */
    private LocalDateTime generateTime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 导出次数
     */
    private Integer exportCount;
    /**
     * 同比数据
     */
    private String yearOnYearData;
    /**
     * 环比数据
     */
    private String chainRatioData;
    /**
     * 租户ID
     */
    private Long tenantId;
}