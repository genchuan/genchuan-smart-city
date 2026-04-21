package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt;

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
 * 荣誉管理 DO
 *
 * @author 芋道源码
 */
@TableName("honor_mgmt")
@KeySequence("honor_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HonorMgmtDO extends BaseDO {

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
     * 荣誉类型：优秀学生/奖学金/竞赛获奖/其他
     */
    private String honorType;
    /**
     * 荣誉名称
     */
    private String honorName;
    /**
     * 获得时间
     */
    private LocalDateTime getTime;
    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 推送时间
     */
    private LocalDateTime pushTime;
    /**
     * 状态：待审核/已通过/已推送
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