package cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagelicense;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排水电子许可证信息 DO
 *
 * @author 超级管理员
 */
@TableName("smartcity_drainage_license")
@KeySequence("smartcity_drainage_license_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrainageLicenseDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 许可证编号
     */
    private String licenseNo;
    /**
     * 有效期开始日期
     */
    private LocalDateTime startDate;
    /**
     * 有效期结束日期
     */
    private LocalDateTime endDate;
    /**
     * 许可排水类型
     *
     * 枚举 {@link TODO sm_drainage_type 对应的类}
     */
    private String drainageType;
    /**
     * 审批单位
     */
    private String approvalUnit;
    /**
     * 状态
     *
     * 枚举 {@link TODO crm_audit_status 对应的类}
     */
    private String licenseStatus;

}