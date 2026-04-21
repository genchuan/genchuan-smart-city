package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.promotemgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 宣传管理 DO
 *
 * @author 芋道源码
 */
@TableName("promote_mgmt")
@KeySequence("promote_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoteMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 宣传任务名称
     */
    private String taskName;
    /**
     * 宣传站点
     */
    private String site;
    /**
     * 宣传人数
     */
    private Integer promoteNum;
    /**
     * 意向学生数
     */
    private Integer intentNum;
    /**
     * 执行人
     */
    private String executeUser;
    /**
     * 执行时间
     */
    private LocalDateTime executeTime;
    /**
     * 状态：未执行/已执行
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