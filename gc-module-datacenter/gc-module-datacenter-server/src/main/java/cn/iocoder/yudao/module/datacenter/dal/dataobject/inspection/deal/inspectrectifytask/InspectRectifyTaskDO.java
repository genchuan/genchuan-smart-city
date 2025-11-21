package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytask;

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
 * 巡查巡检整改任务派发 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_rectify_task")
@KeySequence("biz_inspect_rectify_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRectifyTaskDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 整改任务ID
     */
    private String rectifyTaskId;
    /**
     * 整改任务编码
     */
    private String rectifyTaskCode;
    /**
     * 关联问题ID
     */
    private String problemId;
    /**
     * 关联问题编码
     */
    private String problemCode;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 责任部门代码
     */
    private String deptCode;
    /**
     * 责任部门名称
     */
    private String deptName;
    /**
     * 整改负责人ID
     */
    private String leaderUserId;
    /**
     * 整改负责人姓名
     */
    private String leaderUserName;
    /**
     * 整改时限（小时）
     */
    private Integer rectifyDeadlineHour;
    /**
     * 整改截止时间
     */
    private LocalDateTime rectifyDeadlineTime;
    /**
     * 派发说明
     */
    private String dispatchDesc;
    /**
     * 整改任务状态
     */
    private String rectifyStatus;
    /**
     * 派发人
     */
    private String dispatchUser;
    /**
     * 派发时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新时间
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}