package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 基础监测事件 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BasicMonEvtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "事件ID")
    @ExcelProperty("事件ID")
    private String evtId;

    @Schema(description = "事件编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件编码")
    private String evtCode;

    @Schema(description = "事件类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件类型")
    private String evtType;

    @Schema(description = "关联点位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联点位ID")
    private String relPtId;

    @Schema(description = "关联点位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联点位名称")
    private String relPtName;

    @Schema(description = "关联指标ID")
    @ExcelProperty("关联指标ID")
    private String relIdxId;

    @Schema(description = "关联指标名称")
    @ExcelProperty("关联指标名称")
    private String relIdxName;

    @Schema(description = "关联设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联设备编码")
    private String relDeviceCode;

    @Schema(description = "关联设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联设备名称")
    private String relDeviceName;

    @Schema(description = "事件数值")
    @ExcelProperty("事件数值")
    private String evtValue;

    @Schema(description = "事发时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事发时间")
    private LocalDateTime evtTime;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置状态")
    private String dealStatus;

    @Schema(description = "派单部门代码")
    @ExcelProperty("派单部门代码")
    private String deptCode;

    @Schema(description = "派单部门名称")
    @ExcelProperty("派单部门名称")
    private String deptName;

    @Schema(description = "处置人ID")
    @ExcelProperty("处置人ID")
    private Long dealUserId;

    @Schema(description = "处置人姓名")
    @ExcelProperty("处置人姓名")
    private String dealUserName;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String dealResult;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime dealTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}