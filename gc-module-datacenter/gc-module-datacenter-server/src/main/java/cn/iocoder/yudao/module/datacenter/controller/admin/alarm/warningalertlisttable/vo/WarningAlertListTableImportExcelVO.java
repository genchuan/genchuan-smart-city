package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.alibaba.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 预警告警列表 Excel 导入 VO")
@Data
public class WarningAlertListTableImportExcelVO {

    @ExcelProperty("告警编号")
    private String alertCode;

    @ExcelProperty("关联对象类型")
    private String relatedObjectType;

    @ExcelProperty("关联对象ID")
    private String relatedObjectId;

    @ExcelProperty("关联对象名称")
    private String relatedObjectName;

    @ExcelProperty("预警领域")
    private String warningField;

    @ExcelProperty("预警类型")
    private String warningType;

    @ExcelProperty("预警等级")
    private String warningLevel;

    @ExcelProperty("预警状态")
    private String warningStatus;

    @ExcelProperty("触发原因")
    private String triggerReason;

    @ExcelProperty("关联事件编号")
    private String relatedEventCode;

    @ExcelProperty("派发部门")
    private String dispatchDepartment;

    @ExcelProperty("责任人")
    private String responsiblePerson;

    @ExcelProperty("责任人电话")
    private String responsiblePersonPhone;

    @ExcelProperty("触发时间(yyyy-MM-dd HH:mm:ss)")
    private String triggerTime;

    @ExcelProperty("要求完成时间(yyyy-MM-dd HH:mm:ss)")
    private String requiredCompleteTime;

    @ExcelProperty("处置进展描述")
    private String disposalProgressDesc;

    @ExcelProperty("处置附件路径")
    private String disposalAttachmentPath;

    @ExcelProperty("审核意见")
    private String reviewOpinion;

    @ExcelProperty("审核人")
    private String reviewer;

    @ExcelProperty("审核时间(yyyy-MM-dd HH:mm:ss)")
    private String reviewTime;

    @ExcelProperty("扩展分类字段1")
    private String extendCategory1;

    @ExcelProperty("扩展分类字段2")
    private String extendCategory2;

    @ExcelProperty("扩展分类字段3")
    private String extendCategory3;

    @ExcelProperty("设备ID")
    private String deviceId;

    @ExcelProperty("流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("预警类型ID")
    private String warningTypeId;

    @ExcelProperty("区域编码")
    private String regionCode;

    @ExcelProperty("区域名称")
    private String regionName;

    @ExcelProperty("网格ID")
    private String gridId;

    @ExcelProperty("网格名称")
    private String gridName;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("经度")
    private String longitude;  // Excel中通常用字符串格式

    @ExcelProperty("纬度")
    private String latitude;

    @ExcelProperty("事件描述")
    private String eventDescription;

    @ExcelProperty("现场照片路径")
    private String scenePhotos;

    @ExcelProperty("上传人姓名")
    private String uploaderName;

    @ExcelProperty("上传人电话")
    private String uploaderPhone;

    @ExcelProperty("标题")
    private String title;
}


