package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.decisionanalysis.stationopreport;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 场站运营报表（统计实体，无实际表，从各业务表统计）
 */
@TableName("station_op_report") // 可对应统计视图
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationOpReportDO {

    @TableId
    private Long id;

    private String reportType;    // 报表类型
    private String reportTime;   // 统计时间标识
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String creator;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
    private Long tenantId;

    // ===================== 统计字段（补上就不返回null了）=====================
    private Integer stationCount;       // 场站数
    private Integer totalSpaceCount;    // 总车位数
    private BigDecimal spaceUseRate;   // 车位使用率
    private BigDecimal ruleMatchRate;   // 规则匹配率
    private BigDecimal revenue;         // 营收
    private BigDecimal yearOnYear;      // 同比
    private BigDecimal monthOnMonth;    // 环比
}
