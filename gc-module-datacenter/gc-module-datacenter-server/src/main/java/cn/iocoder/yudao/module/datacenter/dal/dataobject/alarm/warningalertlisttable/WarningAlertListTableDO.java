package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.warningalertlisttable;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警列表 DO
 *
 * @author 亘川智城
 */
@TableName("gc_warning_alert_list_table")
@KeySequence("gc_warning_alert_list_table_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarningAlertListTableDO extends BaseDO {

    /**
     * 预警ID
     */
    @TableId
    private Long id;
    /**
     * 告警编号
     */
    private String alertCode;
    /**
     * 关联对象类型
     */
    private String relatedObjectType;
    /**
     * 关联对象ID
     */
    private String relatedObjectId;
    /**
     * 关联对象名称
     */
    private String relatedObjectName;
    /**
     * 预警领域
     */
    private String warningField;
    /**
     * 预警类型
     */
    private String warningType;
    /**
     * 预警等级
     */
    private String warningLevel;
    /**
     * 预警状态
     */
    private String warningStatus;
    /**
     * 触发原因
     */
    private String triggerReason;
    /**
     * 关联事件编号
     */
    private String relatedEventCode;
    /**
     * 派发部门
     */
    private String dispatchDepartment;
    /**
     * 责任人
     */
    private String responsiblePerson;
    /**
     * 责任人电话
     */
    private String responsiblePersonPhone;
    /**
     * 触发时间
     */
    private LocalDateTime triggerTime;
    /**
     * 要求完成时间
     */
    private LocalDateTime requiredCompleteTime;
    /**
     * 处置进展描述
     */
    private String disposalProgressDesc;
    /**
     * 处置附件路径
     */
    private String disposalAttachmentPath;
    /**
     * 审核意见
     */
    private String reviewOpinion;
    /**
     * 审核人
     */
    private String reviewer;
    /**
     * 审核时间
     */
    private LocalDateTime reviewTime;
    /**
     * 扩展分类字段1
     */
    private String extendCategory1;
    /**
     * 扩展分类字段2
     */
    private String extendCategory2;
    /**
     * 扩展分类字段3
     */
    private String extendCategory3;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 预警状态
     */
    private Integer status;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 预警类型ID
     */
    private String warningTypeId;

    /**
     * 区域编码
     */
    private String regionCode;

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 网格ID
     */
    private String gridId;

    /**
     * 网格名称
     */
    private String gridName;

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 事件描述
     */
    private String eventDescription;

    /**
     * 现场照片（Base64编码，多个图片用分号分隔）
     * 格式：data:image/jpeg;base64,xxx;data:image/png;base64,yyy
     */
    private String scenePhotos;

    /**
     * 上传人姓名
     */
    private String uploaderName;

    /**
     * 上传人电话
     */
    private String uploaderPhone;

    /**
     * 标题
     */
    private String title;
}