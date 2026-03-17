package cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo;

import com.alibaba.excel.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 平台上报 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PlatformReportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27026")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上报UUID", example = "1216")
    @ExcelProperty("上报UUID")
    private String reportId;

    @Schema(description = "上报批次号")
    @ExcelProperty("上报批次号")
    private String batchNo;

    @Schema(description = "关联评价任务ID", example = "2512")
    @ExcelProperty("关联评价任务ID")
    private String taskId;

    @Schema(description = "上报人")
    @ExcelProperty("上报人")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "上报文件名称", example = "李四")
    @ExcelProperty("上报文件名称")
    private String fileName;

    @Schema(description = "数据条数", example = "3432")
    @ExcelProperty("数据条数")
    private Integer dataCount;

    @Schema(description = "成功条数", example = "25863")
    @ExcelProperty("成功条数")
    private Integer successCount;

    @Schema(description = "失败条数", example = "16406")
    @ExcelProperty("失败条数")
    private Integer failCount;

    @Schema(description = "数据状态", example = "2")
    @ExcelProperty("数据状态")
    private String status;

    @Schema(description = "校验时间")
    @ExcelProperty("校验时间")
    private LocalDateTime checkTime;

    @Schema(description = "校验操作人")
    @ExcelProperty("校验操作人")
    private String checkBy;

    @Schema(description = "错误文件下载链接", example = "https://www.iocoder.cn")
    @ExcelProperty("错误文件下载链接")
    private String errorFileUrl;

    @Schema(description = "模板下载状态：可下载/已下载", example = "2")
    @ExcelProperty("模板下载状态：可下载/已下载")
    private String templateStatus;

    @Schema(description = "文件预览链接：可预览/无", example = "https://www.iocoder.cn")
    @ExcelProperty("文件预览链接：可预览/无")
    private String filePreviewUrl;

    @Schema(description = "重新上传次数", example = "12871")
    @ExcelProperty("重新上传次数")
    private Integer reuploadCount;

    @Schema(description = "最近重新上传时间")
    @ExcelProperty("最近重新上传时间")
    private LocalDateTime lastReuploadTime;

    @Schema(description = "失败原因摘要", example = "不香")
    @ExcelProperty("失败原因摘要")
    private String failReason;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}