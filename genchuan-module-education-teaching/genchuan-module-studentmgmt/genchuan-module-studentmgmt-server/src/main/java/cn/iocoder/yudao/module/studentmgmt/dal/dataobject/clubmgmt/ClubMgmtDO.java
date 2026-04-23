package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 社团管理 DO
 *
 * @author 芋道源码
 */
@TableName("club_mgmt")
@KeySequence("club_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 社团名称
     */
    private String clubName;
    /**
     * 社团类型：文体/学术/志愿/其他
     */
    private String clubType;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 入团申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 建档时间
     */
    private LocalDateTime archiveTime;
    /**
     * 场馆申请状态：无/待申请/已通过
     */
    private String venueApplyStatus;
    /**
     * 状态：待审核/已通过/已建档
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