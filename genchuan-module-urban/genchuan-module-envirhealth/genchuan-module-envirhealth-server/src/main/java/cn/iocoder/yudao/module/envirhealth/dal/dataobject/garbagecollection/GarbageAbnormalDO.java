package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 垃圾异常记录 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_abnormal")
@KeySequence("garbage_abnormal_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarbageAbnormalDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 异常记录主键（UUID）
     */
    private String abnormalId;
    /**
     * 关联garbage_collection.collection_id
     */
    private String planId;
    /**
     * 关联sys_abnormal_type.id
     */
    private String abnormalTypeId;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 优先级：高/中/低
     */
    private String priority;
    /**
     * 关联sys_user.id
     */
    private String handlerId;
    /**
     * 处置状态：待处置/处理中/已办结/退回
     */
    private String handleStatus;
    /**
     * 超时提醒：是/否
     */
    private String isTimeout;
    /**
     * 异常描述
     */
    private String abnormalDesc;
    /**
     * 异常照片
     */
    private String abnormalPhotoUrl;
    /**
     * 整改说明
     */
    private String handleDesc;
    /**
     * 整改照片URL，JSON
     */
    private String handlePhotoUrl;
    /**
     * 处置时间
     */
    private LocalDateTime handleTime;
    /**
     * 复核状态：待复核/通过/退回
     */
    private String reviewStatus;
    /**
     * 关联sys_user.id
     */
    private String reviewBy;
    /**
     * 复核意见
     */
    private String reviewDesc;
    /**
     * 复核时间
     */
    private LocalDateTime reviewTime;
    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;
}