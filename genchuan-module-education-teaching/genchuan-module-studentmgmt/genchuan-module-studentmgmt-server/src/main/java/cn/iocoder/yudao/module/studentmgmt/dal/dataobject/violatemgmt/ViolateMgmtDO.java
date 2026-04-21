package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违纪管理 DO
 *
 * @author 芋道源码
 */
@TableName("violate_mgmt")
@KeySequence("violate_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViolateMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 违纪类型：仪容仪表/行为违规/其他
     */
    private String violateType;
    /**
     * 处分类型：警告/记过/留校察看/开除
     */
    private String punishType;
    /**
     * 违纪时间
     */
    private LocalDateTime violateTime;
    /**
     * 违纪原因
     */
    private String violateReason;
    /**
     * 审批人
     */
    private String auditUser;
    /**
     * 审批时间
     */
    private LocalDateTime auditTime;
    /**
     * 家长推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 预警时间
     */
    private LocalDateTime warnTime;
    /**
     * 状态：待审批/已执行/已预警
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