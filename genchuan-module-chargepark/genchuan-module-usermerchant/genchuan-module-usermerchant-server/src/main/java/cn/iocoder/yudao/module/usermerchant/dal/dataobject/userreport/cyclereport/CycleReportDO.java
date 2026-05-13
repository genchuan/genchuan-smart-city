package cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 周期报表存储 DO
 *
 * @author 亘川智城
 */
@TableName("cycle_report_usermerchant")
@KeySequence("cycle_report_usermerchant_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
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
     * 报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）
     */
    private String reportCycle;
    /**
     * 统计时间
     */
    private String statTime;
    /**
     * 统计开始时间
     */
    private LocalDateTime statStartTime;
    /**
     * 统计结束时间
     */
    private LocalDateTime statEndTime;
    /**
     * 报表名称
     */
    private String reportName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 新增用户数
     */
    private Integer newUserCount;
    /**
     * 绑定车辆数
     */
    private Integer bindCarCount;
    /**
     * 车牌认证量
     */
    private Integer plateAuthCount;
    /**
     * 新增商户数
     */
    private Integer newMerchantCount;
    /**
     * 对接商户数
     */
    private Integer linkMerchantCount;
    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * 发券量
     */
    private Integer sendCouponCount;
    /**
     * 新增集团数
     */
    private Integer newGroupCount;
    /**
     * 会员新增数
     */
    private Integer newMemberCount;
    /**
     * 平均信用分
     */
    private Integer avgCreditScore;
    /**
     * 图表分布数据（JSON格式存储柱状图/饼图/折线图数据）
     */
    private String distributionData;
    /**
     * 报表生成状态（待生成/已生成/生成失败）
     */
    private String reportStatus;
    /**
     * 导出次数
     */
    private Integer exportCount;
    /**
     * 租户ID
     */
    private Long tenantId;


}