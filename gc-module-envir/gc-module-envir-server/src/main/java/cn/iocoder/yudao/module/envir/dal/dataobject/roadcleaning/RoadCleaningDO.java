package cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 道路清扫计划 DO
 *
 * @author 芋道源码
 */
@TableName("road_cleaning")
@KeySequence("road_cleaning_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadCleaningDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String roadCleaningId;
    /**
     * 清扫计划编号
     */
    private String planNo;
    /**
     * 清扫路段（关联sys_road.sys_road_id）
     */
    private String roadId;
    /**
     * 责任区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 清扫频次
     */
    private String frequency;
    /**
     * 清扫时段
     */
    private String timePeriod;
    /**
     * 负责人员（关联sys_user.id，多个用逗号分隔）
     */
    private String staffIds;
    /**
     * 清扫工具（关联sys_tool.sys_tool_id，多个用逗号分隔）
     */
    private String toolIds;
    /**
     * 计划状态（关联sys_plan_status.sys_plan_status_id）
     */
    private String planStatusId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String abnormalCreateBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 质量达标率
     */
    private BigDecimal qualityRate;
    /**
     * 问题处置数
     */
    private Integer problemCount;
    /**
     * 考勤全勤率
     */
    private BigDecimal attendanceRate;
    /**
     * 质量核查对比照片URL（多个用逗号分隔）
     */
    private String checkPhotoUrl;
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