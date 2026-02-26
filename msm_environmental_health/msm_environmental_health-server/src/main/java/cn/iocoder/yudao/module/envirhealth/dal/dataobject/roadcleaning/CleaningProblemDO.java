package cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 道路清扫问题 DO
 *
 * @author 芋道源码
 */
@TableName("road_cleaning_problem")
@KeySequence("road_cleaning_problem_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleaningProblemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 问题主键（UUID）
     */
    private String problemId;
    /**
     * 关联road_cleaning.cleaning_id
     */
    private String planId;
    /**
     * 关联sys_problem_type.id
     */
    private String problemTypeId;
    /**
     * 问题位置
     */
    private String location;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 问题描述
     */
    private String desc;
    /**
     * 关联sys_team.id
     */
    private String teamId;
    /**
     * 处置状态：待处置/处理中/已办结
     */
    private String handleStatus;
    /**
     * 超时提醒：是/否
     */
    private String isTimeout;
    /**
     * 处置结果
     */
    private String handleResult;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}