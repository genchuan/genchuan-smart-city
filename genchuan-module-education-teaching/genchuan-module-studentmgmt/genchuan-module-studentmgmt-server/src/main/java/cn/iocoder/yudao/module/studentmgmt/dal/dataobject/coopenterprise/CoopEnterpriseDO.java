package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.coopenterprise;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 校企合作 DO
 *
 * @author 芋道源码
 */
@TableName("coop_enterprise")
@KeySequence("coop_enterprise_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoopEnterpriseDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业类型：国企/民企/外企
     */
    private String enterpriseType;
    /**
     * 负责系部
     */
    private Long deptId;
    /**
     * 联系人
     */
    private String contactUser;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 合作开始时间
     */
    private LocalDateTime coopStartTime;
    /**
     * 合作结束时间
     */
    private LocalDateTime coopEndTime;
    /**
     * 状态：合作中/已结束
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}