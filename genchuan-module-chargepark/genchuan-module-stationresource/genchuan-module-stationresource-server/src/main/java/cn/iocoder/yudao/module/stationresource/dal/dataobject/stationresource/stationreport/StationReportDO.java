package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.math.BigDecimal;

/**
 * 场站资源报表 DO
 *
 * @author 亘川智城
 */
@TableName("station_report")
@KeySequence("station_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库主键自增，MySQL 可删除
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationReportDO extends BaseDO {

    /**
     * 主键 ID（报表记录ID）
     */
    @TableId
    private Long id;

    /**
     * 统计编码
     */
    private String statCode;

    /**
     * 报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）
     */
    private String reportCycle;

    /**
     * 报表开始时间
     */
    private LocalDateTime reportStartTime;

    /**
     * 报表结束时间
     */
    private LocalDateTime reportEndTime;

    /**
     * 总片区数：来源 area_info 表统计有效记录总数
     */
    private Long totalAreaCount;

    /**
     * 覆盖场站数：来源 area_info 表 station_count 字段求和
     */
    private Long coverStationCount;

    /**
     * 总站场数：来源 station_info 表统计有效记录总数
     */
    private Long totalStationCount;

    /**
     * 正常运营数：来源 station_info 表 status = 已生效 的场站数量
     */
    private Long normalOperateCount;

    /**
     * 总车位数：来源 parking_space_info 表统计有效车位总数
     */
    private Long totalSpaceCount;

    /**
     * 可用车位数：来源 parking_space_info 表 real_status = 空闲 的车位数量
     */
    private Long availableSpaceCount;

    /**
     * 生效规则数：来源 time_permission + fee_rule + charge_park_link 已生效规则总数
     */
    private Long effectiveRuleCount;

    /**
     * 订单量：来源订单相关表统计当前周期内订单总数（时间筛选）
     */
    private Long orderCount;

    /**
     * 营收：来源订单相关表统计当前周期内总营收（时间筛选）
     */
    private BigDecimal revenue;

    /**
     * 追缴完成率：来源 debt_expand 表 recovery_rate 字段平均值
     */
    private BigDecimal recoveryRate;

    /**
     * 押金订单量：来源 deposit_plan 表 deposit_order_count 字段求和
     */
    private Long depositOrderCount;

    /**
     * 生成状态（生成中/已生成/生成失败）
     */
    private String generateStatus;

    /**
     * 报表生成时间
     */
    private LocalDateTime generateTime;

    /**
     * 操作人，关联 system_user
     */
    private String operator;

    /**
     * 报表导出次数
     */
    private Long exportCount;

//    /**
//     * 自定义筛选配置（仅自定义报表使用）
//     */
//    private String customConfig;

    /**
     * [备注] 扩展说明
     */
    private String remark;

    /**
     * [备用字段1] 预留扩展
     */
    private String reserve1;

    /**
     * [备用字段2] 预留扩展
     */
    private String reserve2;

    /**
     * [创建者] 创建人账号/姓名
     */

    private String creator;

    /**
     * [更新者] 更新人账号/姓名
     */

    private String updater;

    /**
     * [删除标识] 0-未删除 1-已删除
     */

    private Boolean deleted;

    /**
     * [创建时间] 记录创建时间
     */

    private LocalDateTime createTime;

    /**
     * [更新时间] 记录最后更新时间
     */

    private LocalDateTime updateTime;

}
