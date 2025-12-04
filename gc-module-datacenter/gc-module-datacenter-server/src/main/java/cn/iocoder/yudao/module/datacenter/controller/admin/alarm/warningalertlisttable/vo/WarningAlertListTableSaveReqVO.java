package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警列表新增/修改 Request VO")
@Data
public class WarningAlertListTableSaveReqVO {

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3855")
    private Long id;

    @Schema(description = "告警编号")
//    @NotEmpty(message = "告警编号不能为空") requiredMode = Schema.RequiredMode.REQUIRED
    private String alertCode;

    @Schema(description = "关联对象类型")
//    @NotEmpty(message = "关联对象类型不能为空")
    private String relatedObjectType;

    @Schema(description = "关联对象ID")
//    @NotEmpty(message = "关联对象ID不能为空")
    private String relatedObjectId;

    @Schema(description = "关联对象名称")
//    @NotEmpty(message = "关联对象名称不能为空")
    private String relatedObjectName;

    @Schema(description = "预警领域")
//    @NotEmpty(message = "预警领域不能为空")
    private String warningField;

    @Schema(description = "预警类型")
//    @NotEmpty(message = "预警类型不能为空")
    private String warningType;

    @Schema(description = "预警等级")
//    @NotEmpty(message = "预警等级不能为空")
    private String warningLevel;

    @Schema(description = "预警状态")
    private String warningStatus;

    @Schema(description = "触发原因")
//    @NotEmpty(message = "触发原因不能为空")
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
//    @NotNull(message = "触发时间不能为空")
    private LocalDateTime triggerTime;

    @Schema(description = "要求完成时间")
//    @NotNull(message = "要求完成时间不能为空")
    private LocalDateTime requiredCompleteTime;

    @Schema(description = "处置进展描述")
    private String disposalProgressDesc;

    @Schema(description = "处置附件路径")
    private String disposalAttachmentPath;

    @Schema(description = "审核意见")
    private String reviewOpinion;

    @Schema(description = "审核人")
    private String reviewer;

    @Schema(description = "审核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "扩展分类字段1")
    private String extendCategory1;

    @Schema(description = "扩展分类字段2")
    private String extendCategory2;

    @Schema(description = "扩展分类字段3")
    private String extendCategory3;

    @Schema(description = "设备ID")
//    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "预警结果")
    @NotNull(message = "预警结果")
    private Byte status;

    @Schema(description = "流程实例的编号")
//    @NotEmpty(message = "流程实例的编号")
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

    @Schema(description = "经度", example = "116.397128")
    private String longitude;

    @Schema(description = "纬度", example = "39.916527")
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