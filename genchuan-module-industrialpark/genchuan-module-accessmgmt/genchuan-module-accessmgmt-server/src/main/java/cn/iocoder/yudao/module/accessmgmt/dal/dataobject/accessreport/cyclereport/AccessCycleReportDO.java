package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.accessreport.cyclereport;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 通行周期报表 DO
 *
 * @author 亘川智城
 */
@TableName("access_cycle_report")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessCycleReportDO extends BaseDO {

    /**
     * [主键ID] 主键
     */
    @TableId
    private Long id;
    /**
     * [报表名称] 报表名称
     */
    private String reportName;
    /**
     * [周期类型] 日报/周报/月报/季报/半年报/年报/自定义
     */
    private String cycleType;
    /**
     * [开始时间] 开始时间
     */
    private LocalDateTime startTime;
    /**
     * [结束时间] 结束时间
     */
    private LocalDateTime endTime;
    /**
     * [人员通行总量] 人员通行总量
     */
    private Integer totalPersonAccess;
    /**
     * [访客到访总量] 访客到访总量
     */
    private Integer totalVisitorArrive;
    /**
     * [车辆通行总量] 车辆通行总量
     */
    private Integer totalVehicleAccess;
    /**
     * [车位使用率] 车位使用率
     */
    private BigDecimal spaceUseRate;
    /**
     * [缴费收入] 缴费收入
     */
    private BigDecimal payIncome;

}
