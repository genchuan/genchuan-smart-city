package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警列表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarningAlertListTablePageReqVO extends PageParam {

    @Schema(description = "告警编号")
    private String alertCode;

    @Schema(description = "关联对象类型", example = "2")
    private String relatedObjectType;

    @Schema(description = "关联对象ID", example = "20469")
    private String relatedObjectId;

    @Schema(description = "关联对象名称", example = "张三")
    private String relatedObjectName;

    @Schema(description = "预警领域")
    private String warningField;

    @Schema(description = "预警类型", example = "1")
    private String warningType;

    @Schema(description = "预警等级")
    private String warningLevel;

    @Schema(description = "预警状态", example = "1")
    private String warningStatus;

    @Schema(description = "触发原因", example = "不好")
    private String triggerReason;

    @Schema(description = "关联事件编号")
    private String relatedEventCode;

    @Schema(description = "派发部门")
    private String dispatchDepartment;

    @Schema(description = "责任人")
    private String responsiblePerson;

    @Schema(description = "责任人电话")
    private String responsiblePersonPhone;

    @Schema(description = "触发时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] triggerTime;

    @Schema(description = "要求完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] requiredCompleteTime;

    @Schema(description = "处置进展描述")
    private String disposalProgressDesc;

    @Schema(description = "处置附件路径")
    private String disposalAttachmentPath;

    @Schema(description = "审核意见")
    private String reviewOpinion;

    @Schema(description = "审核人")
    private String reviewer;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewTime;

    @Schema(description = "扩展分类字段1")
    private String extendCategory1;

    @Schema(description = "扩展分类字段2")
    private String extendCategory2;

    @Schema(description = "扩展分类字段3")
    private String extendCategory3;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "设备ID", example = "9924")
    private String deviceId;

    @Schema(description = "排序字段")
    private String orderByColumn;

    @Schema(description = "是否升序", example = "asc")
    private String isAsc;

    @Schema(description = "预警结果状态", example = "1")
    private Integer status;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "预警类型ID")
    private String warningTypeId;

    @Schema(description = "区域编码")
    private String regionCode;

    @Schema(description = "区域名称")
    private String regionName;

    @Schema(description = "网格ID")
    private String gridId;

    @Schema(description = "网格名称")
    private String gridName;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "事件描述")
    private String eventDescription;

    @Schema(description = "现场照片")
    private String scenePhotos;

    @Schema(description = "上传人姓名")
    private String uploaderName;

    @Schema(description = "上传人电话")
    private String uploaderPhone;

    @Schema(description = "标题")
    private String title;
}