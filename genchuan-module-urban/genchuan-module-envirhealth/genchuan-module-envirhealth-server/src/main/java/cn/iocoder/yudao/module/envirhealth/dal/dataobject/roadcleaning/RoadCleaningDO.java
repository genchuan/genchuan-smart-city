package cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
     * 清扫计划主键（UUID）
     */
    private String cleaningId;
    /**
     * 清扫计划编号
     */
    private String planNo;
    /**
     * 关联sys_road.id
     */
    private String roadId;
    /**
     * 关联sys_area.area_code
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
     * 负责人员IDs，JSON
     */
    private String staffIds;
    /**
     * 关联sys_plan_status.id
     */
    private String planStatusId;
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
     * 清扫工具IDs，JSON
     */
    private String toolIds;
    /**
     * 清扫标准
     */
    private String standard;
    /**
     * 到岗时间
     */
    private LocalDateTime checkinTime;
    /**
     * 当前进度
     */
    private String progress;
    /**
     * 作业状态：运行/暂停/异常
     */
    private String operationStatus;
    /**
     * 轨迹覆盖情况：合规/偏离
     */
    private String trackCoverage;
    /**
     * 最新上报时间
     */
    private LocalDateTime lastReportTime;
    /**
     * 是否异常：是/否
     */
    private String isAbnormal;
    /**
     * 作业完成时间
     */
    private LocalDateTime completeTime;
    /**
     * 上报照片URL，JSON
     */
    private String checkPhotoUrl;
    /**
     * 核查状态：待核查/达标/不达标
     */
    private String reviewStatus;
    /**
     * 关联sys_user.id
     */
    private String reviewBy;
    /**
     * 核查时间
     */
    private LocalDateTime reviewTime;
    /**
     * 整改要求
     */
    private String reformRequire;
    /**
     * 复盘意见
     */
    private String reviewDesc;
    /**
     * 是否异常：是/否
     */
    private String isEffective;

    /**
     * 现场照片URL，JSON
     */
    private String localePhotoUrl;

    /**
     * 核查照片URL，JSON
     */
    private String reviewPhotoUrl;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;
}