package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警列表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WarningAlertListTableRespVO {

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警ID")
    private Long id;

    @Schema(description = "告警编号")
    @ExcelProperty("告警编号")
    private String alertCode;

    @Schema(description = "关联对象类型")
    @ExcelProperty("关联对象类型")
    private String relatedObjectType;

    @Schema(description = "关联对象ID")
    @ExcelProperty("关联对象ID")
    private String relatedObjectId;

    @Schema(description = "关联对象名称")
    @ExcelProperty("关联对象名称")
    private String relatedObjectName;

    @Schema(description = "预警领域")
    @ExcelProperty("预警领域")
    private String warningField;

    @Schema(description = "预警类型")
    @ExcelProperty("预警类型")
    private String warningType;

    @Schema(description = "预警等级")
    @ExcelProperty("预警等级")
    private String warningLevel;

    @Schema(description = "预警状态")
    @ExcelProperty("预警状态")
    private String warningStatus;

    @Schema(description = "触发原因")
    @ExcelProperty("触发原因")
    private String triggerReason;

    @Schema(description = "关联事件编号")
    @ExcelProperty("关联事件编号")
    private String relatedEventCode;

    @Schema(description = "派发部门")
    @ExcelProperty("派发部门")
    private String dispatchDepartment;

    @Schema(description = "责任人")
    @ExcelProperty("责任人")
    private String responsiblePerson;

    @Schema(description = "责任人电话")
    @ExcelProperty("责任人电话")
    private String responsiblePersonPhone;

    @Schema(description = "触发时间")
    @ExcelProperty("触发时间")
    private LocalDateTime triggerTime;

    @Schema(description = "要求完成时间")
    @ExcelProperty("要求完成时间")
    private LocalDateTime requiredCompleteTime;

    @Schema(description = "处置进展描述")
    @ExcelProperty("处置进展描述")
    private String disposalProgressDesc;

    @Schema(description = "处置附件路径")
    @ExcelProperty("处置附件路径")
    private String disposalAttachmentPath;

    @Schema(description = "审核意见")
    @ExcelProperty("审核意见")
    private String reviewOpinion;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String reviewer;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "扩展分类字段1")
    @ExcelProperty("扩展分类字段1")
    private String extendCategory1;

    @Schema(description = "扩展分类字段2")
    @ExcelProperty("扩展分类字段2")
    private String extendCategory2;

    @Schema(description = "扩展分类字段3")
    @ExcelProperty("扩展分类字段3")
    private String extendCategory3;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "设备ID")
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "预警结果")
    @ExcelProperty("预警结果")
    private Byte status;

    @Schema(description = "流程实例的编号")
    @ExcelProperty("流程实例的编号")
    private String processInstanceId;

    @Schema(description = "预警类型ID")
    @ExcelProperty("预警类型ID")
    private String warningTypeId;

    @Schema(description = "区域编码")
    @ExcelProperty("区域编码")
    private String regionCode;

    @Schema(description = "区域名称")
    @ExcelProperty("区域名称")
    private String regionName;

    @Schema(description = "网格ID")
    @ExcelProperty("网格ID")
    private String gridId;

    @Schema(description = "网格名称")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "地址")
    @ExcelProperty("地址")
    private String address;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private String longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private String latitude;

    @Schema(description = "事件描述")
    @ExcelProperty("事件描述")
    private String eventDescription;

    @Schema(description = "现场照片")
    @ExcelProperty("现场照片")
    private String scenePhotos;

    @Schema(description = "上传人姓名")
    @ExcelProperty("上传人姓名")
    private String uploaderName;

    @Schema(description = "上传人电话")
    @ExcelProperty("上传人电话")
    private String uploaderPhone;

    @Schema(description = "标题")
    @ExcelProperty("标题")
    private String title;
}