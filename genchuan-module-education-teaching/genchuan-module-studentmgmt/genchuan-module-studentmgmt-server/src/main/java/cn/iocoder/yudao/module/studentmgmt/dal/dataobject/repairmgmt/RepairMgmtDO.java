package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt;

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
 * 报修管理 DO
 *
 * @author 芋道源码
 */
@TableName("repair_mgmt")
@KeySequence("repair_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 宿舍号
     */
    private String dormNum;
    /**
     * 报修类型：水电/家具/其他
     */
    private String repairType;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 派单人
     */
    private String dispatchUser;
    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 维修人
     */
    private String repairUser;
    /**
     * 维修反馈
     */
    private String feedbackContent;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 验收人
     */
    private String checkUser;
    /**
     * 验收时间
     */
    private LocalDateTime checkTime;
    /**
     * 状态：待派单/维修中/已维修
     */
    private String status;
    /**
     * 验收状态：未验收/已验收
     */
    private String checkStatus;
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