package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.interconnection;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 互联互通表 DO
 *
 * @author 亘川智城
 */
@TableName("interconnection")
@KeySequence("interconnection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterconnectionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 对接编号
     */
    private String connectCode;
    /**
     * 第三方平台名称
     */
    private String thirdPlatform;
    /**
     * 对接类型
     */
    private String connectType;
    /**
     * API参数
     */
    private String apiParam;
    /**
     * 同步频率，单位：分钟
     */
    private Integer syncFreq;
    /**
     * 同步成功率，单位：%
     */
    private BigDecimal syncSuccessRate;
    /**
     * 对接状态：未申请/审核中/已开通/已关闭
     */
    private String connectStatus;
    /**
     * 审核人员
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;
    /**
     * 是否审核通过
     */
    @TableField(exist = false)
    private Boolean pass;

}