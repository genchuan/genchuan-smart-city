package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 平台上报数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalPlatformRptDataRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上报数据ID")
    @ExcelProperty("上报数据ID")
    private String platformRptDataId;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务名称")
    private String evalTaskName;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "关联指标项ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联指标项ID")
    private String idxItemId;

    @Schema(description = "关联指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联指标项名称")
    private String idxItemName;

    @Schema(description = "数据值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据值")
    private String dataValue;

    @Schema(description = "数据单位")
    @ExcelProperty("数据单位")
    private String dataUnit;

    @Schema(description = "上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报时间")
    private LocalDateTime rptTime;

    @Schema(description = "上报人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报人ID")
    private String rptUserId;

    @Schema(description = "上报人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报人姓名")
    private String rptUserName;

    @Schema(description = "数据状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据状态")
    private String dataStatus;

    @Schema(description = "校验失败原因")
    @ExcelProperty("校验失败原因")
    private String verifyFailReason;

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