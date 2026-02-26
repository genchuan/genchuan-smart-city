package cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 城中村 DO
 *
 * @author 芋道源码
 */
@TableName("urban_village")
@KeySequence("urban_village_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrbanVillageDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String villageId;
    /**
     * 城中村名称
     */
    private String name;
    /**
     * 城中村地址
     */
    private String address;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 责任区域数
     */
    private Integer responsibilityAreas;
    /**
     * 道路保洁频次
     */
    private String roadCleaningFrequency;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 保洁达标率
     */
    private BigDecimal cleaningRate;
    /**
     * 问题处置完成率
     */
    private BigDecimal problemRate;
    /**
     * 复核通过率
     */
    private BigDecimal reviewPassRate;
    /**
     * 考核得分（满分100）
     */
    private BigDecimal assessmentScore;
    /**
     * 责任区域名称
     */
    private String responsibilityAreaName;
    /**
     * 保洁标准
     */
    private String cleaningStandard;
    /**
     * 负责人员IDs，JSON
     */
    private String staffIds;
    /**
     * 问题位置
     */
    private String problemLocation;
    /**
     * 问题描述
     */
    private String problemDesc;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 现场照片URL，JSON
     */
    private String problemPhotoUrl;
    /**
     * 关联sys_dept.id
     */
    private String deptId;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 关联sys_handle_status.id
     */
    private String handleStatusId;
    /**
     * 超时提醒：是/否
     */
    private String isTimeout;
    /**
     * 处置说明
     */
    private String handleDesc;
    /**
     * 整改照片URL，JSON
     */
    private String reformPhotoUrl;
    /**
     * 关联sys_user.id
     */
    private String reviewBy;
    /**
     * 复核时间
     */
    private LocalDateTime reviewTime;
    /**
     * 关联sys_review_result.id
     */
    private String reviewResultId;
    /**
     * 复核意见
     */
    private String reviewOpinion;
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

    private String planStatusId;

    private String problemTypeId;

    private String taskTypeId;
}