package cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.servicereport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 周期报表 DO。
 *
 * 表由 XxlJob 定时任务(每周期结束后)或管理员手动触发生成,
 * 生成后即"冻结快照",不会因底层业务表变动而漂移。
 */
@TableName("cycle_report")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 周期:日报/周报/月报/季报/半年报/年报/自定义 */
    private String reportCycle;

    private LocalDateTime statStartTime;
    private LocalDateTime statEndTime;

    /** 展示标签:2026-03 / 2026-W15 / 2026-Q1 / 2026-H1 / 2026 / 自定义区间字符串 */
    private String statTimeLabel;

    // ====== 5 大成功率(0~100 百分比)======
    private BigDecimal rescueCompleteRate;
    private BigDecimal reserveSuccessRate;
    private BigDecimal complaintHandleRate;
    private BigDecimal findCarSuccessRate;
    private BigDecimal spacePushSuccessRate;

    // ====== 4 大业务量 + 话术数 ======
    private Integer rescueTotal;
    private Integer reserveTotal;
    private Integer complaintTotal;
    private Integer spacePushTotal;
    private Integer effectiveWordingCount;

    // ====== 同环比(允许负,也允许 null)======
    private BigDecimal yearOnYearGrowthRate;
    private BigDecimal monthOnMonthGrowthRate;

    private String serviceStatusRatio;

    // ====== 报表元信息 ======
    private String generateStatus;       // 生成中/已生成/生成失败
    private LocalDateTime generateTime;
    private String operator;             // xxl-job / 昵称
    private Long operatorUserId;         // 定时任务为 null

    /**
     * 完整聚合结果 JSON:
     * 详情页 / chart 接口 / 同比环比 所需的折线、柱状、饼、地图数据均从此字段取。
     * 以 String 类型存,避免 MyBatis TypeHandler 绑定问题。
     */
    private String detailData;

}
